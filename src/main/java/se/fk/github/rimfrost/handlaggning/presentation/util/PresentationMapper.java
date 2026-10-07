package se.fk.github.rimfrost.handlaggning.presentation.util;

import java.util.List;
import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslut;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslutsrad;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.GetHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Handlaggning;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.HandlaggningUpdate;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Idtyp;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Regelutfall;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.RollIYrkande;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.SakfragaStallningstagandeContainer;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.SakfragaStallningstagandeRef;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Underlag;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgift;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftSpecifikation;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgiftsdata;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkande;

@ApplicationScoped
public class PresentationMapper
{

   public HandlaggningGetRequest toHandlaggningGetRequest(UUID HandlaggningId)
   {
      return ImmutableHandlaggningGetRequest.builder()
            .handlaggningId(HandlaggningId)
            .build();
   }

   public GetHandlaggningResponse toGetHandlaggningResponse(HandlaggningGetResponse handlaggningGetResponse)
   {
      GetHandlaggningResponse response = new GetHandlaggningResponse();
      response.setHandlaggning(toHandlaggning(handlaggningGetResponse.handlaggning()));

      return response;
   }

   public HandlaggningPutRequest toHandlaggningPutRequest(UUID handlaggningId,
         HandlaggningUpdate handlaggningUpdate)
   {
      return ImmutableHandlaggningPutRequest.builder()
            .handlaggning(toHandlaggningDTO(handlaggningUpdate.getHandlaggning()))
            .uppgift(toUppgiftDTO(handlaggningId, handlaggningUpdate.getUppgift()))
            .build();
   }

   public HandlaggningUpdate toPutHandlaggningResponse(HandlaggningPutResponse handlaggningPutResponse)
   {
      HandlaggningUpdate handlaggningUpdate = new HandlaggningUpdate();
      handlaggningUpdate.setHandlaggning(toHandlaggning(handlaggningPutResponse.handlaggning()));
      handlaggningUpdate.setUppgift(toUppgift(handlaggningPutResponse.uppgift()));
      return handlaggningUpdate;
   }

   private Handlaggning toHandlaggning(HandlaggningDTO handlaggningDTO)
   {
      Handlaggning handlaggning = new Handlaggning();
      handlaggning.setId(handlaggningDTO.id());
      handlaggning.setYrkande(toYrkande(handlaggningDTO.yrkande()));
      handlaggning.setVersion(handlaggningDTO.version());
      handlaggning.setHandlaggningIdTyp(handlaggningDTO.handlaggningIdTyp());
      handlaggning.setHandlaggningIdVarde(handlaggningDTO.handlaggningIdVarde());
      handlaggning.setSkapadTS(handlaggningDTO.skapadTS());
      handlaggning.setAvslutadTS(handlaggningDTO.avslutadTS());
      handlaggning.setHandlaggningspecifikationId(handlaggningDTO.handlaggningspecifikationId());

      return handlaggning;
   }

   private HandlaggningDTO toHandlaggningDTO(Handlaggning handlaggning)
   {
      return ImmutableHandlaggningDTO.builder()
            .id(handlaggning.getId())
            .yrkande(toYrkandeDTO(handlaggning.getYrkande()))
            .version(handlaggning.getVersion())
            .skapadTS(handlaggning.getSkapadTS())
            .avslutadTS(handlaggning.getAvslutadTS())
            .handlaggningIdTyp(handlaggning.getHandlaggningIdTyp())
            .handlaggningIdVarde(handlaggning.getHandlaggningIdVarde())
            .handlaggningspecifikationId(handlaggning.getHandlaggningspecifikationId())
            .build();
   }

