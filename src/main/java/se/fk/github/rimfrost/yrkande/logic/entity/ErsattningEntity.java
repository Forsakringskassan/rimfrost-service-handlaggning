package se.fk.github.rimfrost.yrkande.logic.entity;

import java.util.UUID;

import org.immutables.value.Value;

import jakarta.annotation.Nullable;
import se.fk.github.rimfrost.yrkande.logic.enums.BeloppstypEntity;
import se.fk.github.rimfrost.yrkande.logic.enums.BerakningsgrundEntity;
import se.fk.github.rimfrost.yrkande.logic.enums.BeslutsutfallEntity;
import se.fk.github.rimfrost.yrkande.logic.enums.ErsattningstypEntity;
import se.fk.github.rimfrost.yrkande.logic.enums.PeriodiseringEntity;

@Value.Immutable
public interface ErsattningEntity
{
   UUID id();

   Integer belopp();

   BerakningsgrundEntity berakningsgrund();

   BeloppstypEntity beloppstyp();

   ErsattningstypEntity ersattningstyp();

   PeriodiseringEntity periodisering();

   Integer omfattning();

   BeslutsutfallEntity beslutsutfall();

   @Nullable
   String avslagsanledning();

   ProduceratResultatEntity produceratResultat();
}
