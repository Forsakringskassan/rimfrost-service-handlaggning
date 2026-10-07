package se.fk.github.rimfrost.handlaggning.logic.util;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.github.rimfrost.handlaggning.logic.entity.*;

@ApplicationScoped
public class LogicMapper
{

   public YrkandeDTO toYrkandeDTO(YrkandeEntity yrkandeEntity)
   {
      return ImmutableYrkandeDTO.builder()
            .id(yrkandeEntity.id())
            .ingangtypId(yrkandeEntity.ingangtypId())
            .version(yrkandeEntity.version())
            .yrkandedatum(yrkandeEntity.yrkandedatum())
            .yrkandeFrom(yrkandeEntity.yrkandeFrom())
            .yrkandeTom(yrkandeEntity.yrkandeTom())
            .yrkandestatus(yrkandeEntity.yrkandestatus())
            .avsikt(yrkandeEntity.avsikt())
            .beslut(yrkandeEntity.beslut()
                  .stream()
                  .map(this::toBeslutDTO)
                  .toList())
            .rollerIYrkande(yrkandeEntity.rollerIYrkande()
                  .stream()
                  .map(this::toRollIYrkandeDTO)
                  .toList())
            .sakfragorStallningstaganden(yrkandeEntity.sakfragorStallningstaganden()
                  .stream()
                  .map(this::toSakfragaStallningstagandeDTO)
                  .toList())
            .build();
   }

   public RollIYrkandeDTO toRollIYrkandeDTO(RollIYrkandeEntity rollIYrkandeEntity)
   {
      return ImmutableRollIYrkandeDTO.builder()
            .id(rollIYrkandeEntity.id())
            .individ(toIdtypDTO(rollIYrkandeEntity.individ()))
            .yrkandeRollId(rollIYrkandeEntity.yrkandeRollId())
            .avserYrkande(rollIYrkandeEntity.avserYrkande())
            .build();
   }

   public SakfragaStallningstagandeDTO toSakfragaStallningstagandeDTO(
         SakfragaStallningstagandeEntity sakfragaStallningstagandeEntity)
   {
      return ImmutableSakfragaStallningstagandeDTO.builder()
            .id(sakfragaStallningstagandeEntity.id())
            .objektTypId(sakfragaStallningstagandeEntity.objektTypId())
            .data(sakfragaStallningstagandeEntity.data())
            .build();
   }

   public BeslutDTO toBeslutDTO(BeslutEntity beslutEntity)
   {
      return ImmutableBeslutDTO.builder()
            .id(beslutEntity.id())
            .version(beslutEntity.version())
            .datum(beslutEntity.datum())
            .beslutsfattare(toIdtypDTO(beslutEntity.beslutsfattare()))
            .beslutsrader(beslutEntity.beslutsrader()
                  .stream()
                  .map(this::toBeslutsradDTO)
                  .toList())
            .build();
   }

   public BeslutsradDTO toBeslutsradDTO(BeslutsradEntity beslutsradEntity)
   {
      return ImmutableBeslutsradDTO.builder()
            .id(beslutsradEntity.id())
            .version(beslutsradEntity.version())
            .beslutsTyp(beslutsradEntity.beslutsTyp())
            .beslutsUtfall(beslutsradEntity.beslutsUtfall())
            .avslutsTyp(beslutsradEntity.avslutsTyp())
            .sakfragorStallningstagandeRefs(beslutsradEntity.sakfragorStallningstagandeRefs()
                  .stream()
                  .map(this::toSakfragaStallningstagandeRefDTO)
                  .toList())
            .build();
   }

   public SakfragaStallningstagandeRefDTO toSakfragaStallningstagandeRefDTO(
         SakfragaStallningstagandeRefEntity refEntity)
   {
      return ImmutableSakfragaStallningstagandeRefDTO.builder()
            .id(refEntity.id())
            .version(refEntity.version())
            .build();
   }

   public HandlaggningDTO toHandlaggningDTO(HandlaggningEntity handlaggningEntity)
   {
      return ImmutableHandlaggningDTO.builder()
            .id(handlaggningEntity.id())
            .yrkande(toYrkandeDTO(handlaggningEntity.yrkande()))
            .version(handlaggningEntity.version())
            .handlaggningIdTyp(handlaggningEntity.handlaggningIdTyp())
            .handlaggningIdVarde(handlaggningEntity.handlaggningIdVarde())
            .skapadTS(handlaggningEntity.skapadTS())
            .avslutadTS(handlaggningEntity.avslutadTS())
            .handlaggningspecifikationId(handlaggningEntity.handlaggningspecifikationId())
            .build();
   }

