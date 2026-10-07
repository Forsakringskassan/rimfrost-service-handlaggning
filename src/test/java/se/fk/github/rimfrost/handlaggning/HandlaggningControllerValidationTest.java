package se.fk.github.rimfrost.handlaggning;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Regelutfall;

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
      handlaggningUpdate.getHandlaggning().setId(null);
      sendHandlaggningUpdate(UUID.randomUUID(), handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_handlaggning_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.setHandlaggning(null);
      sendHandlaggningUpdate(UUID.randomUUID(), handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.setUppgift(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_handlaggning_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_handlaggning_skapad_ts_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().setSkapadTS(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_handlaggning_specifikation_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().setHandlaggningspecifikationId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_handlaggning_id_typ_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().setHandlaggningIdTyp(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_handlaggning_yrkande_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().setYrkande(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().setId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_ingangtyp_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().setIngangtypId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_yrkande_datum_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().setYrkandedatum(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_yrkandestatus_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().setYrkandestatus(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_yrkande_from_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().setYrkandeFrom(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_yrkande_tom_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().setYrkandeTom(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_avsikt_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().setAvsikt(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_roller_i_yrkande_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().setRollerIYrkande(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_roll_i_yrkande_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getRollerIYrkande().getFirst().setId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_roll_i_yrkande_roll_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getRollerIYrkande().getFirst().setYrkandeRollId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_roll_i_yrkande_avser_yrkande_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getRollerIYrkande().getFirst().setAvserYrkande(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_roll_i_yrkande_individ_typ_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getRollerIYrkande().getFirst().getIndivid().setTypId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_roll_i_yrkande_individ_varde_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getRollerIYrkande().getFirst().getIndivid().setVarde(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_sakfragor_stallningstaganden_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().setSakfragorStallningstaganden(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_sakfraga_stallningstagande_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getSakfragorStallningstaganden().getFirst().setId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_sakfraga_stallningstagande_objekt_typ_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getSakfragorStallningstaganden().getFirst().setObjektTypId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_sakfraga_stallningstagande_data_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getSakfragorStallningstaganden().getFirst().setData(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().setBeslut(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().setId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_datum_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().setDatum(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsfattare_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().setBeslutsfattare(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsfattare_typ_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().getBeslutsfattare().setTypId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsfattare_varde_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().getBeslutsfattare().setVarde(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrader_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().setBeslutsrader(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().getBeslutsrader().getFirst().setId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().getBeslutsrader().getFirst().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_besluts_typ_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().getBeslutsrader().getFirst().setBeslutsTyp(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_besluts_utfall_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().getBeslutsrader().getFirst()
            .setBeslutsUtfall(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_avsluts_typ_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().getBeslutsrader().getFirst().setAvslutsTyp(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_sakfragor_stallningstaganden_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().getBeslutsrader().getFirst()
            .setSakfragorStallningstaganden(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_sakfraga_stallningstagande_ref_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().getBeslutsrader().getFirst()
            .getSakfragorStallningstaganden().getFirst().setId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_yrkande_beslut_beslutsrad_sakfraga_stallningstagande_ref_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().getBeslut().getFirst().getBeslutsrader().getFirst()
            .getSakfragorStallningstaganden().getFirst().setVersion(null);
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
      handlaggningUpdate.getUppgift().setSkapadTS(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_utforare_typ_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().getUtforare().setTypId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_utforare_varde_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().getUtforare().setVarde(null);
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
   void should_return_400_when_uppgift_handlaggning_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setHandlaggningId(null);
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
   void should_return_400_when_uppgift_fssa_information_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setFsSAinformation(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_underlag_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().getUnderlag().getFirst().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_underlag_typ_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().getUnderlag().getFirst().setTyp(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_underlag_data_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().getUnderlag().getFirst().setData(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_resultat_informationsobjekt_id_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().getResultat().getFirst().setInformationsobjektId(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_resultat_version_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().getResultat().getFirst().setVersion(null);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }

   @Test
   void should_return_400_when_uppgift_regelutfall_varde_null_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      var regelutfall = new Regelutfall();
      handlaggningUpdate.getUppgift().setRegelutfall(regelutfall);
      sendHandlaggningUpdate(handlaggningUpdate, 400);
   }
}
