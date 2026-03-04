package se.fk.github.rimfrost.yrkande.presentation.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.InternalServerErrorException;
import se.fk.github.rimfrost.yrkande.logic.dto.*;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Ersattning;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.FSSAinformation;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.GetHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Individ;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkande;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Handlaggning;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Handlaggningspecifikation;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkanderoll;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Lagrum;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PatchHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Period;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostYrkandeRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostYrkandeResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostHandlaggningRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.ProduceratResultat;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutHandlaggningRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Regel;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Underlag;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UpdateErsattning;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgift;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftStatus;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgiftspecifikation;

@ApplicationScoped
public class PresentationMapper
{

   @Inject
   PresentationEnumMapper enumMapper;

   public YrkandeCreateRequest toYrkandeCreateRequest(PostYrkandeRequest PostYrkandeRequest)
   {
      if (PostYrkandeRequest == null)
      {
         return null;
      }

      YrkandeCreateRequest request = ImmutableYrkandeCreateRequest.builder()
            .persnr(PostYrkandeRequest.getPersnr())
            .formanstyp(PostYrkandeRequest.getFormanstyp())
            .start(PostYrkandeRequest.getPeriod().getStart())
            .slut(PostYrkandeRequest.getPeriod().getSlut())
            .build();
      return request;
   }

   public PostYrkandeResponse toPostYrkandeResponse(YrkandeCreateResponse yrkandeCreateResponse)
   {
      if (yrkandeCreateResponse == null)
      {
         return null;
      }

      PostYrkandeResponse postYrkandeResponse = new PostYrkandeResponse();
      postYrkandeResponse.setYrkande(toYrkande(yrkandeCreateResponse.yrkande()));
      return postYrkandeResponse;
   }

   public Yrkande toYrkande(YrkandeDTO yrkandeDTO)
   {
      if (yrkandeDTO == null)
      {
         return null;
      }

      Yrkande yrkande = new Yrkande();
      yrkande.setId(yrkandeDTO.id());
      yrkande.setFormanstyp(yrkandeDTO.formanstyp());
      yrkande.setVersion(yrkandeDTO.version());
      yrkande.setYrkandedatum(yrkandeDTO.yrkandedatum());
      yrkande.setYrkandestatus(enumMapper.toYrkandestatus(yrkandeDTO.yrkandestatus()));
      yrkande.setPeriod(toPeriod(yrkandeDTO.period()));
      yrkande.setAvsikt(enumMapper.toAvsikt(yrkandeDTO.avsikt()));
      yrkande.setAndringsorsak(yrkandeDTO.andringsorsak());
      yrkande.setYrkanderoll(
            yrkandeDTO.yrkanderoll()
                  .stream()
                  .map(this::toYrkanderoll)
                  .toList());
      yrkande.setErsattning(
            yrkandeDTO.ersattning()
                  .stream()
                  .map(this::toErsattning)
                  .toList());
      return yrkande;
   }

   public Period toPeriod(PeriodDTO periodDTO)
   {
      if (periodDTO == null)
      {
         return null;
      }

      Period period = new Period();
      period.setStart(periodDTO.start());
      period.setSlut(periodDTO.slut());
      return period;
   }

   public Yrkanderoll toYrkanderoll(YrkanderollDTO yrkanderollDTO)
   {
      if (yrkanderollDTO == null)
      {
         return null;
      }

      Yrkanderoll yrkanderoll = new Yrkanderoll();
      yrkanderoll.setId(yrkanderollDTO.id());
      yrkanderoll.setIndivid(toIndivid(yrkanderollDTO.individ()));
      yrkanderoll.setRoll(enumMapper.toRoll(yrkanderollDTO.roll()));
      yrkanderoll.setYrkande(yrkanderollDTO.yrkande());
      return yrkanderoll;
   }

   public Individ toIndivid(IndividDTO individDTO)
   {
      if (individDTO == null)
      {
         return null;
      }

      Individ individ = new Individ();
      individ.setId(individDTO.id());
      individ.setFornamn(individDTO.fornamn());
      individ.setEfternamn(individDTO.efternamn());
      return individ;
   }

