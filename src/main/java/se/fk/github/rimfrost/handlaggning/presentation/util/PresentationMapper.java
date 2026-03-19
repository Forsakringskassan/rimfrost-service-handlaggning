package se.fk.github.rimfrost.handlaggning.presentation.util;

import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.InternalServerErrorException;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Avsiktstyp;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.FSSAinformation;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.GetHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkande;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkandestatus;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Handlaggning;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.HandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.IndividYrkandeRoll;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostYrkandeRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostYrkandeResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostHandlaggningRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.ProduceratResultat;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutHandlaggningRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Underlag;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgift;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftSpecifikation;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftStatus;

@ApplicationScoped
public class PresentationMapper
{

   public YrkandeCreateRequest toYrkandeCreateRequest(PostYrkandeRequest postYrkandeRequest)
   {
      var individYrkandeRoller = postYrkandeRequest.getIndividYrkandeRoller().stream()
            .map(e -> ImmutableIndividYrkandeRollCreateRequest.builder()
                  .individId(e.getIndividId())
                  .yrkandeRollId(e.getYrkandeRollId())
                  .build())
            .toList();

      var produceradeResultat = postYrkandeRequest.getProduceradeResultat().stream()
            .map(e -> ImmutableProduceratResultatCreateRequest.builder()
                  .franOchMed(e.getFrom())
                  .tillOchMed(e.getTom())
                  .typ(e.getTyp())
                  .data(e.getData())
                  .build())
            .toList();

      YrkandeCreateRequest request = ImmutableYrkandeCreateRequest.builder()
            .erbjudandedId(postYrkandeRequest.getErbjudandeId())
            .yrkandeFrom(postYrkandeRequest.getYrkandeFrom())
            .yrkandeTom(postYrkandeRequest.getYrkandeTom())
            .individYrkandeRoller(individYrkandeRoller)
            .produceradeResultat(produceradeResultat)
            .build();

      return request;
   }

   public PostYrkandeResponse toPostYrkandeResponse(YrkandeCreateResponse yrkandeCreateResponse)
   {
      PostYrkandeResponse postYrkandeResponse = new PostYrkandeResponse();
      postYrkandeResponse.setYrkande(toYrkande(yrkandeCreateResponse.yrkande()));
      return postYrkandeResponse;
   }

   public Yrkande toYrkande(YrkandeDTO yrkandeDTO)
   {
      Yrkande yrkande = new Yrkande();
      yrkande.setId(yrkandeDTO.id());
      yrkande.setErbjudandeId(yrkandeDTO.erbjudandeId());
      yrkande.setVersion(yrkandeDTO.version());
      yrkande.setYrkandedatum(yrkandeDTO.yrkandedatum());
      yrkande.setYrkandeFrom(yrkandeDTO.yrkandeFrom());
      yrkande.setYrkandeTom(yrkandeDTO.yrkandeTom());
      yrkande.setYrkandestatus(mapYrkandeStatus(yrkandeDTO.yrkandestatus()));
      yrkande.setIndividYrkandeRoller(
            yrkandeDTO.individYrkandeRoll()
                  .stream()
                  .map(this::toIndividYrkandeRoll)
                  .toList());
      yrkande.setProduceradeResultat(
            yrkandeDTO.produceradeResultat()
                  .stream()
                  .map(this::toProduceratResultat)
                  .toList());
      return yrkande;
   }

   public ProduceratResultat toProduceratResultat(ProduceratResultatDTO produceratResultatDTO)
   {
      ProduceratResultat produceratResultat = new ProduceratResultat();
      produceratResultat.setId(produceratResultatDTO.id());
      produceratResultat.setVersion(produceratResultatDTO.version());
      produceratResultat.setFrom(produceratResultatDTO.franOchMed());
      produceratResultat.setTom(produceratResultatDTO.tillOchMed());
      produceratResultat.setTyp(produceratResultatDTO.typ());
      produceratResultat.setData(produceratResultatDTO.data());
      return produceratResultat;
   }

   public ProduceratResultatDTO toProduceratResultatDTO(ProduceratResultat produceratResultat)
   {

      return ImmutableProduceratResultatDTO.builder()
            .id(produceratResultat.getId())
            .version(produceratResultat.getVersion())
            .franOchMed(produceratResultat.getFrom())
            .tillOchMed(produceratResultat.getTom())
            .typ(produceratResultat.getTyp())
            .data(produceratResultat.getData())
            .build();
   }

   public HandlaggningCreateRequest toHandlaggningCreateRequest(PostHandlaggningRequest postYrkandeRequest)
   {
      HandlaggningCreateRequest request = ImmutableHandlaggningCreateRequest.builder()
            .yrkandeId(postYrkandeRequest.getYrkandeId())
            .processInstansId(postYrkandeRequest.getProcessInstansId())
            .handlaggningspecifikationId(postYrkandeRequest.getHandlaggningspecifikationId())
            .build();
      return request;
   }

