package se.fk.github.rimfrost.handlaggning.presentation.util;

import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.InternalServerErrorException;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.github.rimfrost.handlaggning.logic.enums.Avsikt;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Avsiktstyp;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslut;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslutsrad;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.FSSAinformation;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.GetHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkande;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkandestatus;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Handlaggning;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.HandlaggningUpdate;
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
      yrkande.setAvsikt(mapAvsiktstyp(yrkandeDTO.avsikt()));
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
      produceratResultat.setAvslagsanledning(produceratResultatDTO.avslagsanledning());
      produceratResultat.setYrkandestatus(mapYrkandeStatus(produceratResultatDTO.yrkandestatus()));
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
            .avslagsanledning(produceratResultat.getAvslagsanledning())
            .yrkandestatus(mapYrkandeStatus(produceratResultat.getYrkandestatus()))
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
      response.setHandlaggning(toHandlaggning(handlaggningGetResponse.handlaggning()));

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

   private HandlaggningDTO toHandlaggningDTO(HandlaggningUpdate handlaggning)
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
            .beslut(toBeslutDTO(yrkande.getBeslut()))
            .build();

   }

   private BeslutDTO toBeslutDTO(Beslut beslut)
   {
      if (beslut == null)
      {
         return null;
      }

      return ImmutableBeslutDTO.builder()
            .id(beslut.getId())
            .version(beslut.getVersion())
            .datum(beslut.getDatum())
            .beslutsfattare(beslut.getBeslutsfattare())
            .beslutsrader(beslut.getBeslutsrader().stream().map(this::toBeslutsradDTO).toList())
            .build();
   }

   private BeslutsradDTO toBeslutsradDTO(Beslutsrad beslutsrad)
   {
      return ImmutableBeslutsradDTO.builder()
            .id(beslutsrad.getId())
            .version(beslutsrad.getVersion())
            .beslutsTyp(beslutsrad.getBeslutsTyp())
            .beslutsUtfall(beslutsrad.getBeslutsUtfall())
            .avslutsTyp(beslutsrad.getAvslutsTyp())
            .build();
   }

   private se.fk.github.rimfrost.handlaggning.logic.enums.Yrkandestatus mapYrkandeStatus(Yrkandestatus yrkandestatus) {
      return switch (yrkandestatus) {
         case PLANERAT -> se.fk.github.rimfrost.handlaggning.logic.enums.Yrkandestatus.PLANERAT;
         case YRKAT -> se.fk.github.rimfrost.handlaggning.logic.enums.Yrkandestatus.YRKAT;
         case UNDER_UTREDNING -> se.fk.github.rimfrost.handlaggning.logic.enums.Yrkandestatus.UNDER_UTREDNING;
         case FASTSTALLT_UNDER_UTREDNING -> se.fk.github.rimfrost.handlaggning.logic.enums.Yrkandestatus.FASTSTALLT_UNDER_UTREDNING;
         case FASTSTALLT -> se.fk.github.rimfrost.handlaggning.logic.enums.Yrkandestatus.FASTSTALLT;
      };
   }

   private Yrkandestatus mapYrkandeStatus(se.fk.github.rimfrost.handlaggning.logic.enums.Yrkandestatus yrkandestatus) {
      return switch (yrkandestatus) {
         case PLANERAT -> Yrkandestatus.PLANERAT;
         case YRKAT -> Yrkandestatus.YRKAT;
         case UNDER_UTREDNING -> Yrkandestatus.UNDER_UTREDNING;
         case FASTSTALLT_UNDER_UTREDNING -> Yrkandestatus.FASTSTALLT_UNDER_UTREDNING;
         case FASTSTALLT -> Yrkandestatus.FASTSTALLT;
      };
   }

   private se.fk.github.rimfrost.handlaggning.logic.enums.Avsikt mapAvsikt(Avsiktstyp avsiktsTyp) {
         return switch (avsiktsTyp) {
            case NY -> se.fk.github.rimfrost.handlaggning.logic.enums.Avsikt.NY;
            case ANDRING -> se.fk.github.rimfrost.handlaggning.logic.enums.Avsikt.ANDRING;
            case BORTTAG -> se.fk.github.rimfrost.handlaggning.logic.enums.Avsikt.BORTTAG;
            case ATERTAGEN -> se.fk.github.rimfrost.handlaggning.logic.enums.Avsikt.ATERTAGEN;
         };
   }

   private Avsiktstyp mapAvsiktstyp(se.fk.github.rimfrost.handlaggning.logic.enums.Avsikt avsiktsTyp)
   {
      return switch(avsiktsTyp){case se.fk.github.rimfrost.handlaggning.logic.enums.Avsikt.NY->Avsiktstyp.NY;case se.fk.github.rimfrost.handlaggning.logic.enums.Avsikt.ANDRING->Avsiktstyp.ANDRING;case se.fk.github.rimfrost.handlaggning.logic.enums.Avsikt.BORTTAG->Avsiktstyp.BORTTAG;case se.fk.github.rimfrost.handlaggning.logic.enums.Avsikt.ATERTAGEN->Avsiktstyp.ATERTAGEN;};
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
            .fssaInformation(toFSSAInformation(uppgift.getFsSAinformation()))
            .aktivitetId(uppgift.getAktivitetId());
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

   private se.fk.github.rimfrost.handlaggning.logic.enums.FSSAInformation toFSSAInformation(FSSAinformation fssaInformation)
   {
      switch (fssaInformation)
      {
         case HANDLAGGNING_PAGAR:
            return se.fk.github.rimfrost.handlaggning.logic.enums.FSSAInformation.HANDLAGGNING_PAGAR;
         case VANTAR_PA_INFO_FRAN_ANNAN_PART:
            return se.fk.github.rimfrost.handlaggning.logic.enums.FSSAInformation.VANTAR_PA_INFO_FRAN_ANNAN_PART;
         case VANTAR_PA_INFO_FRAN_KUND:
            return se.fk.github.rimfrost.handlaggning.logic.enums.FSSAInformation.VANTAR_PA_INFO_FRAN_KUND;
         default:
            throw new InternalServerErrorException("Could not map fssaInformation: " + fssaInformation);
      }
   }

   private UppgiftStatus toUppgiftStatus(se.fk.github.rimfrost.handlaggning.logic.enums.UppgiftStatus uppgiftStatus)
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
            throw new InternalServerErrorException("Could not map UppgiftStatus: " + uppgiftStatus);
      }
   }

   public PutHandlaggningResponse toPutHandlaggningResponse(HandlaggningPutResponse handlaggningPutResponse)
   {
      PutHandlaggningResponse response = new PutHandlaggningResponse();
      response.handlaggning(toHandlaggningUpdate(handlaggningPutResponse.handlaggning()));
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

   private se.fk.github.rimfrost.handlaggning.logic.enums.UppgiftStatus toUppgiftStatus(UppgiftStatus uppgiftStatus)
   {
      switch (uppgiftStatus)
      {
         case PLANERAD:
            return se.fk.github.rimfrost.handlaggning.logic.enums.UppgiftStatus.PLANERAD;
         case TILLDELAD:
            return se.fk.github.rimfrost.handlaggning.logic.enums.UppgiftStatus.TILLDELAD;
         case AVSLUTAD:
            return se.fk.github.rimfrost.handlaggning.logic.enums.UppgiftStatus.AVSLUTAD;
         default:
            throw new InternalServerErrorException("Could not map uppgiftStatus: " + uppgiftStatus);
      }
   }

   private FSSAinformation toFSSAInformation(se.fk.github.rimfrost.handlaggning.logic.enums.FSSAInformation fssaInformation)
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

   public HandlaggningUpdate toHandlaggningUpdate(HandlaggningDTO handlaggningDTO)
   {

      HandlaggningUpdate handlaggningUpdate = new HandlaggningUpdate();
      handlaggningUpdate.setId(handlaggningDTO.id());
      handlaggningUpdate.setYrkande(toYrkande(handlaggningDTO.yrkande()));
      handlaggningUpdate.setVersion(handlaggningDTO.version());
      handlaggningUpdate.setProcessinstansId(handlaggningDTO.processinstansId());
      handlaggningUpdate.setSkapadTS(handlaggningDTO.skapadTS());
      handlaggningUpdate.setAvslutadTS(handlaggningDTO.avslutadTS());
      handlaggningUpdate.setHandlaggningspecifikationId(handlaggningDTO.handlaggningspecifikationId());
      handlaggningUpdate.uppgift(toUppgift(handlaggningDTO.uppgift()));
      handlaggningUpdate.underlag(handlaggningDTO.underlag().stream()
            .map(this::toUnderlag)
            .toList());
      return handlaggningUpdate;
   }

   private Handlaggning toHandlaggning(HandlaggningDTO handlaggningDTO)
   {
      Handlaggning handlagning = new Handlaggning();
      handlagning.setId(handlaggningDTO.id());
      handlagning.setYrkande(toYrkande(handlaggningDTO.yrkande()));
      handlagning.setVersion(handlaggningDTO.version());
      handlagning.setProcessinstansId(handlaggningDTO.processinstansId());
      handlagning.setSkapadTS(handlaggningDTO.skapadTS());
      handlagning.setAvslutadTS(handlaggningDTO.avslutadTS());
      handlagning.setHandlaggningspecifikationId(handlaggningDTO.handlaggningspecifikationId());

      return handlagning;
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
