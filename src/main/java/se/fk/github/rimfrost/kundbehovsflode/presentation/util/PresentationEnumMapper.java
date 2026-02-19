package se.fk.github.rimfrost.kundbehovsflode.presentation.util;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.AvsiktDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.BeloppstypDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.BerakningsgrundDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.BeslutsutfallDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ErsattningsstatusDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ErsattningstypDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsstatusDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.PeriodiseringDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.RollDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.VerksamhetslogikDTO;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Avsikt;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beloppstyp;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Berakningsgrund;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslutsutfall;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Ersattningsstatus;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Ersattningstyp;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Kundbehovsstatus;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Periodisering;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Roll;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Verksamhetslogik;

@ApplicationScoped
public class PresentationEnumMapper
{
   public Avsikt toAvsikt(AvsiktDTO avisktDTO) {
        Avsikt out;
        switch (avisktDTO) {
            case ANDRING -> out = Avsikt.ANDRING;
            case ATERTAGEN -> out = Avsikt.ATERTAGEN;
            case BORTTAG -> out = Avsikt.BORTTAG;
            case NY -> out = Avsikt.NY;
            default -> {
                return null;
            }
        }

        return out;
    }

   public Beloppstyp toBeloppstyp(BeloppstypDTO beloppstypDTO) {
        Beloppstyp out;
        switch (beloppstypDTO) {
            case GRUNDBELOPP -> out = Beloppstyp.GRUNDBELOPP;
            case INKOMSTBASERAD -> out = Beloppstyp.INKOMSTBASERAD;
            default -> {
                return null;
            }
        }

        return out;
    }

   public Berakningsgrund toBerakningsgrund(BerakningsgrundDTO berakningsgrundDTO) {
        Berakningsgrund out;
        switch (berakningsgrundDTO) {
            case FASTBELOPP -> out = Berakningsgrund.FASTBELOPP;
            case LON -> out = Berakningsgrund.LON;
            default -> {
                return null;
            }
        }

        return out;
    }

   public Beslutsutfall toBeslutsutfallEnum(BeslutsutfallDTO beslutsutfallDTO) {
        Beslutsutfall out;
        switch (beslutsutfallDTO) {
            case FU -> out = Beslutsutfall.FU;
            case JA -> out = Beslutsutfall.JA;
            case NEJ -> out = Beslutsutfall.NEJ;
            default -> {
                return null;
            }
        }

        return out;
    }

   public Ersattningsstatus toErsattningsstatus(ErsattningsstatusDTO ersattningsstatusDTO) {
        Ersattningsstatus out;
        switch (ersattningsstatusDTO) {
            case FASTSTALLT -> out = Ersattningsstatus.FASTSTALLT;
            case FASTSTALLT_UNDER_UTREDNING -> out = Ersattningsstatus.FASTSTALLT_UNDER_UTREDNING;
            case PLANERAT -> out = Ersattningsstatus.PLANERAT;
            case UNDER_UTREDNING -> out = Ersattningsstatus.UNDER_UTREDNING;
            case YRKAT -> out = Ersattningsstatus.YRKAT;
            default -> {
                return null;
            }
        }

        return out;
    }

   public Ersattningstyp toErsattningstyp(ErsattningstypDTO ersattningstypDTO) {
        Ersattningstyp out;
        switch (ersattningstypDTO) {
            case HUNDBIDRAG -> out = Ersattningstyp.HUNDBIDRAG;
            case TILLFALLIG_VARD_AV_HUND -> out = Ersattningstyp.TILLFALLIG_VARD_AV_HUND;
            default -> {
                return null;
            }            
        }

        return out;
    }

   public Kundbehovsstatus toKundbehovsstatus(KundbehovsstatusDTO kundbehovsstatusDTO) {
        Kundbehovsstatus out;
        switch (kundbehovsstatusDTO) {
            case FASTSTALLT -> out = Kundbehovsstatus.FASTSTALLT;
            case FASTSTALLT_UNDER_UTREDNING -> out = Kundbehovsstatus.FASTSTALLT_UNDER_UTREDNING;
            case PLANERAT -> out = Kundbehovsstatus.PLANERAT;
            case UNDER_UTREDNING -> out = Kundbehovsstatus.UNDER_UTREDNING;
            case YRKAT -> out = Kundbehovsstatus.YRKAT;
            default -> {
                return null;
            }
        }

        return out;
    }

   public Periodisering toPeriodisering(PeriodiseringDTO periodiseringDTO) {
        Periodisering out;
        switch(periodiseringDTO) {
            case DAG -> out = Periodisering.DAG;
            case ENGANGS -> out = Periodisering.ENGANGS;
            case MANAD -> out = Periodisering.MANAD;
            case VECKA -> out = Periodisering.VECKA;
            default -> {
                return null;
            }
        }
        
        return out;
    }

   public Roll toRoll(RollDTO rollDTO) {
        Roll out;
        switch(rollDTO) {
            case AGARE -> out = Roll.AGARE;
            case ANSVARIG_HANDLAGGARE -> out = Roll.ANSVARIG_HANDLAGGARE;
            case DJUR -> out = Roll.DJUR;
            default -> {
                return null;
            }            
        }

        return out;
    }

