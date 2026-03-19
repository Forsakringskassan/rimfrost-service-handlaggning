package se.fk.github.rimfrost.handlaggning.logic.util;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.github.rimfrost.handlaggning.logic.dto.YrkandestatusDTO;
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
}
