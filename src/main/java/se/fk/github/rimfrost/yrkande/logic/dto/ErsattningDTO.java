package se.fk.github.rimfrost.yrkande.logic.dto;

import java.util.UUID;

import org.immutables.value.Value;

import jakarta.annotation.Nullable;

@Value.Immutable
public interface ErsattningDTO
{
   UUID id();

   Integer belopp();

   BerakningsgrundDTO berakningsgrund();

   BeloppstypDTO beloppstyp();

   ErsattningstypDTO ersattningstyp();

   PeriodiseringDTO periodisering();

   Integer omfattning();

   BeslutsutfallDTO beslutsutfall();

   @Nullable
   String avslagsanledning();

   ProduceratResultatDTO produceratResultat();
}
