package se.fk.github.rimfrost.handlaggning.logic.service.impl;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.github.rimfrost.handlaggning.logic.entity.*;
import se.fk.github.rimfrost.handlaggning.logic.repository.ProduceratResultatRepository;
import se.fk.github.rimfrost.handlaggning.logic.repository.YrkandeRepository;
import se.fk.github.rimfrost.handlaggning.logic.service.YrkandeService;
import se.fk.github.rimfrost.handlaggning.logic.util.LogicMapper;

@ApplicationScoped
public class YrkandeServiceImpl implements YrkandeService
{
   private static final Logger LOGGER = LoggerFactory.getLogger(YrkandeService.class);

   @Inject
   private YrkandeRepository yrkandeRepository;

   @Inject
   private ProduceratResultatRepository produceratResultatRepository;

   @Inject
   private LogicMapper mapper;

   @Override
   public YrkandeCreateResponse createYrkande(YrkandeCreateRequest request)
   {
      var individYrkandeRoller = request.individYrkandeRoller().stream()
            .map(e -> (IndividYrkandeRollEntity) ImmutableIndividYrkandeRollEntity.builder()
                  .individ(mapper.toIdtypEntity(e.individ()))
                  .yrkandeRollId(e.yrkandeRollId())
                  .build())
            .toList();

      var produceradeResultat = request.produceradeResultat().stream()
            .map(e -> (ProduceratResultatEntity) ImmutableProduceratResultatEntity.builder()
                  .id(UUID.randomUUID())
                  .version(1)
                  .yrkandeStatus("e27da561-a8db-4513-8272-ef652b097b16") // YRKAT
                  .franOchMed(e.franOchMed())
                  .tillOchMed(e.tillOchMed())
                  .typ(e.typ())
                  .data(e.data())
                  .build())
            .toList();

      var yrkandeEntity = ImmutableYrkandeEntity.builder()
            .id(UUID.randomUUID())
            .version(1)
            .erbjudandeId(request.erbjudandedId())
            .yrkandeFrom(request.yrkandeFrom())
            .yrkandeTom(request.yrkandeTom())
            .yrkandedatum(OffsetDateTime.now())
            .yrkandestatus("e27da561-a8db-4513-8272-ef652b097b16") // YRKAT
            .avsikt("dae2ffc3-07c8-4686-a3d5-58bc942dfe06") // NY
            .individYrkandeRoll(individYrkandeRoller)
            .produceradeResultat(produceradeResultat)
            .build();

      produceratResultatRepository.save(produceradeResultat);
      yrkandeRepository.save(yrkandeEntity);

      return ImmutableYrkandeCreateResponse.builder()
            .yrkande(mapper.toYrkandeDTO(yrkandeEntity))
            .build();
   }

   @Override
   public YrkandeGetResponse getById(YrkandeGetRequest yrkandeGetRequest)
   {
      var yrkandeEntity = yrkandeRepository.findById(yrkandeGetRequest.yrkandeId()).orElseThrow();
      return ImmutableYrkandeGetResponse.builder()
            .yrkande(mapper.toYrkandeDTO(yrkandeEntity))
            .build();
   }

}