   public PostHandlaggningResponse toPostHandlaggningResponse(HandlaggningCreateResponse handlaggningCreateResponse)
   {
      PostHandlaggningResponse postHandlaggningResponse = new PostHandlaggningResponse();
      postHandlaggningResponse.setHandlaggning(toHandlaggning(handlaggningCreateResponse.handlaggning()));
      return postHandlaggningResponse;
   }

   public HandlaggningGetRequest toHandlaggningGetRequest(UUID HandlaggningId)
   {
      HandlaggningGetRequest handlaggningGetRequest = ImmutableHandlaggningGetRequest.builder()
            .handlaggningId(HandlaggningId)
            .build();

      return handlaggningGetRequest;
   }

   public GetHandlaggningResponse toGetHandlaggningResponse(HandlaggningGetResponse handlaggningGetResponse)
   {
      GetHandlaggningResponse response = new GetHandlaggningResponse();
      response.setHandlaggning(toHandlaggningResponse(handlaggningGetResponse.handlaggning()));

      return response;
   }

   public HandlaggningPutRequest toHandlaggningPutRequest(UUID handlaggningId,
         PutHandlaggningRequest putHandlaggningRequest)
   {
      HandlaggningPutRequest request = ImmutableHandlaggningPutRequest.builder()
            .handlaggning(toHandlaggningDTO(putHandlaggningRequest.getHandlaggning()))
            .build();

      return request;
   }

   private HandlaggningDTO toHandlaggningDTO(Handlaggning handlaggning)
   {

      return ImmutableHandlaggningDTO.builder()
            .id(handlaggning.getId())
            .yrkande(toYrkandeDTO(handlaggning.getYrkande()))
            .version(handlaggning.getVersion())
            .processinstansId(handlaggning.getProcessinstansId())
            .skapadTS(handlaggning.getSkapadTS())
            .avslutadTS(handlaggning.getAvslutadTS())
            .handlaggningspecifikationId(handlaggning.getHandlaggningspecifikationId())
            .uppgift(toUppgiftDTO(handlaggning.getId(), handlaggning.getUppgift()))
            .underlag(handlaggning.getUnderlag()
                  .stream()
                  .map(this::toUnderlagDTO)
                  .toList())
            .build();
   }

   private YrkandeDTO toYrkandeDTO(Yrkande yrkande)
   {
      return ImmutableYrkandeDTO.builder()
            .id(yrkande.getId())
            .version(yrkande.getVersion())
            .erbjudandeId(yrkande.getErbjudandeId())
            .yrkandeFrom(yrkande.getYrkandeFrom())
            .yrkandeTom(yrkande.getYrkandeTom())
            .yrkandedatum(yrkande.getYrkandedatum())
            .yrkandestatus(mapYrkandeStatus(yrkande.getYrkandestatus()))
            .avsikt(mapAvsikt(yrkande.getAvsikt()))
            .produceradeResultat(yrkande.getProduceradeResultat()
                  .stream()
                  .map(this::toProduceratResultatDTO)
                  .toList())
            .individYrkandeRoll(yrkande.getIndividYrkandeRoller()
                  .stream()
                  .map(this::toIndividYrkandeRollDTO)
                  .toList())
            .build();

   }

   private YrkandestatusDTO mapYrkandeStatus(Yrkandestatus yrkandestatus) {
      return switch (yrkandestatus) {
         case PLANERAT -> YrkandestatusDTO.PLANERAT;
         case YRKAT -> YrkandestatusDTO.YRKAT;
         case UNDER_UTREDNING -> YrkandestatusDTO.UNDER_UTREDNING;
         case FASTSTALLT_UNDER_UTREDNING -> YrkandestatusDTO.FASTSTALLT_UNDER_UTREDNING;
         case FASTSTALLT -> YrkandestatusDTO.FASTSTALLT;
      };
   }

   private Yrkandestatus mapYrkandeStatus(YrkandestatusDTO yrkandestatus) {
      return switch (yrkandestatus) {
         case PLANERAT -> Yrkandestatus.PLANERAT;
         case YRKAT -> Yrkandestatus.YRKAT;
         case UNDER_UTREDNING -> Yrkandestatus.UNDER_UTREDNING;
         case FASTSTALLT_UNDER_UTREDNING -> Yrkandestatus.FASTSTALLT_UNDER_UTREDNING;
         case FASTSTALLT -> Yrkandestatus.FASTSTALLT;
      };
   }

   private AvsiktDTO mapAvsikt(Avsiktstyp avsiktsTyp) {
         return switch (avsiktsTyp) {
            case NY -> AvsiktDTO.NY;
            case ANDRING -> AvsiktDTO.ANDRING;
            case BORTTAG -> AvsiktDTO.BORTTAG;
            case ATERTAGEN -> AvsiktDTO.ATERTAGEN;
         };
   }