   public Ersattning toErsattning(ErsattningDTO ersattningDTO)
   {
      if (ersattningDTO == null)
      {
         return null;
      }

      Ersattning ersattning = new Ersattning();
      ersattning.setId(ersattningDTO.id());
      ersattning.setBelopp(ersattningDTO.belopp());
      ersattning.setBerakningsgrund(enumMapper.toBerakningsgrund(ersattningDTO.berakningsgrund()));
      ersattning.setBeloppstyp(enumMapper.toBeloppstyp(ersattningDTO.beloppstyp()));
      ersattning.setErsattningstyp(enumMapper.toErsattningstyp(ersattningDTO.ersattningstyp()));
      ersattning.setPeriodisering(enumMapper.toPeriodisering(ersattningDTO.periodisering()));
      ersattning.setOmfattning(ersattningDTO.omfattning());
      ersattning.setBeslutsutfall(enumMapper.toBeslutsutfallEnum(ersattningDTO.beslutsutfall()));
      ersattning.setAvslagsanledning(ersattningDTO.avslagsanledning());
      ersattning.setProduceratResultat(toProduceratResultat(ersattningDTO.produceratResultat()));
      return ersattning;
   }

   public ProduceratResultat toProduceratResultat(ProduceratResultatDTO produceratResultatDTO)
   {
      if (produceratResultatDTO == null)
      {
         return null;
      }

      ProduceratResultat produceratResultat = new ProduceratResultat();
      produceratResultat.setId(produceratResultatDTO.id());
      produceratResultat.setVersion(produceratResultatDTO.version());
      produceratResultat.setFrom(produceratResultatDTO.franOchMed());
      produceratResultat.setTom(produceratResultatDTO.tillOchMed());
      produceratResultat.setStatus(enumMapper.toErsattningsstatus(produceratResultatDTO.status()));
      return produceratResultat;
   }

   public HandlaggningCreateRequest toHandlaggningCreateRequest(PostHandlaggningRequest PostYrkandeRequest)
   {
      if (PostYrkandeRequest == null)
      {
         return null;
      }

      HandlaggningCreateRequest request = ImmutableHandlaggningCreateRequest.builder()
            .yrkandeId(PostYrkandeRequest.getYrkandeId())
            .build();
      return request;
   }

   public PostHandlaggningResponse toPostHandlaggningResponse(HandlaggningCreateResponse handlaggningCreateResponse)
   {
      if (handlaggningCreateResponse == null)
      {
         return null;
      }

      PostHandlaggningResponse postHandlaggningResponse = new PostHandlaggningResponse();
      postHandlaggningResponse.setHandlaggning(toHandlaggning(handlaggningCreateResponse.handlaggning()));

      return postHandlaggningResponse;
   }

   public HandlaggningGetRequest toHandlaggningGetRequest(UUID HandlaggningId)
   {
      if (HandlaggningId == null)
      {
         return null;
      }
      HandlaggningGetRequest handlaggningGetRequest = ImmutableHandlaggningGetRequest.builder()
            .handlaggningId(HandlaggningId)
            .build();

      return handlaggningGetRequest;
   }

   public GetHandlaggningResponse toGetHandlaggningResponse(HandlaggningGetResponse handlaggningGetResponse)
   {
      if (handlaggningGetResponse == null)
      {
         return null;
      }

      GetHandlaggningResponse response = new GetHandlaggningResponse();
      response.setHandlaggning(toHandlaggning(handlaggningGetResponse.handlaggning()));

      return response;
   }

   public HandlaggningPutRequest toHandlaggningPutRequest(UUID HandlaggningId,
         PutHandlaggningRequest putHandlaggningRequest)
   {
      if (HandlaggningId == null || putHandlaggningRequest == null)
      {
         return null;
      }

      HandlaggningPutRequest request = ImmutableHandlaggningPutRequest.builder()
            .handlaggningId(HandlaggningId)
            .uppgift(toUppgiftDTO(putHandlaggningRequest.getUppgift()))
            .build();

      return request;
   }

   private UppgiftDTO toUppgiftDTO(Uppgift uppgift)
   {

      var builder = ImmutableUppgiftDTO.builder()
            .uppgiftId(uppgift.getId())
            .utforarId(uppgift.getUtforarId())
            .skapadTs(uppgift.getSkapadTs())
            .planeradTs(uppgift.getPlaneradTs())
            .utfordTs(uppgift.getUtfordTs())
            .handlaggningId(uppgift.getHandlaggningId())
            .uppgiftSpecifikation(toUppgiftspecifikationDTO(uppgift.getUppgiftspecifikation()))
            .version(uppgift.getVersion())
            .uppgiftStatus(toUppgiftStatus(uppgift.getUppgiftStatus()))
            .fssaInformation(toFSSAInformation(uppgift.getFsSAinformation()));

      for (var underlag : uppgift.getUnderlag())
      {
         builder.addUnderlag(toUnderlagDTO(underlag));
      }
      return builder.build();
   }

