package se.fk.github.rimfrost.handlaggning;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.GetHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.HandlaggningUpdate;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.ProduceratResultat;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutHandlaggningRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgift;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkande;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public abstract class HandlaggningTestBase
{
   static
   {
      RestAssured.config = RestAssured.config().objectMapperConfig(
            objectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> new ObjectMapper()
                  .registerModule(new JavaTimeModule())
                  .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)));
   }

   protected static PutHandlaggningResponse sendHandlaggningUpdate(HandlaggningUpdate handlaggningUpdate)
   {
      PutHandlaggningRequest request = new PutHandlaggningRequest();
      request.setHandlaggning(handlaggningUpdate);

      return given().contentType(ContentType.JSON).body(request).put("/handlaggning/" + handlaggningUpdate.getId())
            .then().statusCode(200).extract().body().as(PutHandlaggningResponse.class);
   }

   protected static void sendHandlaggningUpdate(HandlaggningUpdate handlaggningUpdate, int expectedStatusCode)
   {
      sendHandlaggningUpdate(handlaggningUpdate.getId(), handlaggningUpdate, expectedStatusCode);
   }

   protected static void sendHandlaggningUpdate(UUID handlaggningId, HandlaggningUpdate handlaggningUpdate,
         int expectedStatusCode)
   {
      PutHandlaggningRequest request = new PutHandlaggningRequest();
      request.setHandlaggning(handlaggningUpdate);

      given().contentType(ContentType.JSON).body(request).put("/handlaggning/" + handlaggningId)
            .then().statusCode(expectedStatusCode);
   }

   protected static void getHandlaggning(UUID id, int expectedStatus)
   {
      given().contentType(ContentType.JSON).get("/handlaggning/" + id).then().statusCode(expectedStatus);
   }

   protected static GetHandlaggningResponse getHandlaggning(UUID id)
   {
      return given().contentType(ContentType.JSON).get("/handlaggning/" + id).then().statusCode(200).extract().body()
            .as(GetHandlaggningResponse.class);
   }

   protected void verifyHandlaggningUpdateResponse(HandlaggningUpdate handlaggningUpdate,
         PutHandlaggningResponse putHandlaggningResponse)
   {
      assertNotNull(putHandlaggningResponse);
      assertNotNull(putHandlaggningResponse.getHandlaggning());
      assertEquals(handlaggningUpdate.getId(), putHandlaggningResponse.getHandlaggning().getId());
      assertEquals(handlaggningUpdate.getVersion(), putHandlaggningResponse.getHandlaggning().getVersion());
      verifyYrkande(handlaggningUpdate.getYrkande(), putHandlaggningResponse.getHandlaggning().getYrkande());
      assertEquals(handlaggningUpdate.getProcessinstansId(), putHandlaggningResponse.getHandlaggning().getProcessinstansId());
      assertEquals(getInstant(handlaggningUpdate.getSkapadTS()),
            getInstant(putHandlaggningResponse.getHandlaggning().getSkapadTS()));
      assertEquals(
            getInstant(handlaggningUpdate.getAvslutadTS()),
            getInstant(putHandlaggningResponse.getHandlaggning().getAvslutadTS()));
      assertEquals(handlaggningUpdate.getHandlaggningspecifikationId(),
            putHandlaggningResponse.getHandlaggning().getHandlaggningspecifikationId());
      assertEquals(handlaggningUpdate.getUnderlag(), putHandlaggningResponse.getHandlaggning().getUnderlag());
      verifyUppgift(handlaggningUpdate.getUppgift(), putHandlaggningResponse.getHandlaggning().getUppgift());
   }

   protected void verifyHandlaggningGetResponse(HandlaggningUpdate handlaggningUpdate,
         GetHandlaggningResponse getHandlaggningResponse)
   {
      assertNotNull(getHandlaggningResponse);
      assertNotNull(getHandlaggningResponse.getHandlaggning());
      assertEquals(handlaggningUpdate.getId(), getHandlaggningResponse.getHandlaggning().getId());
      assertEquals(handlaggningUpdate.getVersion(), getHandlaggningResponse.getHandlaggning().getVersion());
      verifyYrkande(handlaggningUpdate.getYrkande(), getHandlaggningResponse.getHandlaggning().getYrkande());
      assertEquals(handlaggningUpdate.getProcessinstansId(), getHandlaggningResponse.getHandlaggning().getProcessinstansId());
      assertEquals(getInstant(handlaggningUpdate.getSkapadTS()),
            getInstant(getHandlaggningResponse.getHandlaggning().getSkapadTS()));
      assertEquals(getInstant(handlaggningUpdate.getAvslutadTS()),
            getInstant(getHandlaggningResponse.getHandlaggning().getAvslutadTS()));
      assertEquals(handlaggningUpdate.getHandlaggningspecifikationId(),
            getHandlaggningResponse.getHandlaggning().getHandlaggningspecifikationId());
   }

   private void verifyYrkande(Yrkande expectedYrkande, Yrkande actualYrkande)
   {
      assertEquals(expectedYrkande.getId(), actualYrkande.getId());
      assertEquals(expectedYrkande.getVersion(), actualYrkande.getVersion());
      assertEquals(expectedYrkande.getErbjudandeId(), actualYrkande.getErbjudandeId());
      assertEquals(getInstant(expectedYrkande.getYrkandedatum()), getInstant(actualYrkande.getYrkandedatum()));
      assertEquals(expectedYrkande.getYrkandestatus(), actualYrkande.getYrkandestatus());
      assertEquals(getInstant(expectedYrkande.getYrkandeFrom()), getInstant(actualYrkande.getYrkandeFrom()));
      assertEquals(getInstant(expectedYrkande.getYrkandeTom()), getInstant(actualYrkande.getYrkandeTom()));
      assertEquals(expectedYrkande.getAvsikt(), actualYrkande.getAvsikt());
      assertEquals(expectedYrkande.getIndividYrkandeRoller(), actualYrkande.getIndividYrkandeRoller());
      assertEquals(expectedYrkande.getProduceradeResultat().size(), actualYrkande.getProduceradeResultat().size());

      for (int i = 0; i < expectedYrkande.getProduceradeResultat().size(); i++)
      {
         verifyProduceratResultat(expectedYrkande.getProduceradeResultat().get(i), actualYrkande.getProduceradeResultat().get(i));
      }
   }

   private void verifyProduceratResultat(ProduceratResultat expectedProduceratResultat,
         ProduceratResultat actualProduceratResultat)
   {
      assertEquals(expectedProduceratResultat.getId(), actualProduceratResultat.getId());
      assertEquals(expectedProduceratResultat.getVersion(), actualProduceratResultat.getVersion());
      assertEquals(getInstant(expectedProduceratResultat.getFrom()), getInstant(actualProduceratResultat.getFrom()));
      assertEquals(getInstant(expectedProduceratResultat.getTom()), getInstant(actualProduceratResultat.getTom()));
      assertEquals(expectedProduceratResultat.getYrkandestatus(), actualProduceratResultat.getYrkandestatus());
      assertEquals(expectedProduceratResultat.getAvslagsanledning(), actualProduceratResultat.getAvslagsanledning());
      assertEquals(expectedProduceratResultat.getTyp(), actualProduceratResultat.getTyp());
      assertEquals(expectedProduceratResultat.getData(), actualProduceratResultat.getData());
   }

   private void verifyUppgift(Uppgift expectedUppgift, Uppgift actualUppgift)
   {
      if (expectedUppgift == null)
      {
         assertNull(actualUppgift);
         return;
      }

      assertEquals(expectedUppgift.getId(), actualUppgift.getId());
      assertEquals(expectedUppgift.getVersion(), actualUppgift.getVersion());
      assertEquals(getInstant(expectedUppgift.getSkapadTs()), getInstant(actualUppgift.getSkapadTs()));
      assertEquals(getInstant(expectedUppgift.getPlaneradTs()), getInstant(actualUppgift.getPlaneradTs()));
      assertEquals(getInstant(expectedUppgift.getUtfordTs()), getInstant(actualUppgift.getUtfordTs()));
      assertEquals(expectedUppgift.getUtforarId(), actualUppgift.getUtforarId());
      assertEquals(expectedUppgift.getAktivitetId(), actualUppgift.getAktivitetId());
      assertEquals(expectedUppgift.getUppgiftspecifikation(), actualUppgift.getUppgiftspecifikation());
      assertEquals(expectedUppgift.getUppgiftStatus(), actualUppgift.getUppgiftStatus());
      assertEquals(expectedUppgift.getFsSAinformation(), actualUppgift.getFsSAinformation());
   }

   private Instant getInstant(OffsetDateTime offsetDateTime)
   {
      if (offsetDateTime == null)
      {
         return null;
      }

      return offsetDateTime.toInstant();
   }
}
