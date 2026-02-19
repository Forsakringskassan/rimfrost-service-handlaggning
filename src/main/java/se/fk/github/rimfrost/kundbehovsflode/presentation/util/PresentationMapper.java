package se.fk.github.rimfrost.kundbehovsflode.presentation.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.InternalServerErrorException;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ErsattningDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.FSSAInformationDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableKundbehovCreateRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableKundbehovsflodeCreateRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableKundbehovsflodeGetRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableKundbehovsflodePatchRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableKundbehovsflodePutRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableLagrumDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableRegelDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableUnderlagDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableUpdateErsattningDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableUppgiftDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ImmutableUppgiftspecifikationDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.IndividDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovCreateRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovCreateResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeCreateRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeCreateResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeGetRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeGetResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePatchRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePatchResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePutRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePutResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodespecifikationDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsrollDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.LagrumDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.PeriodDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.ProduceratResultatDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.RegelDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.UnderlagDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.UpdateErsattningDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.UppgiftDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.UppgiftStatusDTO;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.UppgiftspecifikationDTO;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Ersattning;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.FSSAinformation;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.GetKundbehovsflodeResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Individ;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Kundbehov;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Kundbehovsflode;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Kundbehovsflodespecifikation;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Kundbehovsroll;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Lagrum;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PatchKundbehovsflodeResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Period;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostKundbehovRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostKundbehovResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostKundbehovsflodeRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostKundbehovsflodeResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.ProduceratResultat;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutKundbehovsflodeRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutKundbehovsflodeResponse;
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

   public KundbehovCreateRequest toKundbehovCreateRequest(PostKundbehovRequest postKundbehovRequest)
   {
      if (postKundbehovRequest == null)
      {
         return null;
      }

      KundbehovCreateRequest request = ImmutableKundbehovCreateRequest.builder()
            .persnr(postKundbehovRequest.getPersnr())
            .formanstyp(postKundbehovRequest.getFormanstyp())
            .start(postKundbehovRequest.getPeriod().getStart())
            .slut(postKundbehovRequest.getPeriod().getSlut())
            .build();
      return request;
   }

   public PostKundbehovResponse toPostKundbehovResponse(KundbehovCreateResponse kundbehovCreateResponse)
   {
      if (kundbehovCreateResponse == null)
      {
         return null;
      }

      PostKundbehovResponse postKundbehovResponse = new PostKundbehovResponse();
      postKundbehovResponse.setKundbehov(toKundbehov(kundbehovCreateResponse.kundbehov()));
      return postKundbehovResponse;
   }

   public Kundbehov toKundbehov(KundbehovDTO kundbehovDTO)
   {
      if (kundbehovDTO == null)
      {
         return null;
      }

      Kundbehov kundbehov = new Kundbehov();
      kundbehov.setId(kundbehovDTO.id());
      kundbehov.setFormanstyp(kundbehovDTO.formanstyp());
      kundbehov.setVersion(kundbehovDTO.version());
      kundbehov.setKundbehovsdatum(kundbehovDTO.kundbehovsdatum());
      kundbehov.setKundbehovsstatus(enumMapper.toKundbehovsstatus(kundbehovDTO.kundbehovsstatus()));
      kundbehov.setPeriod(toPeriod(kundbehovDTO.period()));
      kundbehov.setAvsikt(enumMapper.toAvsikt(kundbehovDTO.avsikt()));
      kundbehov.setAndringsorsak(kundbehovDTO.andringsorsak());
      kundbehov.setKundbehovsroll(
            kundbehovDTO.kundbehovsroll()
                  .stream()
                  .map(this::toKundbehovsroll)
                  .toList());
      kundbehov.setErsattning(
            kundbehovDTO.ersattning()
                  .stream()
                  .map(this::toErsattning)
                  .toList());
      return kundbehov;
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

   public Kundbehovsroll toKundbehovsroll(KundbehovsrollDTO kundbehovsrollDTO)
   {
      if (kundbehovsrollDTO == null)
      {
         return null;
      }

      Kundbehovsroll kundbehovsroll = new Kundbehovsroll();
      kundbehovsroll.setId(kundbehovsrollDTO.id());
      kundbehovsroll.setIndivid(toIndivid(kundbehovsrollDTO.individ()));
      kundbehovsroll.setRoll(enumMapper.toRoll(kundbehovsrollDTO.roll()));
      kundbehovsroll.setYrkande(kundbehovsrollDTO.yrkande());
      return kundbehovsroll;
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

   public KundbehovsflodeCreateRequest toKundbehovsflodeCreateRequest(PostKundbehovsflodeRequest postKundbehovRequest)
   {
      if (postKundbehovRequest == null)
      {
         return null;
      }

      KundbehovsflodeCreateRequest request = ImmutableKundbehovsflodeCreateRequest.builder()
            .kundbehovId(postKundbehovRequest.getKundbehovId())
            .build();
      return request;
   }

   public PostKundbehovsflodeResponse toPostKundbehovsflodeResponse(KundbehovsflodeCreateResponse kundbehovsflodeCreateResponse)
   {
      if (kundbehovsflodeCreateResponse == null)
      {
         return null;
      }

      PostKundbehovsflodeResponse postKundbehovsflodeResponse = new PostKundbehovsflodeResponse();
      postKundbehovsflodeResponse.setKundbehovsflode(toKundbehovsflode(kundbehovsflodeCreateResponse.kundbehovsflode()));

      return postKundbehovsflodeResponse;
   }

   public KundbehovsflodeGetRequest toKundbehovsflodeGetRequest(UUID kundbehovsflodeId)
   {
      if (kundbehovsflodeId == null)
      {
         return null;
      }
      KundbehovsflodeGetRequest kundbehovsflodeGetRequest = ImmutableKundbehovsflodeGetRequest.builder()
            .kundbehovsflodeId(kundbehovsflodeId)
            .build();

      return kundbehovsflodeGetRequest;
   }

   public GetKundbehovsflodeResponse toGetKundbehovsflodeResponse(KundbehovsflodeGetResponse kundbehovsflodeGetResponse)
   {
      if (kundbehovsflodeGetResponse == null)
      {
         return null;
      }

      GetKundbehovsflodeResponse response = new GetKundbehovsflodeResponse();
      response.setKundbehovsflode(toKundbehovsflode(kundbehovsflodeGetResponse.kundbehovsflode()));

      return response;
   }

   public KundbehovsflodePutRequest toKundbehovsflodePutRequest(UUID kundbehovsflodeId,
         PutKundbehovsflodeRequest putKundbehovsflodeRequest)
   {
      if (kundbehovsflodeId == null || putKundbehovsflodeRequest == null)
      {
         return null;
      }

      KundbehovsflodePutRequest request = ImmutableKundbehovsflodePutRequest.builder()
            .kundbehovsflodeId(kundbehovsflodeId)
            .uppgift(toUppgiftDTO(putKundbehovsflodeRequest.getUppgift()))
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
            .kundbehovsflodeId(uppgift.getKundbehovsflodeId())
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

   public PutKundbehovsflodeResponse toPutKundbehovsflodeResponse(KundbehovsflodePutResponse kundbehovsflodePutResponse)
   {
      if (kundbehovsflodePutResponse == null)
      {
         return null;
      }

      PutKundbehovsflodeResponse response = new PutKundbehovsflodeResponse();
      response.setUppgift(toUppgift(kundbehovsflodePutResponse.uppgift()));

      return response;
   }

   private Uppgift toUppgift(UppgiftDTO uppgiftDTO)
   {
      var uppgift = new Uppgift();
      uppgift.setId(uppgiftDTO.uppgiftId());
      uppgift.setVersion(uppgiftDTO.version());
      uppgift.setKundbehovsflodeId(uppgiftDTO.kundbehovsflodeId());
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

   public Kundbehovsflode toKundbehovsflode(KundbehovsflodeDTO kundbehovsflodeDTO)
   {
      if (kundbehovsflodeDTO == null)
      {
         return null;
      }

      Kundbehovsflode kundbehovsflode = new Kundbehovsflode();
      kundbehovsflode.setId(kundbehovsflodeDTO.id());
      kundbehovsflode.setKundbehov(toKundbehov(kundbehovsflodeDTO.kundbehov()));
      kundbehovsflode.setVersion(kundbehovsflodeDTO.version());
      kundbehovsflode.setProcessinstansId(kundbehovsflodeDTO.processinstansId());
      kundbehovsflode.setSkapadTS(kundbehovsflodeDTO.skapadTS());
      kundbehovsflode.setAvslutadTS(kundbehovsflodeDTO.avslutadTS());
      kundbehovsflode.setKundbehovsspecifikation(toKundbehovsflodespecifikation(kundbehovsflodeDTO.kundbehovsspecifikation()));

      return kundbehovsflode;
   }

   public Kundbehovsflodespecifikation toKundbehovsflodespecifikation(
         KundbehovsflodespecifikationDTO kundbehovsflodespecifikationDTO)
   {
      if (kundbehovsflodespecifikationDTO == null)
      {
         return null;
      }
      Kundbehovsflodespecifikation kundbehovsflodespecifikation = new Kundbehovsflodespecifikation();
      kundbehovsflodespecifikation.setId(kundbehovsflodespecifikationDTO.id());
      kundbehovsflodespecifikation.setVersion(kundbehovsflodespecifikationDTO.version());
      kundbehovsflodespecifikation.setBpmn(kundbehovsflodespecifikationDTO.bpmn());
      kundbehovsflodespecifikation.setNamn(kundbehovsflodespecifikationDTO.namn());
      kundbehovsflodespecifikation.setBeskrivning(kundbehovsflodespecifikationDTO.beskrivning());
      kundbehovsflodespecifikation.setUppgiftspecifikation(
            kundbehovsflodespecifikationDTO.uppgiftspecifikation()
                  .stream()
                  .map(this::toUppgiftspecifikation)
                  .toList());
      return kundbehovsflodespecifikation;
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

   public KundbehovsflodePatchRequest toKundbehovsflodePatchRequest(UUID kundbehovsflodeId,
         List<UpdateErsattning> updateErsattning)
   {
      if (kundbehovsflodeId == null || updateErsattning == null)
      {
         return null;
      }

      return ImmutableKundbehovsflodePatchRequest.builder().kundbehovsflodeId(kundbehovsflodeId)
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

   public PatchKundbehovsflodeResponse toPatchKundbehovsflodeResponse(KundbehovsflodePatchResponse kundbehovsflodePatchResponse)
   {
      if (kundbehovsflodePatchResponse == null)
      {
         return null;
      }

      PatchKundbehovsflodeResponse response = new PatchKundbehovsflodeResponse();
      response.setKundbehovsflode(toKundbehovsflode(kundbehovsflodePatchResponse.kundbehovsflode()));

      return response;
   }

}
