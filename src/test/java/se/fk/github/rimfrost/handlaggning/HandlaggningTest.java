package se.fk.github.rimfrost.handlaggning;

import io.restassured.http.ContentType;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import jakarta.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.spi.Connector;
import org.junit.jupiter.api.Test;
import se.fk.rimfrost.HandlaggningDoneMessage;
import se.fk.rimfrost.HandlaggningRequestMessagePayload;
import se.fk.rimfrost.HandlaggningResponseMessageData;
import se.fk.rimfrost.HandlaggningResponseMessagePayload;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.FSSAinformation;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.GetHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Handlaggning;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.HandlaggningUpdate;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.IndividYrkandeRoll;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostHandlaggningRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostYrkandeRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostYrkandeResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.ProduceratResultat;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutHandlaggningRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Underlag;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgift;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftSpecifikation;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftStatus;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkandestatus;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkande;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class HandlaggningTest
{
   private static final String handlaggningResponses = "handlaggning-responses";
   private static final String handlaggningRequests = "handlaggning-requests";
   private static final String handlaggningDone = "handlaggning-done";

   @Inject
   @Connector("smallrye-in-memory")
   InMemoryConnector inMemoryConnector;

   private List<? extends Message<?>> waitForMessages(String channel)
   {
      await().atMost(5, TimeUnit.SECONDS).until(() -> !inMemoryConnector.sink(channel).received().isEmpty());
      return inMemoryConnector.sink(channel).received();
   }

   private PostYrkandeResponse createYrkande()
   {
      IndividYrkandeRoll individYrkandeRoll = new IndividYrkandeRoll();
      individYrkandeRoll.setIndividId(UUID.fromString("6b7186ed-ac45-4674-91cf-5723a515b556"));
      individYrkandeRoll.setYrkandeRollId(UUID.fromString("7ed1ee53-e53c-4303-b699-ab633eb1339a"));

      ProduceratResultat produceratResultat = new ProduceratResultat();
      produceratResultat.setId(UUID.fromString("34ec4e77-4aef-41f4-a3e3-e3b686e74aef"));
      produceratResultat.setVersion(1);
      produceratResultat.setFrom(OffsetDateTime.now());
      produceratResultat.setTom(OffsetDateTime.now());
      produceratResultat.setYrkandestatus(Yrkandestatus.YRKAT);
      produceratResultat.setTyp("ERSATTNING");
      produceratResultat.setData("{}");

      PostYrkandeRequest message = new PostYrkandeRequest();
      message.erbjudandeId(UUID.fromString("43da1371-ad39-407f-adde-c332ef7d3662"));
      message.yrkandeFrom(OffsetDateTime.now());
      message.yrkandeTom(OffsetDateTime.now());
      message.addIndividYrkandeRollerItem(individYrkandeRoll);
      message.addProduceradeResultatItem(produceratResultat);

      return given().contentType(ContentType.JSON).body(message).post("/yrkande").then().statusCode(200).extract().body()
            .as(PostYrkandeResponse.class);
   }

   private PostHandlaggningResponse createHandlaggning(UUID yrkandeId)
   {
      PostHandlaggningRequest request = new PostHandlaggningRequest();
      request.yrkandeId(yrkandeId);
      request.processInstansId(UUID.randomUUID());
      request.handlaggningspecifikationId(UUID.randomUUID());

      return given().contentType(ContentType.JSON).body(request).post("/handlaggning").then().statusCode(200).extract().body()
            .as(PostHandlaggningResponse.class);
   }

   private HandlaggningRequestMessagePayload receiveHandlaggningRequestMsg()
   {
      var msgs = waitForMessages(handlaggningRequests);

      assertEquals(1, msgs.size());
      assertInstanceOf(HandlaggningRequestMessagePayload.class, msgs.getFirst().getPayload());

      return (HandlaggningRequestMessagePayload) msgs.getFirst().getPayload();
   }

   private GetHandlaggningResponse getHandlaggning(UUID handlaggningId)
   {
      return given().contentType(ContentType.JSON).when().get("/handlaggning/{handlaggningId}", handlaggningId).then()
            .statusCode(200).extract().as(GetHandlaggningResponse.class);
   }

   private PutHandlaggningResponse sendHandlaggningUpdate(HandlaggningUpdate handlaggningUpdate)
   {
      PutHandlaggningRequest request = new PutHandlaggningRequest();
      request.handlaggning(handlaggningUpdate);

      return given().contentType(ContentType.JSON).body(request).put("/handlaggning/{handlaggningId}", handlaggningUpdate.getId())
            .then().statusCode(200).extract().as(PutHandlaggningResponse.class);
   }

   private HandlaggningUpdate createHandlaggningUpdate(Handlaggning handlaggning)
   {
      UppgiftSpecifikation uppgiftSpecifikation = new UppgiftSpecifikation();
      uppgiftSpecifikation.id(UUID.randomUUID());
      uppgiftSpecifikation.version(1);

      Uppgift uppgift = new Uppgift();
      uppgift.id(UUID.randomUUID());
      uppgift.version(1);
      uppgift.skapadTs(OffsetDateTime.now());
      uppgift.aktivitetId(UUID.randomUUID());
      uppgift.uppgiftStatus(UppgiftStatus.PLANERAD);
      uppgift.uppgiftspecifikation(uppgiftSpecifikation);
      uppgift.fsSAinformation(FSSAinformation.HANDLAGGNING_PAGAR);

      Underlag underlag = new Underlag();
      underlag.typ("test");
      underlag.version(1);
      underlag.data("test data");

      HandlaggningUpdate handlaggningUpdate = new HandlaggningUpdate();
      handlaggningUpdate.id(handlaggning.getId());
      handlaggningUpdate.version(handlaggning.getVersion() + 1);
      handlaggningUpdate.yrkande(handlaggning.getYrkande());
      handlaggningUpdate.processinstansId(handlaggning.getProcessinstansId());
      handlaggningUpdate.skapadTS(handlaggning.getSkapadTS());
      handlaggningUpdate.avslutadTS(handlaggning.getAvslutadTS());
      handlaggningUpdate.handlaggningspecifikationId(handlaggning.getHandlaggningspecifikationId());
      handlaggningUpdate.uppgift(uppgift);
      handlaggningUpdate.underlag(List.of(underlag));
      return handlaggningUpdate;
   }

   private Handlaggning createHandlaggning(HandlaggningUpdate handlaggningUpdate)
   {
      Handlaggning handlaggning = new Handlaggning();
      handlaggning.id(handlaggningUpdate.getId());
      handlaggning.version(handlaggningUpdate.getVersion() + 1);
      handlaggning.yrkande(handlaggningUpdate.getYrkande());
      handlaggning.processinstansId(handlaggningUpdate.getProcessinstansId());
      handlaggning.skapadTS(handlaggningUpdate.getSkapadTS());
      handlaggning.avslutadTS(handlaggningUpdate.getAvslutadTS());
      handlaggning.handlaggningspecifikationId(handlaggningUpdate.getHandlaggningspecifikationId());
      return handlaggning;
   }

   private void sendHandlaggningResponseMessage(HandlaggningRequestMessagePayload requestMsg)
   {
      HandlaggningResponseMessageData data = new HandlaggningResponseMessageData();
      data.setHandlaggningId(requestMsg.getData().getHandlaggningId());
      data.setResultat("JA");

      HandlaggningResponseMessagePayload responseMsg = new HandlaggningResponseMessagePayload();
      responseMsg.setId(requestMsg.getId());
      responseMsg.setSpecversion(requestMsg.getSpecversion());
      responseMsg.setSource(requestMsg.getSource());
      responseMsg.setType(requestMsg.getType());
      responseMsg.setTime(requestMsg.getTime());
      responseMsg.setKogitoparentprociid(requestMsg.getKogitoparentprociid());
      responseMsg.setKogitorootprocid(requestMsg.getKogitorootprocid());
      responseMsg.setKogitoproctype(requestMsg.getKogitoproctype());
      responseMsg.setKogitoprocinstanceid(requestMsg.getKogitoprocinstanceid());
      responseMsg.setKogitoprocist(responseMsg.getKogitoprocist());
      responseMsg.setKogitoprocversion(requestMsg.getKogitoprocversion());
      responseMsg.setKogitorootprociid(requestMsg.getKogitorootprociid());
      responseMsg.setKogitoprocid(requestMsg.getKogitoprocid());
      responseMsg.setKogitoprocrefid(requestMsg.getKogitoprocrefid());
      responseMsg.setData(data);

      inMemoryConnector.source(handlaggningResponses).send(responseMsg);
   }

   private void verifyYrkande(Yrkande yrkande)
   {
      assertNotNull(yrkande);
      assertNotNull(yrkande.getId());
      assertEquals(UUID.fromString("43da1371-ad39-407f-adde-c332ef7d3662"), yrkande.getErbjudandeId());
      assertNotNull(yrkande.getYrkandeFrom());
      assertNotNull(yrkande.getYrkandeTom());
      assertNotNull(yrkande.getYrkandedatum());
      assertEquals(1, yrkande.getIndividYrkandeRoller().size());
      assertEquals(UUID.fromString("6b7186ed-ac45-4674-91cf-5723a515b556"),
            yrkande.getIndividYrkandeRoller().getFirst().getIndividId());
      assertEquals(UUID.fromString("7ed1ee53-e53c-4303-b699-ab633eb1339a"),
            yrkande.getIndividYrkandeRoller().getFirst().getYrkandeRollId());
      assertFalse(yrkande.getProduceradeResultat().isEmpty());
      assertNotNull(yrkande.getProduceradeResultat().getFirst().getId());
      assertEquals("ERSATTNING", yrkande.getProduceradeResultat().getFirst().getTyp());
      assertEquals("{}", yrkande.getProduceradeResultat().getFirst().getData());
   }

   private void verifyHandlaggningResponse(Handlaggning handlaggning)
   {
      assertNotNull(handlaggning);
      assertNotNull(handlaggning.getId());
      assertNotNull(handlaggning.getHandlaggningspecifikationId());
      assertNotNull(handlaggning.getProcessinstansId());
      assertNotNull(handlaggning.getSkapadTS());
      verifyYrkande(handlaggning.getYrkande());
   }

   private void verifyCreateYrkandeResponse(PostYrkandeResponse yrkandeResponse)
   {
      assertNotNull(yrkandeResponse);
      verifyYrkande(yrkandeResponse.getYrkande());
   }

   private void verifyCreateHandlaggningResponse(PostHandlaggningResponse postHandlaggningResponse, UUID yrkandeId)
   {
      assertNotNull(postHandlaggningResponse);
      verifyHandlaggningResponse(postHandlaggningResponse.getHandlaggning());
      assertEquals(yrkandeId, postHandlaggningResponse.getHandlaggning().getYrkande().getId());
   }

   private void verifyHandlaggningRequestMsg(HandlaggningRequestMessagePayload msg, UUID handlaggningId)
   {
      assertNotNull(msg);
      assertNotNull(msg.getData());
      assertEquals(handlaggningId.toString(), msg.getData().getHandlaggningId());
   }

   private void verifyHandlaggningReadUpdate(UUID handlaggningId)
   {
      // Read
      var readResponse = getHandlaggning(handlaggningId);
      verifyHandlaggningResponse(readResponse.getHandlaggning());

      // Update
      var handlaggningUpdate = createHandlaggningUpdate(readResponse.getHandlaggning());
      var putResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningPutResponse(putResponse);
   }

   private void verifyHandlaggningPutResponse(PutHandlaggningResponse putHandlaggningResponse)
   {
      assertNotNull(putHandlaggningResponse);
      assertNotNull(putHandlaggningResponse.getHandlaggning());
      verifyHandlaggningResponse(createHandlaggning(putHandlaggningResponse.getHandlaggning()));
      assertNotNull(putHandlaggningResponse.getHandlaggning().getUppgift());
      assertEquals(1, putHandlaggningResponse.getHandlaggning().getUnderlag().size());
   }

   private void verifyHandlaggningDoneMsg(UUID handlaggningId)
   {
      var msgs = waitForMessages(handlaggningDone);

      assertEquals(1, msgs.size());
      assertInstanceOf(HandlaggningDoneMessage.class, msgs.getFirst().getPayload());

      var msg = (HandlaggningDoneMessage) msgs.getFirst().getPayload();
      assertEquals(handlaggningId.toString(), msg.getHandlaggningId());
   }

   @Test
   public void testHealthEndpoint()
   {
      when()
            .get("/q/health/live")
            .then()
            .statusCode(200)
            .body("status", is("UP"));
   }

   @Test
   public void testHandlaggningSmoke()
   {
      //
      // Create yrkande
      //
      var createYrkandeResponse = createYrkande();

      //
      // Verify create yrkande response
      //
      verifyCreateYrkandeResponse(createYrkandeResponse);

      //
      // Create handlaggning to start the flow
      //
      var createHandlaggningResponse = createHandlaggning(createYrkandeResponse.getYrkande().getId());

      //
      // Verify create handlaggning response
      //
      verifyCreateHandlaggningResponse(createHandlaggningResponse, createYrkandeResponse.getYrkande().getId());

      //
      // Receive kafka flow start message
      //
      var handlaggningRequestMsg = receiveHandlaggningRequestMsg();

      //
      // Verify kafka flow start message
      //
      verifyHandlaggningRequestMsg(handlaggningRequestMsg, createHandlaggningResponse.getHandlaggning().getId());

      //
      // Verify read & update handlaggning
      //
      verifyHandlaggningReadUpdate(createHandlaggningResponse.getHandlaggning().getId());

      //
      // Send kafka flow response message
      //
      sendHandlaggningResponseMessage(handlaggningRequestMsg);

      //
      // Verify kafka handlaggning done message
      //
      verifyHandlaggningDoneMsg(createHandlaggningResponse.getHandlaggning().getId());
   }
}
