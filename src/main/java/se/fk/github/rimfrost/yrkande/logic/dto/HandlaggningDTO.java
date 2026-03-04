package se.fk.github.rimfrost.yrkande.logic.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.immutables.value.Value;
import jakarta.annotation.Nullable;

@Value.Immutable
public interface HandlaggningDTO
{
   UUID id();

   YrkandeDTO yrkande();

   String version();

   UUID processinstansId();

   OffsetDateTime skapadTS();

   @Nullable
   OffsetDateTime avslutadTS();

   HandlaggningspecifikationDTO handlaggningspecifikation();
}