   private UnderlagDTO toUnderlagDTO(Underlag underlag)
   {
      return ImmutableUnderlagDTO.builder()
            .typ(underlag.getTyp())
            .version(underlag.getVersion())
            .data(underlag.getData())
            .build();
   }

   private FSSAInformationDTO toFSSAInformation(FSSAinformation fssaInformation)
   {
      switch (fssaInformation)
      {
         case HANDLAGGNING_PAGAR:
            return FSSAInformationDTO.HANDLAGGNING_PAGAR;
         case VANTAR_PA_INFO_FRAN_ANNAN_PART:
            return FSSAInformationDTO.VANTAR_PA_INFO_FRAN_ANNAN_PART;
         case VANTAR_PA_INFO_FRAN_KUND:
            return FSSAInformationDTO.VANTAR_PA_INFO_FRAN_KUND;
         default:
            throw new InternalServerErrorException("Could not map fssaInformation: " + fssaInformation);
      }
   }

   private UppgiftStatusDTO toUppgiftStatus(UppgiftStatus uppgiftStatus)
   {
      switch (uppgiftStatus)
      {
         case PLANERAD:
            return UppgiftStatusDTO.PLANERAD;
         case TILLDELAD:
            return UppgiftStatusDTO.TILLDELAD;
         case AVSLUTAD:
            return UppgiftStatusDTO.AVSLUTAD;
         default:
            throw new InternalServerErrorException("Could not map UppgiftStatus: " + uppgiftStatus);
      }
   }

   public PutHandlaggningResponse toPutHandlaggningResponse(HandlaggningPutResponse handlaggningPutResponse)
   {
      if (handlaggningPutResponse == null)
      {
         return null;
      }

      PutHandlaggningResponse response = new PutHandlaggningResponse();
      response.setUppgift(toUppgift(handlaggningPutResponse.uppgift()));

      return response;
   }

   private Uppgift toUppgift(UppgiftDTO uppgiftDTO)
   {
      var uppgift = new Uppgift();
      uppgift.setId(uppgiftDTO.uppgiftId());
      uppgift.setVersion(uppgiftDTO.version());
      uppgift.setHandlaggningId(uppgiftDTO.handlaggningId());
      uppgift.setPlaneradTs(uppgiftDTO.planeradTs());
      uppgift.setUtfordTs(uppgiftDTO.utfordTs());
      uppgift.setSkapadTs(uppgiftDTO.skapadTs());
      uppgift.setUtforarId(uppgiftDTO.utforarId());
      uppgift.setUppgiftspecifikation(toUppgiftspecifikation(uppgiftDTO.uppgiftSpecifikation()));
      uppgift.setFsSAinformation(toFSSAInformation(uppgiftDTO.fssaInformation()));
      uppgift.setUppgiftStatus(toUppgiftStatus(uppgiftDTO.uppgiftStatus()));

      var underlag = new ArrayList<Underlag>();
      for (var underlagDTO : uppgiftDTO.underlag())
      {
         underlag.add(toUnderlag(underlagDTO));
      }
      uppgift.setUnderlag(underlag);

      return uppgift;
   }

   private UppgiftStatus toUppgiftStatus(UppgiftStatusDTO uppgiftStatus)
   {
      switch (uppgiftStatus)
      {
         case PLANERAD:
            return UppgiftStatus.PLANERAD;
         case TILLDELAD:
            return UppgiftStatus.TILLDELAD;
         case AVSLUTAD:
            return UppgiftStatus.AVSLUTAD;
         default:
            throw new InternalServerErrorException("Could not map uppgiftStatus: " + uppgiftStatus);
      }
   }

