package se.fk.github.rimfrost.handlaggning.logic.entity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.immutables.value.Value;

import jakarta.annotation.Nullable;
import se.fk.github.rimfrost.handlaggning.logic.enums.Avsikt;
import se.fk.github.rimfrost.handlaggning.logic.enums.Yrkandestatus;

@Value.Immutable
public interface YrkandeEntity
{
   UUID id();

   UUID erbjudandeId();

   int version();

   OffsetDateTime yrkandedatum();

   OffsetDateTime yrkandeFrom();

   OffsetDateTime yrkandeTom();

   Yrkandestatus yrkandestatus();

   Avsikt avsikt();

   @Nullable
   String andringsorsak();

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
