package se.fk.github.rimfrost.yrkande.logic.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.rimfrost.yrkande.logic.dto.*;
import se.fk.github.rimfrost.yrkande.logic.dto.YrkandeDTO;
import se.fk.github.rimfrost.yrkande.logic.entity.*;
import se.fk.github.rimfrost.yrkande.logic.entity.YrkandeEntity;

@ApplicationScoped
public class LogicMapper
{

   @Inject
   LogicEnumMapper enumMapper;

   public YrkandeDTO toYrkandeDTO(YrkandeEntity yrkandeEntity)
   {
      if (yrkandeEntity == null)
      {
         return null;
      }

      return ImmutableYrkandeDTO.builder()
            .id(yrkandeEntity.id())
            .formanstyp(yrkandeEntity.formanstyp())
            .version(yrkandeEntity.version())
            .yrkandedatum(yrkandeEntity.yrkandedatum())
            .yrkandestatus(enumMapper.toYrkandestatusDTO(yrkandeEntity.yrkandestatus()))
            .period(toPeriodDTO(yrkandeEntity.period()))
            .avsikt(enumMapper.toAvsiktDTO(yrkandeEntity.avsikt()))
            .andringsorsak(yrkandeEntity.andringsorsak())
            .yrkanderoll(yrkandeEntity.yrkanderoll()
                  .stream()
                  .map(this::toYrkanderollDTO)
                  .toList())
            .ersattning(yrkandeEntity.ersattning()
                  .stream()
                  .map(this::toErsattningDTO)
                  .toList())
            .build();
   }

   public PeriodDTO toPeriodDTO(PeriodEntity periodEntity)
   {
      if (periodEntity == null)
      {
         return null;
      }

      return ImmutablePeriodDTO.builder()
            .start(periodEntity.start())
            .slut(periodEntity.slut())
            .build();
   }

   public YrkanderollDTO toYrkanderollDTO(YrkanderollEntity yrkanderollEntity)
   {
      if (yrkanderollEntity == null)
      {
         return null;
      }

      return ImmutableYrkanderollDTO.builder()
            .id(yrkanderollEntity.id())
            .individ(toIndividDTO(yrkanderollEntity.individ()))
            .roll(enumMapper.toRollDTO(yrkanderollEntity.roll()))
            .yrkande(yrkanderollEntity.yrkande())
            .build();
   }

   public IndividDTO toIndividDTO(IndividEntity individEntity)
   {
      if (individEntity == null)
      {
         return null;
      }

      return ImmutableIndividDTO.builder()
            .id(individEntity.id())
            .fornamn(individEntity.fornamn())
            .efternamn(individEntity.efternamn())
            .build();
   }

   public ErsattningDTO toErsattningDTO(ErsattningEntity ersattningEntity)
   {
      if (ersattningEntity == null)
      {
         return null;
      }

      return ImmutableErsattningDTO.builder()
            .id(ersattningEntity.id())
            .belopp(ersattningEntity.belopp())
            .berakningsgrund(enumMapper.toBerakningsgrundDTO(ersattningEntity.berakningsgrund()))
            .beloppstyp(enumMapper.toBeloppstypDTO(ersattningEntity.beloppstyp()))
            .ersattningstyp(enumMapper.toErsattningstypDTO(ersattningEntity.ersattningstyp()))
            .periodisering(enumMapper.toPeriodiseringDTO(ersattningEntity.periodisering()))
            .omfattning(ersattningEntity.omfattning())
            .beslutsutfall(enumMapper.toBeslutsutfallDTO(ersattningEntity.beslutsutfall()))
            .avslagsanledning(ersattningEntity.avslagsanledning())
            .produceratResultat(toProduceratResultatDTO(ersattningEntity.produceratResultat()))
            .build();
   }

   public ProduceratResultatDTO toProduceratResultatDTO(ProduceratResultatEntity produceratResultatEntity)
   {
      if (produceratResultatEntity == null)
      {
         return null;
      }

      return ImmutableProduceratResultatDTO.builder()
            .id(produceratResultatEntity.id())
            .version(produceratResultatEntity.version())
            .franOchMed(produceratResultatEntity.franOchMed())
            .tillOchMed(produceratResultatEntity.tillOchMed())
            .status(enumMapper.toErsattningsstatusDTO(produceratResultatEntity.status()))
            .build();
   }

