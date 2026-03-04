package se.fk.github.rimfrost.yrkande.logic.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.immutables.value.Value;
import jakarta.annotation.Nullable;

@Value.Immutable
public interface UppgiftDTO
{

   UUID uppgiftId();

   String version();

   OffsetDateTime skapadTs();

   @Nullable
   OffsetDateTime utfordTs();

   @Nullable
   OffsetDateTime planeradTs();

   @Nullable
   UUID utforarId();

   UUID handlaggningId();

   UppgiftStatusDTO uppgiftStatus();

   FSSAInformationDTO fssaInformation();

   UppgiftspecifikationDTO uppgiftSpecifikation();

   List<UnderlagDTO> underlag();

}
