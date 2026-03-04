package se.fk.github.rimfrost.yrkande.logic.entity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.immutables.value.Value;
import se.fk.github.rimfrost.yrkande.logic.enums.AvsiktEntity;
import se.fk.github.rimfrost.yrkande.logic.enums.YrkandestatusEntity;

@Value.Immutable
public interface YrkandeEntity
{
   UUID id();

   String formanstyp();

   String version();

   OffsetDateTime yrkandedatum();

   YrkandestatusEntity yrkandestatus();

   PeriodEntity period();

   AvsiktEntity avsikt();

   String andringsorsak();

   @Value.Default
   default List<YrkanderollEntity> yrkanderoll()
   {
      return List.of();
   }

   @Value.Default
   default List<ErsattningEntity> ersattning()
   {
      return List.of();
   }
}