   private Yrkande toYrkande(YrkandeDTO yrkandeDTO)
   {
      Yrkande yrkande = new Yrkande();
      yrkande.setId(yrkandeDTO.id());
      yrkande.setIngangtypId(yrkandeDTO.ingangtypId());
      yrkande.setVersion(yrkandeDTO.version());
      yrkande.setYrkandedatum(yrkandeDTO.yrkandedatum());
      yrkande.setYrkandeFrom(yrkandeDTO.yrkandeFrom());
      yrkande.setYrkandeTom(yrkandeDTO.yrkandeTom());
      yrkande.setYrkandestatus(yrkandeDTO.yrkandestatus());
      yrkande.setAvsikt(yrkandeDTO.avsikt());
      yrkande.setRollerIYrkande(yrkandeDTO.rollerIYrkande()
            .stream()
            .map(this::toRollIYrkande)
            .toList());
      yrkande.setSakfragorStallningstaganden(yrkandeDTO.sakfragorStallningstaganden()
            .stream()
            .map(this::toSakfragaStallningstagande)
            .toList());
      yrkande.setBeslut(yrkandeDTO.beslut()
            .stream()
            .map(this::toBeslut)
            .toList());
      return yrkande;
   }

   private YrkandeDTO toYrkandeDTO(Yrkande yrkande)
   {
      return ImmutableYrkandeDTO.builder()
            .id(yrkande.getId())
            .version(yrkande.getVersion())
            .ingangtypId(yrkande.getIngangtypId())
            .yrkandeFrom(yrkande.getYrkandeFrom())
            .yrkandeTom(yrkande.getYrkandeTom())
            .yrkandedatum(yrkande.getYrkandedatum())
            .yrkandestatus(yrkande.getYrkandestatus())
            .avsikt(yrkande.getAvsikt())
            .sakfragorStallningstaganden(nullSafe(yrkande.getSakfragorStallningstaganden())
                  .stream()
                  .map(this::toSakfragaStallningstagandeDTO)
                  .toList())
            .rollerIYrkande(nullSafe(yrkande.getRollerIYrkande())
                  .stream()
                  .map(this::toRollIYrkandeDTO)
                  .toList())
            .beslut(nullSafe(yrkande.getBeslut())
                  .stream()
                  .map(this::toBeslutDTO)
                  .toList())
            .build();
   }

   private SakfragaStallningstagandeContainer toSakfragaStallningstagande(
         SakfragaStallningstagandeDTO sakfragaStallningstagandeDTO)
   {
      SakfragaStallningstagandeContainer container = new SakfragaStallningstagandeContainer();
      container.setId(sakfragaStallningstagandeDTO.id());
      container.setObjektTypId(sakfragaStallningstagandeDTO.objektTypId());
      container.setData(sakfragaStallningstagandeDTO.data());
      return container;
   }

   private SakfragaStallningstagandeDTO toSakfragaStallningstagandeDTO(SakfragaStallningstagandeContainer container)
   {
      return ImmutableSakfragaStallningstagandeDTO.builder()
            .id(container.getId())
            .objektTypId(container.getObjektTypId())
            .data(container.getData())
            .build();
   }

   private RollIYrkande toRollIYrkande(RollIYrkandeDTO rollIYrkandeDTO)
   {
      var rollIYrkande = new RollIYrkande();
      rollIYrkande.setId(rollIYrkandeDTO.id());
      rollIYrkande.setIndivid(toIdtyp(rollIYrkandeDTO.individ()));
      rollIYrkande.setYrkandeRollId(rollIYrkandeDTO.yrkandeRollId());
      rollIYrkande.setAvserYrkande(rollIYrkandeDTO.avserYrkande());
      return rollIYrkande;
   }

   private RollIYrkandeDTO toRollIYrkandeDTO(RollIYrkande rollIYrkande)
   {
      return ImmutableRollIYrkandeDTO.builder()
            .id(rollIYrkande.getId())
            .individ(toIdtypDTO(rollIYrkande.getIndivid()))
            .yrkandeRollId(rollIYrkande.getYrkandeRollId())
            .avserYrkande(rollIYrkande.getAvserYrkande())
            .build();
   }

   private BeslutDTO toBeslutDTO(Beslut beslut)
   {
      return ImmutableBeslutDTO.builder()
            .id(beslut.getId())
            .version(beslut.getVersion())
            .datum(beslut.getDatum())
            .beslutsfattare(toIdtypDTO(beslut.getBeslutsfattare()))
            .beslutsrader(nullSafe(beslut.getBeslutsrader()).stream().map(this::toBeslutsradDTO).toList())
            .build();
   }

