package se.fk.github.rimfrost.handlaggning.logic.entity;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.immutables.value.Value;

import edu.umd.cs.findbugs.annotations.Nullable;
import se.fk.github.rimfrost.handlaggning.logic.enums.Yrkandestatus;

@Value.Immutable
public interface ProduceratResultatEntity
{
   UUID id();

   int version();

   OffsetDateTime franOchMed();

   OffsetDateTime tillOchMed();

   Yrkandestatus yrkandeStatus();

   @Nullable
   String avslagsanledning();

   String typ();

   String data();
}