   public Verksamhetslogik toVerksamhetslogik(VerksamhetslogikDTO verksamhetslogikDTO) {
        Verksamhetslogik out;
        switch(verksamhetslogikDTO) {
            case A -> out = Verksamhetslogik.A;
            case B -> out = Verksamhetslogik.B;
            case C -> out = Verksamhetslogik.C;
            default -> {
                return null;
            }            
        }
        return out;
    }

   public AvsiktDTO toAvsiktDTO(Avsikt avsikt) {
        AvsiktDTO out;
        switch (avsikt) {
            case ANDRING -> out = AvsiktDTO.ANDRING;
            case ATERTAGEN -> out = AvsiktDTO.ATERTAGEN;
            case BORTTAG -> out = AvsiktDTO.BORTTAG;
            case NY -> out = AvsiktDTO.NY;
            default -> {
                return null;
            }
        }

        return out;
    }

   public BeloppstypDTO toBeloppstypDTO(Beloppstyp beloppstyp) {
        BeloppstypDTO out;
        switch (beloppstyp) {
            case GRUNDBELOPP -> out = BeloppstypDTO.GRUNDBELOPP;
            case INKOMSTBASERAD -> out = BeloppstypDTO.INKOMSTBASERAD;
            default -> {
                return null;
            }
        }

        return out;
    }

   public BerakningsgrundDTO toBerakningsgrundDTO(Berakningsgrund berakningsgrund) {
        BerakningsgrundDTO out;
        switch (berakningsgrund) {
            case FASTBELOPP -> out = BerakningsgrundDTO.FASTBELOPP;
            case LON -> out = BerakningsgrundDTO.LON;
            default -> {
                return null;
            }
        }

        return out;
    }

   public BeslutsutfallDTO toBeslutsutfallDTO(Beslutsutfall beslutsutfall) {
        BeslutsutfallDTO out;
        switch (beslutsutfall) {
            case FU -> out = BeslutsutfallDTO.FU;
            case JA -> out = BeslutsutfallDTO.JA;
            case NEJ -> out = BeslutsutfallDTO.NEJ;
            default -> {
                return null;
            }
        }

        return out;
    }

   public ErsattningsstatusDTO toErsattningsstatusDTO(Ersattningsstatus ersattningsstatus) {
        ErsattningsstatusDTO out;
        switch (ersattningsstatus) {
            case FASTSTALLT -> out = ErsattningsstatusDTO.FASTSTALLT;
            case FASTSTALLT_UNDER_UTREDNING -> out = ErsattningsstatusDTO.FASTSTALLT_UNDER_UTREDNING;
            case PLANERAT -> out = ErsattningsstatusDTO.PLANERAT;
            case UNDER_UTREDNING -> out = ErsattningsstatusDTO.UNDER_UTREDNING;
            case YRKAT -> out = ErsattningsstatusDTO.YRKAT;
            default -> {
                return null;
            }
        }

        return out;
    }

   public ErsattningstypDTO toErsattningstypDTO(Ersattningstyp ersattningstyp) {
        ErsattningstypDTO out;
        switch (ersattningstyp) {
            case HUNDBIDRAG -> out = ErsattningstypDTO.HUNDBIDRAG;
            case TILLFALLIG_VARD_AV_HUND -> out = ErsattningstypDTO.TILLFALLIG_VARD_AV_HUND;
            default -> {
                return null;
            }
        }

        return out;
    }

   public KundbehovsstatusDTO toKundbehovsstatusDTO(Kundbehovsstatus kundbehovsstatus) {
        KundbehovsstatusDTO out;
        switch (kundbehovsstatus) {
            case FASTSTALLT -> out = KundbehovsstatusDTO.FASTSTALLT;
            case FASTSTALLT_UNDER_UTREDNING -> out = KundbehovsstatusDTO.FASTSTALLT_UNDER_UTREDNING;
            case PLANERAT -> out = KundbehovsstatusDTO.PLANERAT;
            case UNDER_UTREDNING -> out = KundbehovsstatusDTO.UNDER_UTREDNING;
            case YRKAT -> out = KundbehovsstatusDTO.YRKAT;
            default -> {
                return null;
            }
        }

        return out;
    }

   public PeriodiseringDTO toPeriodiseringDTO(Periodisering periodisering) {
        PeriodiseringDTO out;
        switch (periodisering) {
            case DAG -> out = PeriodiseringDTO.DAG;
            case ENGANGS -> out = PeriodiseringDTO.ENGANGS;
            case MANAD -> out = PeriodiseringDTO.MANAD;
            case VECKA -> out = PeriodiseringDTO.VECKA;
            default -> {
                return null;
            }
        }

        return out;
    }

   public RollDTO toRollDTO(Roll roll) {
        RollDTO out;
        switch (roll) {
            case AGARE -> out = RollDTO.AGARE;
            case ANSVARIG_HANDLAGGARE -> out = RollDTO.ANSVARIG_HANDLAGGARE;
            case DJUR -> out = RollDTO.DJUR;
            default -> {
                return null;
            }
        }

        return out;
    }

   public VerksamhetslogikDTO toVerksamhetslogikDTO(Verksamhetslogik verksamhetslogik) {
        VerksamhetslogikDTO out;
        switch (verksamhetslogik) {
            case A -> out = VerksamhetslogikDTO.A;
            case B -> out = VerksamhetslogikDTO.B;
            case C -> out = VerksamhetslogikDTO.C;
            default -> {
                return null;
            }
        }

        return out;
    }

}
