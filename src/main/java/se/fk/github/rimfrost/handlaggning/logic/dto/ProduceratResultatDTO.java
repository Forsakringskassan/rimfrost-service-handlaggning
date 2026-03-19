package se.fk.github.rimfrost.handlaggning.logic.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.immutables.value.Value;

import jakarta.annotation.Nullable;
import se.fk.github.rimfrost.handlaggning.logic.enums.Yrkandestatus;

@Value.Immutable
public interface ProduceratResultatDTO
{
   UUID id();

   Integer version();

   OffsetDateTime franOchMed();

   OffsetDateTime tillOchMed();

   Yrkandestatus yrkandestatus();

   @Nullable
   String avslagsanledning();

   String typ();

   String data();

}
