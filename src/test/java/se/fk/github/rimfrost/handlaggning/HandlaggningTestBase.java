package se.fk.github.rimfrost.handlaggning;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.GetHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Handlaggning;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.HandlaggningUpdate;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgift;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkande;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class HandlaggningTestBase
{
   static
   {
      RestAssured.config = RestAssured.config().objectMapperConfig(
            objectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> new ObjectMapper()
                  .registerModule(new JavaTimeModule())
                  .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)));
   }

   protected static HandlaggningUpdate sendHandlaggningUpdate(HandlaggningUpdate handlaggningUpdate)
   {
      return given().contentType(ContentType.JSON).body(handlaggningUpdate)
            .put("/handlaggning/" + handlaggningUpdate.getHandlaggning().getId())
            .then().statusCode(200).extract().body().as(HandlaggningUpdate.class);
   }

   protected static void sendHandlaggningUpdate(HandlaggningUpdate handlaggningUpdate, int expectedStatusCode)
   {
      sendHandlaggningUpdate(handlaggningUpdate.getHandlaggning().getId(), handlaggningUpdate, expectedStatusCode);
   }

   protected static void sendHandlaggningUpdate(UUID handlaggningId, HandlaggningUpdate handlaggningUpdate,
         int expectedStatusCode)
   {
      var request = given().contentType(ContentType.JSON);
      if (handlaggningUpdate != null)
      {
         request.body(handlaggningUpdate);
      }

      request.put("/handlaggning/" + handlaggningId)
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
         HandlaggningUpdate putHandlaggningResponse)
   {
      assertNotNull(putHandlaggningResponse);
      verifyHandlaggning(handlaggningUpdate.getHandlaggning(), putHandlaggningResponse.getHandlaggning());
      verifyUppgift(handlaggningUpdate.getUppgift(), putHandlaggningResponse.getUppgift());
   }

   protected void verifyHandlaggningGetResponse(HandlaggningUpdate handlaggningUpdate,
         GetHandlaggningResponse getHandlaggningResponse)
   {
      assertNotNull(getHandlaggningResponse);
      verifyHandlaggning(handlaggningUpdate.getHandlaggning(), getHandlaggningResponse.getHandlaggning());
   }

   private void verifyHandlaggning(Handlaggning expectedHandlaggning, Handlaggning actualHandlaggning)
   {
      assertNotNull(actualHandlaggning);
      assertEquals(expectedHandlaggning.getId(), actualHandlaggning.getId());
      assertEquals(expectedHandlaggning.getVersion(), actualHandlaggning.getVersion());
      verifyYrkande(expectedHandlaggning.getYrkande(), actualHandlaggning.getYrkande());
      assertEquals(expectedHandlaggning.getHandlaggningIdTyp(), actualHandlaggning.getHandlaggningIdTyp());
      assertEquals(expectedHandlaggning.getHandlaggningIdVarde(), actualHandlaggning.getHandlaggningIdVarde());
      assertEquals(getInstant(expectedHandlaggning.getSkapadTS()), getInstant(actualHandlaggning.getSkapadTS()));
      assertEquals(getInstant(expectedHandlaggning.getAvslutadTS()), getInstant(actualHandlaggning.getAvslutadTS()));
      assertEquals(expectedHandlaggning.getHandlaggningspecifikationId(),
            actualHandlaggning.getHandlaggningspecifikationId());
   }

   private void verifyYrkande(Yrkande expectedYrkande, Yrkande actualYrkande)
   {
      assertEquals(expectedYrkande.getId(), actualYrkande.getId());
      assertEquals(expectedYrkande.getVersion(), actualYrkande.getVersion());
      assertEquals(expectedYrkande.getIngangtypId(), actualYrkande.getIngangtypId());
      assertEquals(getInstant(expectedYrkande.getYrkandedatum()), getInstant(actualYrkande.getYrkandedatum()));
      assertEquals(expectedYrkande.getYrkandestatus(), actualYrkande.getYrkandestatus());
      assertEquals(getInstant(expectedYrkande.getYrkandeFrom()), getInstant(actualYrkande.getYrkandeFrom()));
      assertEquals(getInstant(expectedYrkande.getYrkandeTom()), getInstant(actualYrkande.getYrkandeTom()));
      assertEquals(expectedYrkande.getAvsikt(), actualYrkande.getAvsikt());
      assertEquals(expectedYrkande.getRollerIYrkande(), actualYrkande.getRollerIYrkande());
      assertEquals(expectedYrkande.getSakfragorStallningstaganden(), actualYrkande.getSakfragorStallningstaganden());
   }

   private void verifyUppgift(Uppgift expectedUppgift, Uppgift actualUppgift)
   {
      assertNotNull(actualUppgift);
      assertEquals(expectedUppgift.getId(), actualUppgift.getId());
      assertEquals(expectedUppgift.getVersion(), actualUppgift.getVersion());
      assertEquals(getInstant(expectedUppgift.getSkapadTS()), getInstant(actualUppgift.getSkapadTS()));
      assertEquals(getInstant(expectedUppgift.getPlaneradTillTS()), getInstant(actualUppgift.getPlaneradTillTS()));
      assertEquals(getInstant(expectedUppgift.getUtfordTS()), getInstant(actualUppgift.getUtfordTS()));
      assertEquals(expectedUppgift.getUtforare(), actualUppgift.getUtforare());
      assertEquals(expectedUppgift.getAktivitetId(), actualUppgift.getAktivitetId());
      assertEquals(expectedUppgift.getUppgiftspecifikation(), actualUppgift.getUppgiftspecifikation());
      assertEquals(expectedUppgift.getUppgiftStatus(), actualUppgift.getUppgiftStatus());
      assertEquals(expectedUppgift.getFsSAinformation(), actualUppgift.getFsSAinformation());
      assertEquals(expectedUppgift.getHandlaggningId(), actualUppgift.getHandlaggningId());
      assertEquals(expectedUppgift.getRegelutfall(), actualUppgift.getRegelutfall());
      assertEquals(expectedUppgift.getUnderlag(), actualUppgift.getUnderlag());
      assertEquals(expectedUppgift.getResultat(), actualUppgift.getResultat());
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
