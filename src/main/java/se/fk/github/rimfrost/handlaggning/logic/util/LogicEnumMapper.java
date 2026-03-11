package se.fk.github.rimfrost.handlaggning.logic.util;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.github.rimfrost.handlaggning.logic.dto.YrkandestatusDTO;
import se.fk.github.rimfrost.handlaggning.logic.entity.ProduktvariantEntity;
import se.fk.github.rimfrost.handlaggning.logic.entity.RollEntity;
import se.fk.github.rimfrost.handlaggning.logic.enums.*;
import se.fk.github.rimfrost.handlaggning.logic.enums.YrkandestatusEntity;

@ApplicationScoped
public class LogicEnumMapper
{
   public AvsiktEntity toAvsiktEntity(AvsiktDTO avsiktDTO) {
        if (avsiktDTO == null) {
            return null;
        }

        AvsiktEntity out;
        switch (avsiktDTO) {
            case ANDRING -> out = AvsiktEntity.ANDRING;
            case ATERTAGEN -> out = AvsiktEntity.ATERTAGEN;
            case BORTTAG -> out = AvsiktEntity.BORTTAG;
            case NY -> out = AvsiktEntity.NY;
            default -> { return null; }
        }
        return out;
    }

   public AvsiktDTO toAvsiktDTO(AvsiktEntity avsikt) {
        if (avsikt == null) {
            return null;
        }

        AvsiktDTO out;
        switch (avsikt) {
            case ANDRING -> out = AvsiktDTO.ANDRING;
            case ATERTAGEN -> out = AvsiktDTO.ATERTAGEN;
            case BORTTAG -> out = AvsiktDTO.BORTTAG;
            case NY -> out = AvsiktDTO.NY;
            default -> { return null; }
        }
        return out;
    }

   public BeloppstypEntity toBeloppstypEntity(BeloppstypDTO beloppstypDTO) {
        if (beloppstypDTO == null) {
            return null;
        }

        BeloppstypEntity out;
        switch (beloppstypDTO) {
            case GRUNDBELOPP -> out = BeloppstypEntity.GRUNDBELOPP;
            case INKOMSTBASERAD -> out = BeloppstypEntity.INKOMSTBASERAD;
            default -> { return null; }
        }
        return out;
    }

   public BeloppstypDTO toBeloppstypDTO(BeloppstypEntity beloppstyp) {
        if (beloppstyp == null) {
            return null;
        }

        BeloppstypDTO out;
        switch (beloppstyp) {
            case GRUNDBELOPP -> out = BeloppstypDTO.GRUNDBELOPP;
            case INKOMSTBASERAD -> out = BeloppstypDTO.INKOMSTBASERAD;
            default -> { return null; }
        }
        return out;
    }

   public BerakningsgrundEntity toBerakningsgrundEntity(BerakningsgrundDTO dto) {
        if (dto == null) {
            return null;
        }

        BerakningsgrundEntity out;
        switch (dto) {
            case FASTBELOPP -> out = BerakningsgrundEntity.FASTBELOPP;
            case LON -> out = BerakningsgrundEntity.LON;
            default -> { return null; }
        }
        return out;
    }

   public BerakningsgrundDTO toBerakningsgrundDTO(BerakningsgrundEntity entity) {
        if (entity == null) {
            return null;
        }

        BerakningsgrundDTO out;
        switch (entity) {
            case FASTBELOPP -> out = BerakningsgrundDTO.FASTBELOPP;
            case LON -> out = BerakningsgrundDTO.LON;
            default -> { return null; }
        }
        return out;
    }

   public BeslutsutfallEntity toBeslutsutfallEntity(BeslutsutfallDTO dto) {
        if (dto == null) {
            return null;
        }

        BeslutsutfallEntity out;
        switch (dto) {
            case FU -> out = BeslutsutfallEntity.FU;
            case JA -> out = BeslutsutfallEntity.JA;
            case NEJ -> out = BeslutsutfallEntity.NEJ;
            default -> { return null; }
        }
        return out;
    }

   public BeslutsutfallDTO toBeslutsutfallDTO(BeslutsutfallEntity entity) {
        if (entity == null) {
            return null;
        }

        BeslutsutfallDTO out;
        switch (entity) {
            case FU -> out = BeslutsutfallDTO.FU;
            case JA -> out = BeslutsutfallDTO.JA;
            case NEJ -> out = BeslutsutfallDTO.NEJ;
            default -> { return null; }
        }
        return out;
    }

   public ErsattningsstatusEntity toErsattningsstatusEntity(ErsattningsstatusDTO dto) {
        if (dto == null) {
            return null;
        }

        ErsattningsstatusEntity out;
        switch (dto) {
            case FASTSTALLT -> out = ErsattningsstatusEntity.FASTSTALLT;
            case FASTSTALLT_UNDER_UTREDNING -> out = ErsattningsstatusEntity.FASTSTALLT_UNDER_UTREDNING;
            case PLANERAT -> out = ErsattningsstatusEntity.PLANERAT;
            case UNDER_UTREDNING -> out = ErsattningsstatusEntity.UNDER_UTREDNING;
            case YRKAT -> out = ErsattningsstatusEntity.YRKAT;
            default -> { return null; }
        }
        return out;
    }

