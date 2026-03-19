package se.fk.github.rimfrost.handlaggning.logic.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.github.rimfrost.handlaggning.logic.entity.*;

@ApplicationScoped
public class LogicMapper
{

   @Inject
   LogicEnumMapper enumMapper;

   public YrkandeDTO toYrkandeDTO(YrkandeEntity yrkandeEntity)
   {
      return ImmutableYrkandeDTO.builder()
            .id(yrkandeEntity.id())
            .erbjudandeId(yrkandeEntity.erbjudandeId())
            .version(yrkandeEntity.version())
            .yrkandedatum(yrkandeEntity.yrkandedatum())
            .yrkandeFrom(yrkandeEntity.yrkandeFrom())
            .yrkandeTom(yrkandeEntity.yrkandeTom())
            .yrkandestatus(enumMapper.toYrkandestatusDTO(yrkandeEntity.yrkandestatus()))
            .avsikt(enumMapper.toAvsiktDTO(yrkandeEntity.avsikt()))
            .andringsorsak(yrkandeEntity.andringsorsak())
            .individYrkandeRoll(yrkandeEntity.individYrkandeRoll()
                  .stream()
                  .map(this::toIndividYrkandeRollDTO)
                  .toList())
            .produceradeResultat(yrkandeEntity.produceradeResultat()
                  .stream()
                  .map(this::toProduceratResultatDTO)
                  .toList())
            .build();
   }

   public IndividYrkandeRollDTO toIndividYrkandeRollDTO(IndividYrkandeRollEntity individYrkandeRollEntity)
   {
      return ImmutableIndividYrkandeRollDTO.builder()
            .individId(individYrkandeRollEntity.individId())
            .yrkandeRollId(individYrkandeRollEntity.yrkandeRollId())
            .build();
   }

   public ProduceratResultatDTO toProduceratResultatDTO(ProduceratResultatEntity produceratResultatEntity)
   {
      return ImmutableProduceratResultatDTO.builder()
            .id(produceratResultatEntity.id())
            .version(produceratResultatEntity.version())
            .franOchMed(produceratResultatEntity.franOchMed())
            .tillOchMed(produceratResultatEntity.tillOchMed())
            .typ(produceratResultatEntity.typ())
            .data(produceratResultatEntity.data())
            .build();
   }

   public HandlaggningDTO toHandlaggningDTO(HandlaggningEntity handlaggningEntity)
   {
      return ImmutableHandlaggningDTO.builder()
            .id(handlaggningEntity.id())
            .yrkande(toYrkandeDTO(handlaggningEntity.yrkande()))
            .version(handlaggningEntity.version())
            .processinstansId(handlaggningEntity.processinstansId())
            .skapadTS(handlaggningEntity.skapadTS())
            .avslutadTS(handlaggningEntity.avslutadTS())
            .handlaggningspecifikationId(handlaggningEntity.handlaggningspecifikationId())
            .build();
   }

   public YrkandeEntity toYrkandeEntity(YrkandeDTO yrkandeDTO)
   {
      return ImmutableYrkandeEntity.builder()
            .id(yrkandeDTO.id())
            .erbjudandeId(yrkandeDTO.erbjudandeId())
            .version(yrkandeDTO.version())
            .yrkandedatum(yrkandeDTO.yrkandedatum())
            .yrkandeFrom(yrkandeDTO.yrkandeFrom())
            .yrkandeTom(yrkandeDTO.yrkandeTom())
            .yrkandestatus(enumMapper.toYrkandestatusEntity(yrkandeDTO.yrkandestatus()))
            .avsikt(enumMapper.toAvsiktEntity(yrkandeDTO.avsikt()))
            .andringsorsak(yrkandeDTO.andringsorsak())
            .individYrkandeRoll(
                  yrkandeDTO.individYrkandeRoll()
                        .stream()
                        .map(this::toIndividYrkandeRollEntity)
                        .toList())
            .produceradeResultat(
                  yrkandeDTO.produceradeResultat()
                        .stream()
                        .map(this::toProduceratResultatEntity)
                        .toList())
            .build();
   }

   public IndividYrkandeRollEntity toIndividYrkandeRollEntity(IndividYrkandeRollDTO yrkanderollDTO)
   {
      return ImmutableIndividYrkandeRollEntity.builder()
            .individId(yrkanderollDTO.individId())
            .yrkandeRollId(yrkanderollDTO.yrkandeRollId())
            .build();
   }

   public ProduceratResultatEntity toProduceratResultatEntity(ProduceratResultatDTO produceratResultatDTO)
   {
      return ImmutableProduceratResultatEntity.builder()
            .id(produceratResultatDTO.id())
            .version(produceratResultatDTO.version())
            .franOchMed(produceratResultatDTO.franOchMed())
            .tillOchMed(produceratResultatDTO.tillOchMed())
            .typ(produceratResultatDTO.typ())
            .data(produceratResultatDTO.data())
            .build();
   }

   public HandlaggningEntity toHandlaggningEntity(HandlaggningDTO handlaggningDTO)
   {
      return ImmutableHandlaggningEntity.builder()
            .id(handlaggningDTO.id())
            .yrkande(toYrkandeEntity(handlaggningDTO.yrkande()))
            .version(handlaggningDTO.version())
            .processinstansId(handlaggningDTO.processinstansId())
            .skapadTS(handlaggningDTO.skapadTS())
            .avslutadTS(handlaggningDTO.avslutadTS())
            .handlaggningspecifikationId(handlaggningDTO.handlaggningspecifikationId())
            .build();
   }

}
