package se.fk.github.rimfrost.handlaggning.logic.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import se.fk.github.rimfrost.handlaggning.logic.enums.Avsikt;
import se.fk.github.rimfrost.handlaggning.logic.enums.Yrkandestatus;

@Value.Immutable
public interface YrkandeDTO
{
   UUID id();

   UUID erbjudandeId();

   Integer version();

   OffsetDateTime yrkandedatum();

   OffsetDateTime yrkandeFrom();

   OffsetDateTime yrkandeTom();

   Yrkandestatus yrkandestatus();

   Avsikt avsikt();

   @Nullable
   BeslutDTO beslut();

   @Value.Default
   default List<IndividYrkandeRollDTO> individYrkandeRoll()
   {
      return List.of();
   }

   @Value.Default
   default List<ProduceratResultatDTO> produceradeResultat()
   {
      return List.of();
   }
}