   public HandlaggningDTO toHandlaggningDTO(HandlaggningEntity handlaggningEntity)
   {
      if (handlaggningEntity == null)
      {
         return null;
      }

      @SuppressWarnings("UnnecessaryLocalVariable")
      HandlaggningDTO handlaggningDTO = ImmutableHandlaggningDTO.builder()
            .id(handlaggningEntity.id())
            .yrkande(toYrkandeDTO(handlaggningEntity.yrkande()))
            .version(handlaggningEntity.version())
            .processinstansId(handlaggningEntity.processinstansId())
            .skapadTS(handlaggningEntity.skapadTS())
            .avslutadTS(handlaggningEntity.avslutadTS())
            .handlaggningspecifikation(toHandlaggningspecifikationDTO(handlaggningEntity.handlaggningspecifikation()))
            .build();

      return handlaggningDTO;
   }

   public HandlaggningspecifikationDTO toHandlaggningspecifikationDTO(
         HandlaggningspecifikationEntity handlaggningspecifikationEntity)
   {
      if (handlaggningspecifikationEntity == null)
      {
         return null;
      }

      @SuppressWarnings("UnnecessaryLocalVariable")
      HandlaggningspecifikationDTO handlaggningspecifikationDTO = ImmutableHandlaggningspecifikationDTO.builder()
            .id(handlaggningspecifikationEntity.id())
            .version(handlaggningspecifikationEntity.version())
            .bpmn(handlaggningspecifikationEntity.bpmn())
            .namn(handlaggningspecifikationEntity.namn())
            .beskrivning(handlaggningspecifikationEntity.beskrivning())
            .uppgiftspecifikation(
                  handlaggningspecifikationEntity.uppgiftspecifikation()
                        .stream()
                        .map(this::toUppgiftspecifikationDTO)
                        .toList())
            .build();

      return handlaggningspecifikationDTO;
   }

   public UppgiftspecifikationDTO toUppgiftspecifikationDTO(UppgiftspecifikationEntity uppgiftspecifikationEntity)
   {
      if (uppgiftspecifikationEntity == null)
      {
         return null;
      }

      @SuppressWarnings("UnnecessaryLocalVariable")
      UppgiftspecifikationDTO uppgiftspecifikationDTO = ImmutableUppgiftspecifikationDTO.builder()
            .id(uppgiftspecifikationEntity.id())
            .version(uppgiftspecifikationEntity.version())
            .namn(uppgiftspecifikationEntity.namn())
            .uppgiftbeskrivning(uppgiftspecifikationEntity.uppgiftbeskrivning())
            .verksamhetslogik(enumMapper.toVerksamhetslogikDTO(uppgiftspecifikationEntity.verksamhetslogik()))
            .roll(enumMapper.toRollDTO(uppgiftspecifikationEntity.roll()))
            .applikationsId(uppgiftspecifikationEntity.applikationsId())
            .applikationsVersion(uppgiftspecifikationEntity.applikationsVersion())
            .regel(toRegelDTO(uppgiftspecifikationEntity.regel()))
            .uppgiftsGui(uppgiftspecifikationEntity.uppgiftsGui())
            .build();

      return uppgiftspecifikationDTO;
   }

   public RegelDTO toRegelDTO(RegelEntity regelEntity)
   {
      if (regelEntity == null)
      {
         return null;
      }

      @SuppressWarnings("UnnecessaryLocalVariable")
      RegelDTO regelDTO = ImmutableRegelDTO.builder()
            .id(regelEntity.id())
            .version(regelEntity.version())
            .lagrum(toLagrumDTO(regelEntity.lagrum()))
            .build();

      return regelDTO;
   }

