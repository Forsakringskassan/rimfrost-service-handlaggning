package se.fk.github.rimfrost.handlaggning.logic.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.immutables.value.Value;
import jakarta.annotation.Nullable;

@Value.Immutable
public interface UppgiftDTO
{

   UUID uppgiftId();

   int version();

   OffsetDateTime skapadTs();

   @Nullable
   OffsetDateTime utfordTs();

   @Nullable
   OffsetDateTime planeradTs();

   @Nullable
   UUID utforarId();

   UUID handlaggningId();

   UUID aktivitetId();

   UppgiftStatusDTO uppgiftStatus();

   FSSAInformationDTO fssaInformation();

   UppgiftspecifikationDTO uppgiftSpecifikation();

}