   private Beslut toBeslut(BeslutDTO beslutDTO)
   {
      Beslut beslut = new Beslut();
      beslut.setId(beslutDTO.id());
      beslut.setVersion(beslutDTO.version());
      beslut.setDatum(beslutDTO.datum());
      beslut.setBeslutsfattare(toIdtyp(beslutDTO.beslutsfattare()));
      beslut.setBeslutsrader(beslutDTO.beslutsrader().stream().map(this::toBeslutsrad).toList());

      return beslut;
   }

   private BeslutsradDTO toBeslutsradDTO(Beslutsrad beslutsrad)
   {
      return ImmutableBeslutsradDTO.builder()
            .id(beslutsrad.getId())
            .version(beslutsrad.getVersion())
            .beslutsTyp(beslutsrad.getBeslutsTyp())
            .beslutsUtfall(beslutsrad.getBeslutsUtfall())
            .avslutsTyp(beslutsrad.getAvslutsTyp())
            .sakfragorStallningstagandeRefs(nullSafe(beslutsrad.getSakfragorStallningstaganden())
                  .stream()
                  .map(this::toSakfragaStallningstagandeRefDTO)
                  .toList())
            .build();
   }

   private Beslutsrad toBeslutsrad(BeslutsradDTO beslutsradDTO)
   {
      Beslutsrad beslutsrad = new Beslutsrad();
      beslutsrad.setId(beslutsradDTO.id());
      beslutsrad.setVersion(beslutsradDTO.version());
      beslutsrad.setAvslutsTyp(beslutsradDTO.avslutsTyp());
      beslutsrad.setBeslutsTyp(beslutsradDTO.beslutsTyp());
      beslutsrad.setBeslutsUtfall(beslutsradDTO.beslutsUtfall());
      beslutsrad.setSakfragorStallningstaganden(beslutsradDTO.sakfragorStallningstagandeRefs()
            .stream()
            .map(this::toSakfragaStallningstagandeRef)
            .toList());

      return beslutsrad;
   }

   private SakfragaStallningstagandeRefDTO toSakfragaStallningstagandeRefDTO(SakfragaStallningstagandeRef ref)
   {
      return ImmutableSakfragaStallningstagandeRefDTO.builder()
            .id(ref.getId())
            .version(ref.getVersion())
            .build();
   }

   private SakfragaStallningstagandeRef toSakfragaStallningstagandeRef(SakfragaStallningstagandeRefDTO refDTO)
   {
      SakfragaStallningstagandeRef ref = new SakfragaStallningstagandeRef();
      ref.setId(refDTO.id());
      ref.setVersion(refDTO.version());

      return ref;
   }

   private UppgiftDTO toUppgiftDTO(UUID handlaggningId, Uppgift uppgift)
   {
      return ImmutableUppgiftDTO.builder()
            .uppgiftId(uppgift.getId())
            .handlaggningId(handlaggningId)
            .utforare(toIdtypDTO(uppgift.getUtforare()))
            .skapadTS(uppgift.getSkapadTS())
            .planeradTillTS(uppgift.getPlaneradTillTS())
            .utfordTS(uppgift.getUtfordTS())
            .uppgiftSpecifikation(toUppgiftspecifikationDTO(uppgift.getUppgiftspecifikation()))
            .version(uppgift.getVersion())
            .uppgiftStatus(uppgift.getUppgiftStatus())
            .fssaInformation(uppgift.getFsSAinformation())
            .aktivitetId(uppgift.getAktivitetId())
            .regelutfall(toRegelutfallDTO(uppgift.getRegelutfall()))
            .underlag(nullSafe(uppgift.getUnderlag()).stream().map(this::toUnderlagDTO).toList())
            .resultat(nullSafe(uppgift.getResultat()).stream().map(this::toUppgiftsdataDTO).toList())
            .build();
   }

