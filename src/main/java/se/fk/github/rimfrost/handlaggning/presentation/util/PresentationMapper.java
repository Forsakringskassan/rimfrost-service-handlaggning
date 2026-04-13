package se.fk.github.rimfrost.handlaggning.presentation.util;

import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslut;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslutsrad;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.GetHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Idtyp;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.ProduceratResultatRef;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkande;
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

@ApplicationScoped
public class PresentationMapper
{

   public YrkandeCreateRequest toYrkandeCreateRequest(PostYrkandeRequest postYrkandeRequest)
   {
      var individYrkandeRoller = postYrkandeRequest.getIndividYrkandeRoller().stream()
            .map(e -> ImmutableIndividYrkandeRollCreateRequest.builder()
                  .individ(toIdtypDTO(e.getIndivid()))
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
      yrkande.setYrkandestatus(yrkandeDTO.yrkandestatus());
      yrkande.setAvsikt(yrkandeDTO.avsikt());
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
      yrkande.setBeslut(toBeslut(yrkandeDTO.beslut()));
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
      produceratResultat.setYrkandestatus(produceratResultatDTO.yrkandestatus());
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
            .yrkandestatus(produceratResultat.getYrkandestatus())
            .typ(produceratResultat.getTyp())
            .data(produceratResultat.getData())
            .build();
   }

   public HandlaggningCreateRequest toHandlaggningCreateRequest(PostHandlaggningRequest postYrkandeRequest)
   {
      HandlaggningCreateRequest request = ImmutableHandlaggningCreateRequest.builder()
            .yrkandeId(postYrkandeRequest.getYrkandeId())
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
            .yrkandestatus(yrkande.getYrkandestatus())
            .avsikt(yrkande.getAvsikt())
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
            .beslutsfattare(toIdtypDTO(beslut.getBeslutsfattare()))
            .beslutsrader(beslut.getBeslutsrader().stream().map(this::toBeslutsradDTO).toList())
            .build();
   }

   private Beslut toBeslut(BeslutDTO beslutDTO)
   {
      if (beslutDTO == null)
      {
         return null;
      }

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
            .produceratResultatRefs(
                  beslutsrad.getProduceradeResultatRef().stream().map(this::toProduceratResultatRefDTO).toList())
            .build();
   }

   private Beslutsrad toBeslutsrad(BeslutsradDTO beslutsradDTO)
   {
      if (beslutsradDTO == null)
      {
         return null;
      }

      Beslutsrad beslutsrad = new Beslutsrad();
      beslutsrad.setId(beslutsradDTO.id());
      beslutsrad.setVersion(beslutsradDTO.version());
      beslutsrad.setAvslutsTyp(beslutsradDTO.avslutsTyp());
      beslutsrad.setBeslutsTyp(beslutsradDTO.beslutsTyp());
      beslutsrad.setBeslutsUtfall(beslutsradDTO.beslutsUtfall());
      beslutsrad.setProduceradeResultatRef(
            beslutsradDTO.produceratResultatRefs().stream().map(this::toProduceratResultatRef).toList());

      return beslutsrad;
   }

   private ProduceratResultatRefDTO toProduceratResultatRefDTO(ProduceratResultatRef produceratResultatRef)
   {
      if (produceratResultatRef == null)
      {
         return null;
      }

      return ImmutableProduceratResultatRefDTO.builder()
            .id(produceratResultatRef.getId())
            .version(produceratResultatRef.getVersion())
            .build();
   }

   private ProduceratResultatRef toProduceratResultatRef(ProduceratResultatRefDTO produceratResultatRefDTO)
   {
      if (produceratResultatRefDTO == null)
      {
         return null;
      }

      ProduceratResultatRef produceratResultatRef = new ProduceratResultatRef();
      produceratResultatRef.setId(produceratResultatRefDTO.id());
      produceratResultatRef.setVersion(produceratResultatRefDTO.version());

      return produceratResultatRef;
   }

   private UppgiftDTO toUppgiftDTO(UUID handlaggningId, Uppgift uppgift)
   {

      var builder = ImmutableUppgiftDTO.builder()
            .uppgiftId(uppgift.getId())
            .handlaggningId(handlaggningId)
            .utforarId(toIdtypDTO(uppgift.getUtforarId()))
            .skapadTs(uppgift.getSkapadTs())
            .planeradTs(uppgift.getPlaneradTs())
            .utfordTs(uppgift.getUtfordTs())
            .uppgiftSpecifikation(toUppgiftspecifikationDTO(uppgift.getUppgiftspecifikation()))
            .version(uppgift.getVersion())
            .uppgiftStatus(uppgift.getUppgiftStatus())
            .fssaInformation(uppgift.getFsSAinformation())
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
      uppgift.setAktivitetId(uppgiftDTO.aktivitetId());
      uppgift.setPlaneradTs(uppgiftDTO.planeradTs());
      uppgift.setUtfordTs(uppgiftDTO.utfordTs());
      uppgift.setSkapadTs(uppgiftDTO.skapadTs());
      uppgift.setUtforarId(toIdtyp(uppgiftDTO.utforarId()));
      uppgift.setUppgiftspecifikation(toUppgiftspecifikation(uppgiftDTO.uppgiftSpecifikation()));
      uppgift.setFsSAinformation(uppgiftDTO.fssaInformation());
      uppgift.setUppgiftStatus(uppgiftDTO.uppgiftStatus());
      return uppgift;
   }

   private UppgiftSpecifikation toUppgiftspecifikation(UppgiftspecifikationDTO uppgiftSpecifikationDto)
   {
      var uppgiftSpecifikation = new UppgiftSpecifikation();
      uppgiftSpecifikation.setId(uppgiftSpecifikationDto.id());
      uppgiftSpecifikation.setVersion(uppgiftSpecifikationDto.version());
      return uppgiftSpecifikation;
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
      invidvidYrkandeRoll.setIndivid(toIdtyp(individYrkandeRollDTO.individ()));
      invidvidYrkandeRoll.setYrkandeRollId(individYrkandeRollDTO.yrkandeRollId());
      return invidvidYrkandeRoll;
   }

   private IndividYrkandeRollDTO toIndividYrkandeRollDTO(IndividYrkandeRoll individYrkandeRoll)
   {
      return ImmutableIndividYrkandeRollDTO.builder()
            .individ(toIdtypDTO(individYrkandeRoll.getIndivid()))
            .yrkandeRollId(individYrkandeRoll.getYrkandeRollId())
            .build();
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

}
