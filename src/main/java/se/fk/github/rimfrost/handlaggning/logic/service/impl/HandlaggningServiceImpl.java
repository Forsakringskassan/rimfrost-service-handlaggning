package se.fk.github.rimfrost.handlaggning.logic.service.impl;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.rimfrost.handlaggning.integration.KafkaProducer;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.github.rimfrost.handlaggning.logic.entity.*;
import se.fk.github.rimfrost.handlaggning.logic.repository.ErbjudandeRepository;
import se.fk.github.rimfrost.handlaggning.logic.repository.YrkandeRepository;
import se.fk.github.rimfrost.handlaggning.logic.repository.HandlaggningRepository;
import se.fk.github.rimfrost.handlaggning.logic.service.HandlaggningService;
import se.fk.github.rimfrost.handlaggning.logic.util.LogicEnumMapper;
import se.fk.github.rimfrost.handlaggning.logic.util.LogicMapper;

@ApplicationScoped
public class HandlaggningServiceImpl implements HandlaggningService
{
   @Inject
   HandlaggningRepository handlaggningRepository;

   @Inject
   YrkandeRepository yrkandeRepository;

   @Inject
   ErbjudandeRepository erbjudandeRepository;

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

      YrkandeEntity yrkandeEntity = yrkandeRepository.findById(request.yrkandeId()).orElseThrow();
      // TODO: Fetch this information from correct location
      var erbjudandeFlowInfo = erbjudandeRepository.getErbjudandeFlowInfoByErbjudandetyp(yrkandeEntity.formanstyp())
            .orElseThrow();

      // Innehåller ingen uppgiftspecifikation just nu
      HandlaggningspecifikationEntity handlaggningspecifikationEntity = ImmutableHandlaggningspecifikationEntity
            .builder()
            .id(UUID.randomUUID())
            .version("1.0")
            .bpmn(erbjudandeFlowInfo.bpmn())
            .namn(erbjudandeFlowInfo.namn())
            .beskrivning(erbjudandeFlowInfo.beskrivning())
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
         throw new InternalError("Could not find handlaggning with ID: " + request.handlaggningId());
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
            var ersattningBuilder = ImmutableErsattningEntity.builder().from(ersattning);
            if (updateErsattning.beslutsutfall() != null)
            {
               ersattningBuilder.beslutsutfall(enumMapper.toBeslutsutfallEntity(updateErsattning.beslutsutfall()));
            }
            if (updateErsattning.avslagsanledning() != null)
            {
               ersattningBuilder.avslagsanledning(updateErsattning.avslagsanledning());
            }
            if (updateErsattning.ersattningsStatus() != null)
            {
               ersattningBuilder
                     .produceratResultat(ImmutableProduceratResultatEntity.builder().from(ersattning.produceratResultat())
                           .status(enumMapper.toErsattningsstatusEntity(updateErsattning.ersattningsStatus())).build());
            }
            ersattning = ersattningBuilder.build();
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