   public YrkandeEntity toYrkandeEntity(YrkandeDTO yrkandeDTO)
   {
      return ImmutableYrkandeEntity.builder()
            .id(yrkandeDTO.id())
            .ingangtypId(yrkandeDTO.ingangtypId())
            .version(yrkandeDTO.version())
            .yrkandedatum(yrkandeDTO.yrkandedatum())
            .yrkandeFrom(yrkandeDTO.yrkandeFrom())
            .yrkandeTom(yrkandeDTO.yrkandeTom())
            .yrkandestatus(yrkandeDTO.yrkandestatus())
            .avsikt(yrkandeDTO.avsikt())
            .beslut(
                  yrkandeDTO.beslut()
                        .stream()
                        .map(this::toBeslutEntity)
                        .toList())
            .rollerIYrkande(
                  yrkandeDTO.rollerIYrkande()
                        .stream()
                        .map(this::toRollIYrkandeEntity)
                        .toList())
            .sakfragorStallningstaganden(
                  yrkandeDTO.sakfragorStallningstaganden()
                        .stream()
                        .map(this::toSakfragaStallningstagandeEntity)
                        .toList())
            .build();
   }

   public RollIYrkandeEntity toRollIYrkandeEntity(RollIYrkandeDTO rollIYrkandeDTO)
   {
      return ImmutableRollIYrkandeEntity.builder()
            .id(rollIYrkandeDTO.id())
            .individ(toIdtypEntity(rollIYrkandeDTO.individ()))
            .yrkandeRollId(rollIYrkandeDTO.yrkandeRollId())
            .avserYrkande(rollIYrkandeDTO.avserYrkande())
            .build();
   }

   public SakfragaStallningstagandeEntity toSakfragaStallningstagandeEntity(
         SakfragaStallningstagandeDTO sakfragaStallningstagandeDTO)
   {
      return ImmutableSakfragaStallningstagandeEntity.builder()
            .id(sakfragaStallningstagandeDTO.id())
            .objektTypId(sakfragaStallningstagandeDTO.objektTypId())
            .data(sakfragaStallningstagandeDTO.data())
            .build();
   }

   public BeslutEntity toBeslutEntity(BeslutDTO beslutDTO)
   {
      return ImmutableBeslutEntity.builder()
            .id(beslutDTO.id())
            .version(beslutDTO.version())
            .datum(beslutDTO.datum())
            .beslutsfattare(toIdtypEntity(beslutDTO.beslutsfattare()))
            .beslutsrader(
                  beslutDTO.beslutsrader()
                        .stream()
                        .map(this::toBeslutsradEntity)
                        .toList())
            .build();
   }

   public BeslutsradEntity toBeslutsradEntity(BeslutsradDTO beslutsradDTO)
   {
      return ImmutableBeslutsradEntity.builder()
            .id(beslutsradDTO.id())
            .version(beslutsradDTO.version())
            .beslutsTyp(beslutsradDTO.beslutsTyp())
            .beslutsUtfall(beslutsradDTO.beslutsUtfall())
            .avslutsTyp(beslutsradDTO.avslutsTyp())
            .sakfragorStallningstagandeRefs(
                  beslutsradDTO.sakfragorStallningstagandeRefs()
                        .stream()
                        .map(this::toSakfragaStallningstagandeRefEntity)
                        .toList())
            .build();
   }

   public SakfragaStallningstagandeRefEntity toSakfragaStallningstagandeRefEntity(
         SakfragaStallningstagandeRefDTO refDTO)
   {
      return ImmutableSakfragaStallningstagandeRefEntity.builder()
            .id(refDTO.id())
            .version(refDTO.version())
            .build();
   }

   public HandlaggningEntity toHandlaggningEntity(HandlaggningDTO handlaggningDTO)
   {
      return ImmutableHandlaggningEntity.builder()
            .id(handlaggningDTO.id())
            .yrkande(toYrkandeEntity(handlaggningDTO.yrkande()))
            .version(handlaggningDTO.version())
            .handlaggningIdTyp(handlaggningDTO.handlaggningIdTyp())
            .handlaggningIdVarde(handlaggningDTO.handlaggningIdVarde())
            .skapadTS(handlaggningDTO.skapadTS())
            .avslutadTS(handlaggningDTO.avslutadTS())
            .handlaggningspecifikationId(handlaggningDTO.handlaggningspecifikationId())
            .build();
   }

   public IdtypEntity toIdtypEntity(IdtypDTO idtyp)
   {
      return ImmutableIdtypEntity.builder()
            .typId(idtyp.typId())
            .varde(idtyp.varde())
            .build();
   }

   public IdtypDTO toIdtypDTO(IdtypEntity idtyp)
   {
      return ImmutableIdtypDTO.builder()
            .typId(idtyp.typId())
            .varde(idtyp.varde())
            .build();
   }
}
