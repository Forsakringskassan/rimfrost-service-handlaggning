package se.fk.github.rimfrost.handlaggning.logic.entity;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface ProduceratResultatEntity
{
   UUID id();

   int version();

   OffsetDateTime franOchMed();

   OffsetDateTime tillOchMed();

   String typ();

   String data();
}
