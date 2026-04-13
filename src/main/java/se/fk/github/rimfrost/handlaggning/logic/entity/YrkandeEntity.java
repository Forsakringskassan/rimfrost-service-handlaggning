package se.fk.github.rimfrost.handlaggning.logic.entity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface YrkandeEntity
{
   UUID id();

   String erbjudandeId();

   int version();

   OffsetDateTime yrkandedatum();

   OffsetDateTime yrkandeFrom();

   OffsetDateTime yrkandeTom();

   String yrkandestatus();

   String avsikt();

   @Value.Default
   default List<IndividYrkandeRollEntity> individYrkandeRoll()
   {
      return List.of();
   }

   @Value.Default
   default List<ProduceratResultatEntity> produceradeResultat()
   {
      return List.of();
   }
}
