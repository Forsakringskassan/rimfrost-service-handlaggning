package se.fk.github.rimfrost.handlaggning.logic.service.impl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.github.rimfrost.handlaggning.logic.entity.*;
import se.fk.github.rimfrost.handlaggning.logic.enums.*;
import se.fk.github.rimfrost.handlaggning.logic.repository.IndividYrkandeRollRepository;
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
   private IndividYrkandeRollRepository individYrkandeRollRepository;

   @Inject
   private ProduceratResultatRepository produceratResultatRepository;

   @Inject
   private LogicMapper mapper;

   @Override
   public YrkandeCreateResponse createYrkande(YrkandeCreateRequest request)
   {
      var individYrkandeRoller = request.individYrkandeRoller().stream()
            .map(e -> (IndividYrkandeRollEntity) ImmutableIndividYrkandeRollEntity.builder()
                  .id(UUID.randomUUID())
                  .individId(e.individId())
                  .yrkandeRollId(e.yrkandeRollId())
                  .build())
            .toList();

      var produceradeResultat = request.produceradeResultat().stream()
            .map(e -> (ProduceratResultatEntity) ImmutableProduceratResultatEntity.builder()
                  .id(UUID.randomUUID())
                  .version(1)
                  .yrkandeStatus(Yrkandestatus.YRKAT)
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
            .yrkandestatus(Yrkandestatus.YRKAT)
            .avsikt(Avsikt.NY)
            .individYrkandeRoll(individYrkandeRoller)
            .produceradeResultat(produceradeResultat)
            .build();

      individYrkandeRollRepository.save(individYrkandeRoller);
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