   public ErsattningsstatusDTO toErsattningsstatusDTO(ErsattningsstatusEntity entity) {
        if (entity == null) {
            return null;
        }

        ErsattningsstatusDTO out;
        switch (entity) {
            case FASTSTALLT -> out = ErsattningsstatusDTO.FASTSTALLT;
            case FASTSTALLT_UNDER_UTREDNING -> out = ErsattningsstatusDTO.FASTSTALLT_UNDER_UTREDNING;
            case PLANERAT -> out = ErsattningsstatusDTO.PLANERAT;
            case UNDER_UTREDNING -> out = ErsattningsstatusDTO.UNDER_UTREDNING;
            case YRKAT -> out = ErsattningsstatusDTO.YRKAT;
            default -> { return null; }
        }
        return out;
    }

   public YrkandestatusEntity toYrkandestatusEntity(YrkandestatusDTO dto) {
        if (dto == null) {
            return null;
        }

        YrkandestatusEntity out;
        switch (dto) {
            case FASTSTALLT -> out = YrkandestatusEntity.FASTSTALLT;
            case FASTSTALLT_UNDER_UTREDNING -> out = YrkandestatusEntity.FASTSTALLT_UNDER_UTREDNING;
            case PLANERAT -> out = YrkandestatusEntity.PLANERAT;
            case UNDER_UTREDNING -> out = YrkandestatusEntity.UNDER_UTREDNING;
            case YRKAT -> out = YrkandestatusEntity.YRKAT;
            default -> { return null; }
        }
        return out;
    }

   public YrkandestatusDTO toYrkandestatusDTO(YrkandestatusEntity entity) {
        if (entity == null) {
            return null;
        }

        YrkandestatusDTO out;
        switch (entity) {
            case FASTSTALLT -> out = YrkandestatusDTO.FASTSTALLT;
            case FASTSTALLT_UNDER_UTREDNING -> out = YrkandestatusDTO.FASTSTALLT_UNDER_UTREDNING;
            case PLANERAT -> out = YrkandestatusDTO.PLANERAT;
            case UNDER_UTREDNING -> out = YrkandestatusDTO.UNDER_UTREDNING;
            case YRKAT -> out = YrkandestatusDTO.YRKAT;
            default -> { return null; }
        }
        return out;
    }

   public PeriodiseringEntity toPeriodiseringEntity(PeriodiseringDTO dto) {
        if (dto == null) {
            return null;
        }

        PeriodiseringEntity out;
        switch (dto) {
            case DAG -> out = PeriodiseringEntity.DAG;
            case ENGANGS -> out = PeriodiseringEntity.ENGANGS;
            case MANAD -> out = PeriodiseringEntity.MANAD;
            case VECKA -> out = PeriodiseringEntity.VECKA;
            default -> { return null; }
        }
        return out;
    }

   public PeriodiseringDTO toPeriodiseringDTO(PeriodiseringEntity entity) {
        if (entity == null) {
            return null;
        }

        PeriodiseringDTO out;
        switch (entity) {
            case DAG -> out = PeriodiseringDTO.DAG;
            case ENGANGS -> out = PeriodiseringDTO.ENGANGS;
            case MANAD -> out = PeriodiseringDTO.MANAD;
            case VECKA -> out = PeriodiseringDTO.VECKA;
            default -> { return null; }
        }
        return out;
    }

   public RollDTO toRollDTO(RollEntity entity)
   {
      if (entity == null)
      {
         return null;
      }

      return ImmutableRollDTO.builder()
            .id(entity.id())
            .namn(entity.namn())
            .version(entity.version())
            .build();
   }

   public VerksamhetslogikEntity toVerksamhetslogikEntity(VerksamhetslogikDTO dto) {
        if (dto == null) {
            return null;
        }

        VerksamhetslogikEntity out;
        switch (dto) {
            case A -> out = VerksamhetslogikEntity.A;
            case B -> out = VerksamhetslogikEntity.B;
            case C -> out = VerksamhetslogikEntity.C;
            default -> { return null; }
        }
        return out;
    }

   public VerksamhetslogikDTO toVerksamhetslogikDTO(VerksamhetslogikEntity entity) {
        if (entity == null) {
            return null;
        }

        VerksamhetslogikDTO out;
        switch (entity) {
            case A -> out = VerksamhetslogikDTO.A;
            case B -> out = VerksamhetslogikDTO.B;
            case C -> out = VerksamhetslogikDTO.C;
            default -> { return null; }
        }
        return out;
    }

}
