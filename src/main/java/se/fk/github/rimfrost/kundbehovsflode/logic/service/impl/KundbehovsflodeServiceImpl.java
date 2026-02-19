package se.fk.github.rimfrost.kundbehovsflode.logic.service.impl;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.rimfrost.kundbehovsflode.integration.KafkaProducer;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableKundbehovsflodeCreateResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableKundbehovsflodeGetResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableKundbehovsflodePutResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableKundbehovsflodePatchResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeCreateRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeCreateResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeGetRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeGetResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePutRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePutResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePatchRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePatchResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.entity.KundbehovEntity;
import se.fk.github.rimfrost.kundbehovsflode.logic.entity.KundbehovsflodeEntity;
import se.fk.github.rimfrost.kundbehovsflode.logic.entity.KundbehovsflodespecifikationEntity;
import se.fk.github.rimfrost.kundbehovsflode.logic.entity.ErsattningEntity;
import se.fk.github.rimfrost.kundbehovsflode.logic.entity.ImmutableErsattningEntity;
import se.fk.github.rimfrost.kundbehovsflode.logic.entity.ImmutableKundbehovEntity;
import se.fk.github.rimfrost.kundbehovsflode.logic.entity.ImmutableKundbehovsflodeEntity;
import se.fk.github.rimfrost.kundbehovsflode.logic.entity.ImmutableKundbehovsflodespecifikationEntity;
import se.fk.github.rimfrost.kundbehovsflode.logic.repository.KundbehovRepository;
import se.fk.github.rimfrost.kundbehovsflode.logic.repository.KundbehovsflodeRepository;
import se.fk.github.rimfrost.kundbehovsflode.logic.service.KundbehovsflodeService;
import se.fk.github.rimfrost.kundbehovsflode.logic.util.LogicEnumMapper;
import se.fk.github.rimfrost.kundbehovsflode.logic.util.LogicMapper;

@ApplicationScoped
public class KundbehovsflodeServiceImpl implements KundbehovsflodeService
{
   @Inject
   KundbehovsflodeRepository kundbehovsflodeRepository;

   @Inject
   KundbehovRepository kundbehovRepository;

   @Inject
   KafkaProducer producer;

   @Inject
   private LogicMapper mapper;

   @Inject
   LogicEnumMapper enumMapper;

   private static final Logger LOGGER = LoggerFactory.getLogger(KundbehovsflodeServiceImpl.class);

   @Override
   public KundbehovsflodeCreateResponse createKundbehovsflode(KundbehovsflodeCreateRequest request)
   {
      // KundbehovGetRequest kundbehovGetRequest = ImmutableKundbehovGetRequest.builder()
      //     .kundbehovId(request.kundbehovId())
      //     .build();
      // KundbehovGetResponse kunbdehovGetResponse = kundbehovService.getById(kundbehovGetRequest);

      KundbehovEntity kundbehovEntity = kundbehovRepository.findById(request.kundbehovId()).orElse(null);

      // Innehåller ingen uppgiftspecifikation just nu
      KundbehovsflodespecifikationEntity kundbehovsflodespecifikationEntity = ImmutableKundbehovsflodespecifikationEntity
            .builder()
            .id(UUID.randomUUID())
            .version("1.0")
            .bpmn("VAH") // TODO: Uppdatera detta till något annat baserat på kundbehovets typ?
            .namn("Vård av hudsjur") // TODO: Uppdatera detta till något annat baserat på kundbehovets typ?
            .beskrivning("Kollar om du har rätt för ersättning för vård av husdjur")
            .build();

      KundbehovsflodeEntity kundbehovsflodeEntity = ImmutableKundbehovsflodeEntity.builder()
            .id(UUID.randomUUID())
            .kundbehov(kundbehovEntity)
            .version("1.0")
            .processinstansId(UUID.randomUUID()) // TODO: Fixa denna så att vi startar en ny process och stoppar den här??
            .skapadTS(OffsetDateTime.now())
            .kundbehovsspecifikation(kundbehovsflodespecifikationEntity)
            .build();

      kundbehovsflodeRepository.save(kundbehovsflodeEntity);
      producer.sendVahRequestMessage(kundbehovsflodeEntity.id());

      KundbehovsflodeCreateResponse response = ImmutableKundbehovsflodeCreateResponse.builder()
            .kundbehovsflode(mapper.toKundbehovsflodeDTO(kundbehovsflodeEntity))
            .build();

      return response;
   }

   @Override
   public KundbehovsflodeGetResponse getKundbehovsflode(KundbehovsflodeGetRequest request)
   {
      KundbehovsflodeEntity kundbehovsflodeEntity = kundbehovsflodeRepository.findById(request.kundbehovsflodeId()).orElse(null);
      if (kundbehovsflodeEntity == null)
      {
         return null;
      }

      KundbehovsflodeGetResponse response = ImmutableKundbehovsflodeGetResponse.builder()
            .kundbehovsflode(mapper.toKundbehovsflodeDTO(kundbehovsflodeEntity))
            .build();

      return response;
   }

   @Override
   public KundbehovsflodePutResponse putKundbehovsflode(KundbehovsflodePutRequest request)
   {
      KundbehovsflodePutResponse response = ImmutableKundbehovsflodePutResponse.builder()
            .uppgift(request.uppgift())
            .build();
      LOGGER.info("KundbehovsflodePutRequest update: {}", request);
      return response;
   }

   @Override
   public KundbehovsflodePatchResponse patchKundbehovsflode(KundbehovsflodePatchRequest request)
   {
      KundbehovsflodeEntity kundbehovsflode = kundbehovsflodeRepository.findById(request.kundbehovsflodeId()).orElse(null);
      if (kundbehovsflode == null)
      {
         return null;
      }

      KundbehovEntity kundbehov = kundbehovsflode.kundbehov();

      var updatedErsattning = new ArrayList<ErsattningEntity>();
      for (var ersattning : kundbehov.ersattning())
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

      KundbehovEntity updatedKundbehov = ImmutableKundbehovEntity.builder().from(kundbehov)
            .ersattning(updatedErsattning).build();
      KundbehovsflodeEntity updatedKundbehovsflode = ImmutableKundbehovsflodeEntity.builder()
            .kundbehov(updatedKundbehov).build();
      kundbehovsflodeRepository.save(updatedKundbehovsflode);

      return ImmutableKundbehovsflodePatchResponse.builder()
            .kundbehovsflode(mapper.toKundbehovsflodeDTO(updatedKundbehovsflode)).build();
   }

   @Override
   public void sendKundbehovsflodeDoneMessage(UUID kundbehovsflodeID)
   {
      producer.sendKundbehovsflodeDone(kundbehovsflodeID);
   }

}
