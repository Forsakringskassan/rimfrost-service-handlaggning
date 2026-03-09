package se.fk.github.rimfrost.handlaggning.logic.dto;

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

   ProduktvariantDTO produktvariant();

   PeriodiseringDTO periodisering();

   Integer omfattning();

   BeslutsutfallDTO beslutsutfall();

   @Nullable
   String avslagsanledning();

   ProduceratResultatDTO produceratResultat();
}
