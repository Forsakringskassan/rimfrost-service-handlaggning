package se.fk.github.rimfrost.handlaggning.logic.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.immutables.value.Value;

import jakarta.annotation.Nullable;

@Value.Immutable
public interface ProduceratResultatDTO
{
   @Nullable
   UUID id();

   @Nullable
   Integer version();

   OffsetDateTime franOchMed();

   OffsetDateTime tillOchMed();

   String typ();

   String data();

}
