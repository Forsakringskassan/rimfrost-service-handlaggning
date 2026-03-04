package se.fk.github.rimfrost.yrkande.logic.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface YrkandeDTO
{
   UUID id();

   String formanstyp();

   String version();

   OffsetDateTime yrkandedatum();

   YrkandestatusDTO yrkandestatus();

   PeriodDTO period();

   AvsiktDTO avsikt();

   String andringsorsak();

   @Value.Default
   default List<YrkanderollDTO> yrkanderoll()
   {
      return List.of();
   }

   @Value.Default
   default List<ErsattningDTO> ersattning()
   {
      return List.of();
   }
}