   private FSSAinformation toFSSAInformation(FSSAInformationDTO fssaInformation)
   {
      switch (fssaInformation)
      {
         case HANDLAGGNING_PAGAR:
            return FSSAinformation.HANDLAGGNING_PAGAR;
         case VANTAR_PA_INFO_FRAN_ANNAN_PART:
            return FSSAinformation.VANTAR_PA_INFO_FRAN_ANNAN_PART;
         case VANTAR_PA_INFO_FRAN_KUND:
            return FSSAinformation.VANTAR_PA_INFO_FRAN_KUND;

         default:
            throw new InternalServerErrorException("Could not map fssaInformation: " + fssaInformation);
      }
   }

   private Underlag toUnderlag(UnderlagDTO underlagDTO)
   {
      var underlag = new Underlag();
      underlag.setTyp(underlagDTO.typ());
      underlag.setVersion(underlagDTO.version());
      underlag.setData(underlagDTO.data());
      return underlag;
   }

   public Handlaggning toHandlaggning(HandlaggningDTO handlaggningDTO)
   {
      if (handlaggningDTO == null)
      {
         return null;
      }

      Handlaggning handlaggning = new Handlaggning();
      handlaggning.setId(handlaggningDTO.id());
      handlaggning.setYrkande(toYrkande(handlaggningDTO.yrkande()));
      handlaggning.setVersion(handlaggningDTO.version());
      handlaggning.setProcessinstansId(handlaggningDTO.processinstansId());
      handlaggning.setSkapadTS(handlaggningDTO.skapadTS());
      handlaggning.setAvslutadTS(handlaggningDTO.avslutadTS());
      handlaggning.setHandlaggningspecifikation(toHandlaggningspecifikation(handlaggningDTO.handlaggningspecifikation()));

      return handlaggning;
   }

   public Handlaggningspecifikation toHandlaggningspecifikation(
         HandlaggningspecifikationDTO handlaggningspecifikationDTO)
   {
      if (handlaggningspecifikationDTO == null)
      {
         return null;
      }
      Handlaggningspecifikation handlaggningspecifikation = new Handlaggningspecifikation();
      handlaggningspecifikation.setId(handlaggningspecifikationDTO.id());
      handlaggningspecifikation.setVersion(handlaggningspecifikationDTO.version());
      handlaggningspecifikation.setBpmn(handlaggningspecifikationDTO.bpmn());
      handlaggningspecifikation.setNamn(handlaggningspecifikationDTO.namn());
      handlaggningspecifikation.setBeskrivning(handlaggningspecifikationDTO.beskrivning());
      handlaggningspecifikation.setUppgiftspecifikation(
            handlaggningspecifikationDTO.uppgiftspecifikation()
                  .stream()
                  .map(this::toUppgiftspecifikation)
                  .toList());
      return handlaggningspecifikation;
   }

   public Uppgiftspecifikation toUppgiftspecifikation(UppgiftspecifikationDTO uppgiftspecifikationDTO)
   {
      if (uppgiftspecifikationDTO == null)
      {
         return null;
      }

      Uppgiftspecifikation uppgiftspecifikation = new Uppgiftspecifikation();
      uppgiftspecifikation.setId(uppgiftspecifikationDTO.id());
      uppgiftspecifikation.setVersion(uppgiftspecifikationDTO.version());
      uppgiftspecifikation.setNamn(uppgiftspecifikationDTO.namn());
      uppgiftspecifikation.setUppgiftbeskrivning(uppgiftspecifikationDTO.uppgiftbeskrivning());
      uppgiftspecifikation.setVerksamhetslogik(enumMapper.toVerksamhetslogik(uppgiftspecifikationDTO.verksamhetslogik()));
      uppgiftspecifikation.setRoll(enumMapper.toRoll(uppgiftspecifikationDTO.roll()));
      uppgiftspecifikation.setApplikationsId(uppgiftspecifikationDTO.applikationsId());
      uppgiftspecifikation.setApplikationsVersion(uppgiftspecifikationDTO.applikationsVersion());
      uppgiftspecifikation.setRegel(toRegel(uppgiftspecifikationDTO.regel()));
      uppgiftspecifikation.setUppgiftsGui(uppgiftspecifikationDTO.uppgiftsGui());

      return uppgiftspecifikation;
   }

   public Regel toRegel(RegelDTO regelDTO)
   {
      if (regelDTO == null)
      {
         return null;
      }
      Regel regel = new Regel();
      regel.setId(regelDTO.id());
      regel.setVersion(regelDTO.version());
      regel.setLagrum(toLagrum(regelDTO.lagrum()));

      return regel;
   }

