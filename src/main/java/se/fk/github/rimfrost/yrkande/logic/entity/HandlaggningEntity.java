package se.fk.github.rimfrost.yrkande.logic.entity;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.immutables.value.Value;
import jakarta.annotation.Nullable;

@Value.Immutable
public interface HandlaggningEntity
{
   UUID id();

   YrkandeEntity yrkande();

   String version();

   UUID processinstansId();

   OffsetDateTime skapadTS();

   @Nullable
   OffsetDateTime avslutadTS();

   HandlaggningspecifikationEntity handlaggningspecifikation();
}
