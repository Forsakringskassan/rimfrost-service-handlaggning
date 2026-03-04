package se.fk.github.rimfrost.yrkande.logic.dto;

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
}