   public Lagrum toLagrum(LagrumDTO lagrumDTO)
   {
      if (lagrumDTO == null)
      {
         return null;
      }

      Lagrum lagrum = new Lagrum();
      lagrum.setId(lagrumDTO.id());
      lagrum.setVersion(lagrumDTO.version());
      lagrum.setGiltigFrom(lagrumDTO.giltigFrom());
      lagrum.setGiltigTom(lagrumDTO.giltigTom());
      lagrum.setForfattning(lagrumDTO.forfattning());
      lagrum.setKapitel(lagrumDTO.kapitel());
      lagrum.setParagraf(lagrumDTO.paragraf());
      lagrum.setStycke(lagrumDTO.stycke());
      lagrum.setPunkt(lagrumDTO.punkt());

      return lagrum;
   }

   public UppgiftspecifikationDTO toUppgiftspecifikationDTO(Uppgiftspecifikation uppgiftspecifikation)
   {
      if (uppgiftspecifikation == null)
      {
         return null;
      }

      UppgiftspecifikationDTO uppgiftspecifikationDTO = ImmutableUppgiftspecifikationDTO.builder()
            .id(uppgiftspecifikation.getId())
            .version(uppgiftspecifikation.getVersion())
            .namn(uppgiftspecifikation.getNamn())
            .uppgiftbeskrivning(uppgiftspecifikation.getUppgiftbeskrivning())
            .verksamhetslogik(enumMapper.toVerksamhetslogikDTO(uppgiftspecifikation.getVerksamhetslogik()))
            .roll(enumMapper.toRollDTO(uppgiftspecifikation.getRoll()))
            .applikationsId(uppgiftspecifikation.getApplikationsId())
            .applikationsVersion(uppgiftspecifikation.getApplikationsVersion())
            .regel(toRegelDTO(uppgiftspecifikation.getRegel()))
            .uppgiftsGui(uppgiftspecifikation.getUppgiftsGui())
            .build();

      return uppgiftspecifikationDTO;
   }

   public RegelDTO toRegelDTO(Regel regel)
   {
      if (regel == null)
      {
         return null;
      }

      RegelDTO regelDTO = ImmutableRegelDTO.builder()
            .id(regel.getId())
            .version(regel.getVersion())
            .lagrum(toLagrumDTO(regel.getLagrum()))
            .build();

      return regelDTO;
   }

   public LagrumDTO toLagrumDTO(Lagrum lagrum)
   {
      if (lagrum == null)
      {
         return null;
      }

      LagrumDTO lagrumDTO = ImmutableLagrumDTO.builder()
            .id(lagrum.getId())
            .version(lagrum.getVersion())
            .giltigFrom(lagrum.getGiltigFrom())
            .giltigTom(lagrum.getGiltigTom())
            .forfattning(lagrum.getForfattning())
            .kapitel(lagrum.getKapitel())
            .paragraf(lagrum.getParagraf())
            .stycke(lagrum.getStycke())
            .punkt(lagrum.getPunkt())
            .build();

      return lagrumDTO;
   }

   public HandlaggningPatchRequest toHandlaggningPatchRequest(UUID handlaggningId,
         List<UpdateErsattning> updateErsattning)
   {
      if (handlaggningId == null || updateErsattning == null)
      {
         return null;
      }

      return ImmutableHandlaggningPatchRequest.builder().handlaggningId(handlaggningId)
            .addAllUpdateErsattning(updateErsattning.stream().map(this::toUpdateErsattningDTO).toList()).build();
   }

   public UpdateErsattningDTO toUpdateErsattningDTO(UpdateErsattning updateErsattning)
   {
      if (updateErsattning == null)
      {
         return null;
      }

      return ImmutableUpdateErsattningDTO.builder()
            .ersattningsId(updateErsattning.getErsattningId())
            .beslutsutfall(enumMapper.toBeslutsutfallDTO(updateErsattning.getBeslutsutfall()))
            .avslagsanledning(updateErsattning.getAvslagsanledning())
            .build();
   }

   public PatchHandlaggningResponse toPatchHandlaggningResponse(HandlaggningPatchResponse handlaggningPatchResponse)
   {
      if (handlaggningPatchResponse == null)
      {
         return null;
      }

      PatchHandlaggningResponse response = new PatchHandlaggningResponse();
      response.setHandlaggning(toHandlaggning(handlaggningPatchResponse.handlaggning()));

      return response;
   }

}
