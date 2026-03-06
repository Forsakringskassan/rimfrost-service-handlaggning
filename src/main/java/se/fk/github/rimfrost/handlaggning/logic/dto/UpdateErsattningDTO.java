package se.fk.github.rimfrost.handlaggning.logic.dto;

import java.util.UUID;
import org.immutables.value.Value;
import jakarta.annotation.Nullable;

@Value.Immutable
public interface UpdateErsattningDTO
{
   UUID ersattningsId();

   BeslutsutfallDTO beslutsutfall();

   @Nullable
   String avslagsanledning();

   @Nullable
   ErsattningsstatusDTO ersattningsStatus();
}
