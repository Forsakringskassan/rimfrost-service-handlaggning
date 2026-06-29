package se.fk.github.rimfrost.handlaggning;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static se.fk.github.rimfrost.handlaggning.HandlaggningTestData.createHandlaggningUpdate;

@QuarkusTest
public class HandlaggningControllerValidationTest extends HandlaggningTestBase
{
   @Test
   void should_return_400_when_handlaggning_update_request_null_on_put()
   {
      sendHandlaggningUpdate(UUID.randomUUID(), null, 400);
   }

   @Test
   void should_return_400_when_put_handlaggning_request_null_on_put()
   {
      given().contentType(ContentType.JSON).put("/handlaggning/" + UUID.randomUUID().toString()).then().statusCode(400);
   }

   @Test
   void should_return_400_when_handlaggning_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.setId(null);
      sendHandlaggningUpdate(UUID.randomUUID(), handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_handlaggning_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_handlaggning_skapad_ts_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.setSkapadTS(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_handlaggning_specifikation_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.setHandlaggningspecifikationId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_handlaggning_underlag_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUnderlag().getFirst().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_handlaggning_underlag_typ_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUnderlag().getFirst().setTyp(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_handlaggning_underlag_data_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUnderlag().getFirst().setData(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().setId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_erbjudande_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().setErbjudandeId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_yrkande_datum_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().setYrkandedatum(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_yrkandestatus_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().setYrkandestatus(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_yrkande_from_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().setYrkandeFrom(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_yrkande_tom_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().setYrkandeTom(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_avsikt_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().setYrkandeTom(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_individ_yrkande_roller_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().setIndividYrkandeRoller(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_individ_yrkande_roll_roll_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getIndividYrkandeRoller().getFirst().setYrkandeRollId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_individ_yrkande_roll_individ_typ_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getIndividYrkandeRoller().getFirst().getIndivid().setTypId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_individ_yrkande_roll_individ_varde_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getIndividYrkandeRoller().getFirst().getIndivid().setVarde(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_producerade_resultat_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().setProduceradeResultat(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_producerade_resultat_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getProduceradeResultat().getFirst().setId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_producerade_resultat_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getProduceradeResultat().getFirst().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_producerade_resultat_from_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getProduceradeResultat().getFirst().setFrom(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_producerade_resultat_tom_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getProduceradeResultat().getFirst().setTom(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_producerade_resultat_yrkandestatus_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getProduceradeResultat().getFirst().setYrkandestatus(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_producerade_resultat_typ_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getProduceradeResultat().getFirst().setTyp(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_producerade_resultat_data_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getProduceradeResultat().getFirst().setData(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().setId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_datum_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().setDatum(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsfattare_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().setBeslutsfattare(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsfattare_typ_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().getBeslutsfattare().setTypId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsfattare_varde_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().getBeslutsfattare().setVarde(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrader_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().setBeslutsrader(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().getBeslutsrader().getFirst().setId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().getBeslutsrader().getFirst().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_besluts_typ_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().getBeslutsrader().getFirst().setBeslutsTyp(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_besluts_utfall_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().getBeslutsrader().getFirst().setBeslutsUtfall(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_avsluts_typ_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().getBeslutsrader().getFirst().setAvslutsTyp(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_producerade_resultat_ref_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().getBeslutsrader().getFirst().setProduceradeResultatRef(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_producerat_resultat_ref_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().getBeslutsrader().getFirst().getProduceradeResultatRef().getFirst().setId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_producerat_resultat_ref_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getBeslut().getBeslutsrader().getFirst().getProduceradeResultatRef().getFirst()
            .setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_skapad_ts_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_utforar_id_id_typ_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().getUtforarId().setTypId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_utforar_id_varde_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().getUtforarId().setVarde(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_aktivitet_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setAktivitetId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_uppgiftspecifikation_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setUppgiftspecifikation(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_uppgiftspecifikation_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().getUppgiftspecifikation().setId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_uppgiftspecifikation_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().getUppgiftspecifikation().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_fsa_information_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setFsSAinformation(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }
}