   public LagrumDTO toLagrumDTO(LagrumEntity lagrumEntity)
   {
      if (lagrumEntity == null)
      {
         return null;
      }

      @SuppressWarnings("UnnecessaryLocalVariable")
      LagrumDTO lagrumDTO = ImmutableLagrumDTO.builder()
            .id(lagrumEntity.id())
            .version(lagrumEntity.version())
            .giltigFrom(lagrumEntity.giltigFrom())
            .giltigTom(lagrumEntity.giltigTom())
            .forfattning(lagrumEntity.forfattning())
            .kapitel(lagrumEntity.kapitel())
            .paragraf(lagrumEntity.paragraf())
            .stycke(lagrumEntity.stycke())
            .punkt(lagrumEntity.punkt())
            .build();

      return lagrumDTO;
   }

   public YrkandeEntity toYrkandeEntity(YrkandeDTO yrkandeDTO)
   {
      if (yrkandeDTO == null)
      {
         return null;
      }

      return ImmutableYrkandeEntity.builder()
            .id(yrkandeDTO.id())
            .formanstyp(yrkandeDTO.formanstyp())
            .version(yrkandeDTO.version())
            .yrkandedatum(yrkandeDTO.yrkandedatum())
            .yrkandestatus(enumMapper.toYrkandestatusEntity(yrkandeDTO.yrkandestatus()))
            .period(toPeriodEntity(yrkandeDTO.period()))
            .avsikt(enumMapper.toAvsiktEntity(yrkandeDTO.avsikt()))
            .andringsorsak(yrkandeDTO.andringsorsak())
            .yrkanderoll(
                  yrkandeDTO.yrkanderoll()
                        .stream()
                        .map(this::toYrkanderollEntity)
                        .toList())
            .ersattning(
                  yrkandeDTO.ersattning()
                        .stream()
                        .map(this::toErsattningEntity)
                        .toList())
            .build();
   }

   public PeriodEntity toPeriodEntity(PeriodDTO periodDTO)
   {
      if (periodDTO == null)
      {
         return null;
      }

      return ImmutablePeriodEntity.builder()
            .start(periodDTO.start())
            .slut(periodDTO.slut())
            .build();
   }

   public YrkanderollEntity toYrkanderollEntity(YrkanderollDTO yrkanderollDTO)
   {
      if (yrkanderollDTO == null)
      {
         return null;
      }

      return ImmutableYrkanderollEntity.builder()
            .id(yrkanderollDTO.id())
            .individ(toIndividEntity(yrkanderollDTO.individ()))
            .roll(enumMapper.toRollEntity(yrkanderollDTO.roll()))
            .yrkande(yrkanderollDTO.yrkande())
            .build();
   }

   public IndividEntity toIndividEntity(IndividDTO individDTO)
   {
      if (individDTO == null)
      {
         return null;
      }

      return ImmutableIndividEntity.builder()
            .id(individDTO.id())
            .fornamn(individDTO.fornamn())
            .efternamn(individDTO.efternamn())
            .build();
   }

   public ErsattningEntity toErsattningEntity(ErsattningDTO ersattningDTO)
   {
      if (ersattningDTO == null)
      {
         return null;
      }

      return ImmutableErsattningEntity.builder()
            .id(ersattningDTO.id())
            .belopp(ersattningDTO.belopp())
            .berakningsgrund(enumMapper.toBerakningsgrundEntity(ersattningDTO.berakningsgrund()))
            .beloppstyp(enumMapper.toBeloppstypEntity(ersattningDTO.beloppstyp()))
            .ersattningstyp(enumMapper.toErsattningstypEntity(ersattningDTO.ersattningstyp()))
            .periodisering(enumMapper.toPeriodiseringEntity(ersattningDTO.periodisering()))
            .omfattning(ersattningDTO.omfattning())
            .beslutsutfall(enumMapper.toBeslutsutfallEntity(ersattningDTO.beslutsutfall()))
            .avslagsanledning(ersattningDTO.avslagsanledning())
            .produceratResultat(toProduceratResultatEntity(ersattningDTO.produceratResultat()))
            .build();
   }

