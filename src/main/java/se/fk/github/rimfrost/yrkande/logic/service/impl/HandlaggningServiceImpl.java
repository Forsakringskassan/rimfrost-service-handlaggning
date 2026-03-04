package se.fk.github.rimfrost.yrkande.logic.service.impl;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.rimfrost.yrkande.integration.KafkaProducer;
import se.fk.github.rimfrost.yrkande.logic.dto.*;
import se.fk.github.rimfrost.yrkande.logic.dto.HandlaggningCreateRequest;
import se.fk.github.rimfrost.yrkande.logic.entity.*;
import se.fk.github.rimfrost.yrkande.logic.entity.YrkandeEntity;
import se.fk.github.rimfrost.yrkande.logic.repository.YrkandeRepository;
import se.fk.github.rimfrost.yrkande.logic.repository.HandlaggningRepository;
import se.fk.github.rimfrost.yrkande.logic.service.HandlaggningService;
import se.fk.github.rimfrost.yrkande.logic.util.LogicEnumMapper;
import se.fk.github.rimfrost.yrkande.logic.util.LogicMapper;

@ApplicationScoped
public class HandlaggningServiceImpl implements HandlaggningService
{
   @Inject
   HandlaggningRepository handlaggningRepository;

   @Inject
   YrkandeRepository yrkandeRepository;

   @Inject
   KafkaProducer producer;

   @Inject
   private LogicMapper mapper;

   @Inject
   LogicEnumMapper enumMapper;

   private static final Logger LOGGER = LoggerFactory.getLogger(HandlaggningServiceImpl.class);

   @Override
   public HandlaggningCreateResponse createHandlaggning(HandlaggningCreateRequest request)
   {

      YrkandeEntity yrkandeEntity = yrkandeRepository.findById(request.yrkandeId()).orElse(null);

      // Innehåller ingen uppgiftspecifikation just nu
      HandlaggningspecifikationEntity handlaggningspecifikationEntity = ImmutableHandlaggningspecifikationEntity
            .builder()
            .id(UUID.randomUUID())
            .version("1.0")
            .bpmn("VAH") // TODO: Uppdatera detta till något annat baserat på yrkandets typ?
            .namn("Vård av hudsjur") // TODO: Uppdatera detta till något annat baserat på yrkandets typ?
            .beskrivning("Kollar om du har rätt för ersättning för vård av husdjur")
            .build();

      HandlaggningEntity handlaggningEntity = ImmutableHandlaggningEntity.builder()
            .id(UUID.randomUUID())
            .yrkande(yrkandeEntity)
            .version("1.0")
            .processinstansId(UUID.randomUUID()) // TODO: Fixa denna så att vi startar en ny process och stoppar den här??
            .skapadTS(OffsetDateTime.now())
            .handlaggningspecifikation(handlaggningspecifikationEntity)
            .build();

      handlaggningRepository.save(handlaggningEntity);
      producer.sendVahRequestMessage(handlaggningEntity.id());

      HandlaggningCreateResponse response = ImmutableHandlaggningCreateResponse.builder()
            .handlaggning(mapper.toHandlaggningDTO(handlaggningEntity))
            .build();

      return response;
   }

   @Override
   public HandlaggningGetResponse getHandlaggning(HandlaggningGetRequest request)
   {
      HandlaggningEntity handlaggningEntity = handlaggningRepository.findById(request.handlaggningId()).orElse(null);
      if (handlaggningEntity == null)
      {
         return null;
      }

      HandlaggningGetResponse response = ImmutableHandlaggningGetResponse.builder()
            .handlaggning(mapper.toHandlaggningDTO(handlaggningEntity))
            .build();

      return response;
   }

   @Override
   public HandlaggningPutResponse putHandlaggning(HandlaggningPutRequest request)
   {
      HandlaggningPutResponse response = ImmutableHandlaggningPutResponse.builder()
            .uppgift(request.uppgift())
            .build();
      LOGGER.info("HandlaggningPutResponse update: {}", request);
      return response;
   }

   @Override
   public HandlaggningPatchResponse patchHandlaggning(HandlaggningPatchRequest request)
   {
      HandlaggningEntity handlaggning = handlaggningRepository.findById(request.handlaggningId()).orElse(null);
      if (handlaggning == null)
      {
         return null;
      }

      YrkandeEntity yrkande = handlaggning.yrkande();

      var updatedErsattning = new ArrayList<ErsattningEntity>();
      for (var ersattning : yrkande.ersattning())
      {
         final var ersattningsId = ersattning.id();

         var updateErsattning = request.updateErsattning().stream().filter(e -> e.ersattningsId().equals(ersattningsId))
               .findFirst().orElse(null);
         if (updateErsattning != null)
         {
            ersattning = ImmutableErsattningEntity.builder().from(ersattning)
                  .beslutsutfall(enumMapper.toBeslutsutfallEntity(updateErsattning.beslutsutfall()))
                  .avslagsanledning(updateErsattning.avslagsanledning()).build();
         }

         updatedErsattning.add(ersattning);
      }

      YrkandeEntity updatedYrkande = ImmutableYrkandeEntity.builder().from(yrkande)
            .ersattning(updatedErsattning).build();
      HandlaggningEntity updatedHandlaggning = ImmutableHandlaggningEntity.builder().from(handlaggning)
            .yrkande(updatedYrkande).build();
      handlaggningRepository.save(updatedHandlaggning);

      return ImmutableHandlaggningPatchResponse.builder()
            .handlaggning(mapper.toHandlaggningDTO(updatedHandlaggning)).build();
   }

   @Override
   public void sendHandlaggningDoneMessage(UUID handlaggningID)
   {
      producer.sendHandlaggningDone(handlaggningID);
   }

}
