package se.fk.github.rimfrost.handlaggning.logic.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.immutables.value.Value;
import jakarta.annotation.Nullable;

@Value.Immutable
public interface HandlaggningDTO
{
   UUID id();

   YrkandeDTO yrkande();

   Integer version();

   UUID handlaggningspecifikationId();

   OffsetDateTime skapadTS();

   @Nullable
   OffsetDateTime avslutadTS();

   String handlaggningIdTyp();

   @Nullable
   String handlaggningIdVarde();

}