   public ProduceratResultatEntity toProduceratResultatEntity(ProduceratResultatDTO produceratResultatDTO)
   {
      if (produceratResultatDTO == null)
      {
         return null;
      }

      return ImmutableProduceratResultatEntity.builder()
            .id(produceratResultatDTO.id())
            .version(produceratResultatDTO.version())
            .franOchMed(produceratResultatDTO.franOchMed())
            .tillOchMed(produceratResultatDTO.tillOchMed())
            .status(enumMapper.toErsattningsstatusEntity(produceratResultatDTO.status()))
            .build();
   }

   @SuppressWarnings("unused")
   public HandlaggningEntity toHandlaggningEntity(HandlaggningDTO handlaggningDTO)
   {
      if (handlaggningDTO == null)
      {
         return null;
      }

      return ImmutableHandlaggningEntity.builder()
            .id(handlaggningDTO.id())
            .yrkande(toYrkandeEntity(handlaggningDTO.yrkande()))
            .version(handlaggningDTO.version())
            .processinstansId(handlaggningDTO.processinstansId())
            .skapadTS(handlaggningDTO.skapadTS())
            .avslutadTS(handlaggningDTO.avslutadTS())
            .handlaggningspecifikation(
                  toHandlaggningspecifikationEntity(handlaggningDTO.handlaggningspecifikation()))
            .build();
   }

   public HandlaggningspecifikationEntity toHandlaggningspecifikationEntity(
         HandlaggningspecifikationDTO handlaggningspecifikationDTO)
   {
      if (handlaggningspecifikationDTO == null)
      {
         return null;
      }

      return ImmutableHandlaggningspecifikationEntity.builder()
            .id(handlaggningspecifikationDTO.id())
            .version(handlaggningspecifikationDTO.version())
            .bpmn(handlaggningspecifikationDTO.bpmn())
            .namn(handlaggningspecifikationDTO.namn())
            .beskrivning(handlaggningspecifikationDTO.beskrivning())
            .uppgiftspecifikation(
                  handlaggningspecifikationDTO.uppgiftspecifikation()
                        .stream()
                        .map(this::toUppgiftspecifikationEntity)
                        .toList())
            .build();
   }

   public UppgiftspecifikationEntity toUppgiftspecifikationEntity(UppgiftspecifikationDTO uppgiftspecifikationDTO)
   {
      if (uppgiftspecifikationDTO == null)
      {
         return null;
      }

      return ImmutableUppgiftspecifikationEntity.builder()
            .id(uppgiftspecifikationDTO.id())
            .version(uppgiftspecifikationDTO.version())
            .namn(uppgiftspecifikationDTO.namn())
            .uppgiftbeskrivning(uppgiftspecifikationDTO.uppgiftbeskrivning())
            .verksamhetslogik(enumMapper.toVerksamhetslogikEntity(uppgiftspecifikationDTO.verksamhetslogik()))
            .roll(enumMapper.toRollEntity(uppgiftspecifikationDTO.roll()))
            .applikationsId(uppgiftspecifikationDTO.applikationsId())
            .applikationsVersion(uppgiftspecifikationDTO.applikationsVersion())
            .regel(toRegelEntity(uppgiftspecifikationDTO.regel()))
            .uppgiftsGui(uppgiftspecifikationDTO.uppgiftsGui())
            .build();
   }

   public RegelEntity toRegelEntity(RegelDTO regelDTO)
   {
      if (regelDTO == null)
      {
         return null;
      }

      return ImmutableRegelEntity.builder()
            .id(regelDTO.id())
            .version(regelDTO.version())
            .lagrum(toLagrumEntity(regelDTO.lagrum()))
            .build();
   }

   public LagrumEntity toLagrumEntity(LagrumDTO lagrumDTO)
   {
      if (lagrumDTO == null)
      {
         return null;
      }

      return ImmutableLagrumEntity.builder()
            .id(lagrumDTO.id())
            .version(lagrumDTO.version())
            .giltigFrom(lagrumDTO.giltigFrom())
            .giltigTom(lagrumDTO.giltigTom())
            .forfattning(lagrumDTO.forfattning())
            .kapitel(lagrumDTO.kapitel())
            .paragraf(lagrumDTO.paragraf())
            .stycke(lagrumDTO.stycke())
            .punkt(lagrumDTO.punkt())
            .build();
   }

}
