package se.fk.github.rimfrost.handlaggning.logic.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.immutables.value.Value;
import jakarta.annotation.Nullable;

@Value.Immutable
public interface UppgiftDTO
{

   UUID uppgiftId();

   int version();

   OffsetDateTime skapadTS();

   @Nullable
   OffsetDateTime utfordTS();

   @Nullable
   OffsetDateTime planeradTillTS();

   @Nullable
   IdtypDTO utforare();

   UUID aktivitetId();

   @Nullable
   String uppgiftStatus();

   @Nullable
   String kommentar();

   String fssaInformation();

   UppgiftspecifikationDTO uppgiftSpecifikation();

   @Nullable
   RegelutfallDTO regelutfall();

   @Value.Default
   default List<UppgiftsdataDTO> underlag()
   {
      return List.of();
   }

   @Value.Default
   default List<UppgiftsdatakopplingDTO> resultat()
   {
      return List.of();
   }

}
