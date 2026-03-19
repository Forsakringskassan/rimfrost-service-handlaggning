package se.fk.github.rimfrost.handlaggning.logic.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface YrkandeDTO
{
   UUID id();

   UUID erbjudandeId();

   Integer version();

   OffsetDateTime yrkandedatum();

   OffsetDateTime yrkandeFrom();

   OffsetDateTime yrkandeTom();

   YrkandestatusDTO yrkandestatus();

   AvsiktDTO avsikt();

   String andringsorsak();

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