   private UppgiftDTO toUppgiftDTO(UUID handlaggningId, Uppgift uppgift)
   {

      var builder = ImmutableUppgiftDTO.builder()
            .uppgiftId(uppgift.getId())
            .handlaggningId(handlaggningId)
            .utforarId(uppgift.getUtforarId())
            .skapadTs(uppgift.getSkapadTs())
            .planeradTs(uppgift.getPlaneradTs())
            .utfordTs(uppgift.getUtfordTs())
            .uppgiftSpecifikation(toUppgiftspecifikationDTO(uppgift.getUppgiftspecifikation()))
            .version(uppgift.getVersion())
            .uppgiftStatus(toUppgiftStatus(uppgift.getUppgiftStatus()))
            .fssaInformation(toFSSAInformation(uppgift.getFsSAinformation()));
      return builder.build();
   }

   private UppgiftspecifikationDTO toUppgiftspecifikationDTO(UppgiftSpecifikation uppgiftspecifikation)
   {
      return ImmutableUppgiftspecifikationDTO.builder()
            .id(uppgiftspecifikation.getId())
            .version(uppgiftspecifikation.getVersion())
            .build();
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
      PutHandlaggningResponse response = new PutHandlaggningResponse();
      response.handlaggning(toHandlaggning(handlaggningPutResponse.handlaggning()));
      return response;
   }

   private Uppgift toUppgift(UppgiftDTO uppgiftDTO)
   {
      var uppgift = new Uppgift();
      uppgift.setId(uppgiftDTO.uppgiftId());
      uppgift.setVersion(uppgiftDTO.version());
      uppgift.setPlaneradTs(uppgiftDTO.planeradTs());
      uppgift.setUtfordTs(uppgiftDTO.utfordTs());
      uppgift.setSkapadTs(uppgiftDTO.skapadTs());
      uppgift.setUtforarId(uppgiftDTO.utforarId());
      uppgift.setUppgiftspecifikation(toUppgiftspecifikation(uppgiftDTO.uppgiftSpecifikation()));
      uppgift.setFsSAinformation(toFSSAInformation(uppgiftDTO.fssaInformation()));
      uppgift.setUppgiftStatus(toUppgiftStatus(uppgiftDTO.uppgiftStatus()));
      return uppgift;
   }

   private UppgiftSpecifikation toUppgiftspecifikation(UppgiftspecifikationDTO uppgiftSpecifikationDto)
   {
      var uppgiftSpecifikation = new UppgiftSpecifikation();
      uppgiftSpecifikation.setId(uppgiftSpecifikationDto.id());
      uppgiftSpecifikation.setVersion(uppgiftSpecifikationDto.version());
      return uppgiftSpecifikation;
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

      Handlaggning handlaggning = new Handlaggning();
      handlaggning.setId(handlaggningDTO.id());
      handlaggning.setYrkande(toYrkande(handlaggningDTO.yrkande()));
      handlaggning.setVersion(handlaggningDTO.version());
      handlaggning.setProcessinstansId(handlaggningDTO.processinstansId());
      handlaggning.setSkapadTS(handlaggningDTO.skapadTS());
      handlaggning.setAvslutadTS(handlaggningDTO.avslutadTS());
      handlaggning.setHandlaggningspecifikationId(handlaggningDTO.handlaggningspecifikationId());
      handlaggning.uppgift(toUppgift(handlaggningDTO.uppgift()));
      handlaggning.underlag(handlaggningDTO.underlag().stream()
            .map(this::toUnderlag)
            .toList());
      return handlaggning;
   }

   private HandlaggningResponse toHandlaggningResponse(HandlaggningDTO handlaggningDTO)
   {
      HandlaggningResponse handlagningResponse = new HandlaggningResponse();
      handlagningResponse.setId(handlaggningDTO.id());
      handlagningResponse.setYrkande(toYrkande(handlaggningDTO.yrkande()));
      handlagningResponse.setVersion(handlaggningDTO.version());
      handlagningResponse.setProcessinstansId(handlaggningDTO.processinstansId());
      handlagningResponse.setSkapadTS(handlaggningDTO.skapadTS());
      handlagningResponse.setAvslutadTS(handlaggningDTO.avslutadTS());
      handlagningResponse.setHandlaggningspecifikationId(handlaggningDTO.handlaggningspecifikationId());

      return handlagningResponse;
   }

   private IndividYrkandeRoll toIndividYrkandeRoll(IndividYrkandeRollDTO individYrkandeRollDTO)
   {
      var invidvidYrkandeRoll = new IndividYrkandeRoll();
      invidvidYrkandeRoll.setIndividId(individYrkandeRollDTO.individId());
      invidvidYrkandeRoll.setYrkandeRollId(individYrkandeRollDTO.yrkandeRollId());
      return invidvidYrkandeRoll;
   }

   private IndividYrkandeRollDTO toIndividYrkandeRollDTO(IndividYrkandeRoll individYrkandeRoll)
   {
      return ImmutableIndividYrkandeRollDTO.builder()
            .individId(individYrkandeRoll.getIndividId())
            .yrkandeRollId(individYrkandeRoll.getYrkandeRollId())
            .build();
   }

}