   private Uppgift toUppgift(UppgiftDTO uppgiftDTO)
   {
      var uppgift = new Uppgift();
      uppgift.setId(uppgiftDTO.uppgiftId());
      uppgift.setVersion(uppgiftDTO.version());
      uppgift.setAktivitetId(uppgiftDTO.aktivitetId());
      uppgift.setPlaneradTillTS(uppgiftDTO.planeradTillTS());
      uppgift.setUtfordTS(uppgiftDTO.utfordTS());
      uppgift.setSkapadTS(uppgiftDTO.skapadTS());
      uppgift.setUtforare(toIdtyp(uppgiftDTO.utforare()));
      uppgift.setUppgiftspecifikation(toUppgiftspecifikation(uppgiftDTO.uppgiftSpecifikation()));
      uppgift.setFsSAinformation(uppgiftDTO.fssaInformation());
      uppgift.setUppgiftStatus(uppgiftDTO.uppgiftStatus());
      uppgift.setHandlaggningId(uppgiftDTO.handlaggningId());
      uppgift.setRegelutfall(toRegelutfall(uppgiftDTO.regelutfall()));
      uppgift.setUnderlag(uppgiftDTO.underlag().stream().map(this::toUnderlag).toList());
      uppgift.setResultat(uppgiftDTO.resultat().stream().map(this::toUppgiftsdata).toList());

      return uppgift;
   }

   private UppgiftspecifikationDTO toUppgiftspecifikationDTO(UppgiftSpecifikation uppgiftspecifikation)
   {
      return ImmutableUppgiftspecifikationDTO.builder()
            .id(uppgiftspecifikation.getId())
            .version(uppgiftspecifikation.getVersion())
            .build();
   }

   private UppgiftSpecifikation toUppgiftspecifikation(UppgiftspecifikationDTO uppgiftSpecifikationDto)
   {
      var uppgiftSpecifikation = new UppgiftSpecifikation();
      uppgiftSpecifikation.setId(uppgiftSpecifikationDto.id());
      uppgiftSpecifikation.setVersion(uppgiftSpecifikationDto.version());
      return uppgiftSpecifikation;
   }

   private RegelutfallDTO toRegelutfallDTO(Regelutfall regelutfall)
   {
      if (regelutfall == null)
      {
         return null;
      }

      return ImmutableRegelutfallDTO.builder()
            .varde(regelutfall.getVarde())
            .build();
   }

   private Regelutfall toRegelutfall(RegelutfallDTO regelutfallDTO)
   {
      if (regelutfallDTO == null)
      {
         return null;
      }

      var regelutfall = new Regelutfall();
      regelutfall.setVarde(regelutfallDTO.varde());
      return regelutfall;
   }

   private UnderlagDTO toUnderlagDTO(Underlag underlag)
   {
      return ImmutableUnderlagDTO.builder()
            .typ(underlag.getTyp())
            .version(underlag.getVersion())
            .data(underlag.getData())
            .build();
   }

   private Underlag toUnderlag(UnderlagDTO underlagDTO)
   {
      var underlag = new Underlag();
      underlag.setTyp(underlagDTO.typ());
      underlag.setVersion(underlagDTO.version());
      underlag.setData(underlagDTO.data());
      return underlag;
   }

   private UppgiftsdataDTO toUppgiftsdataDTO(Uppgiftsdata uppgiftsdata)
   {
      return ImmutableUppgiftsdataDTO.builder()
            .informationsobjektId(uppgiftsdata.getInformationsobjektId())
            .version(uppgiftsdata.getVersion())
            .build();
   }

   private Uppgiftsdata toUppgiftsdata(UppgiftsdataDTO uppgiftsdataDTO)
   {
      var uppgiftsdata = new Uppgiftsdata();
      uppgiftsdata.setInformationsobjektId(uppgiftsdataDTO.informationsobjektId());
      uppgiftsdata.setVersion(uppgiftsdataDTO.version());
      return uppgiftsdata;
   }

   private IdtypDTO toIdtypDTO(Idtyp idtyp)
   {
      if (idtyp == null)
      {
         return null;
      }

      return ImmutableIdtypDTO.builder()
            .typId(idtyp.getTypId())
            .varde(idtyp.getVarde())
            .build();
   }

   private Idtyp toIdtyp(IdtypDTO idtypDTO)
   {
      if (idtypDTO == null)
      {
         return null;
      }

      Idtyp idtyp = new Idtyp();
      idtyp.setTypId(idtypDTO.typId());
      idtyp.setVarde(idtypDTO.varde());

      return idtyp;
   }

   private static <T> List<T> nullSafe(List<T> list)
   {
      return list == null ? List.of() : list;
   }

}
