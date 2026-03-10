package se.fk.github.rimfrost.handlaggning.logic.entity;

import java.util.UUID;

import org.immutables.value.Value;

import jakarta.annotation.Nullable;
import se.fk.github.rimfrost.handlaggning.logic.enums.BeloppstypEntity;
import se.fk.github.rimfrost.handlaggning.logic.enums.BerakningsgrundEntity;
import se.fk.github.rimfrost.handlaggning.logic.enums.BeslutsutfallEntity;
import se.fk.github.rimfrost.handlaggning.logic.enums.PeriodiseringEntity;

@Value.Immutable
public interface ErsattningEntity
{
   UUID id();

   Integer belopp();

   BerakningsgrundEntity berakningsgrund();

   BeloppstypEntity beloppstyp();

   ProduktvariantEntity produktvariant();

   PeriodiseringEntity periodisering();

   Integer omfattning();

   BeslutsutfallEntity beslutsutfall();

   @Nullable
   String avslagsanledning();

   ProduceratResultatEntity produceratResultat();
}
