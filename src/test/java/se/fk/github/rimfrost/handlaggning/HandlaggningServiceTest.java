package se.fk.github.rimfrost.handlaggning;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Regelutfall;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static se.fk.github.rimfrost.handlaggning.HandlaggningTestData.createHandlaggningUpdate;

@QuarkusTest
public class HandlaggningServiceTest extends HandlaggningTestBase
{
   @Test
   void should_create_handlaggning_on_put_with_unknown_id()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      var response = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, response);
   }

   @Test
   void should_create_handlaggning_on_put_with_unknown_id_and_handlaggning_id_varde_null_and_underlag_empty()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().setHandlaggningIdVarde(null);
      handlaggningUpdate.getUppgift().setUnderlag(List.of());

      var response = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, response);
   }

   @Test
   void should_create_handlaggning_on_put_with_unknown_id_and_yrkande_beslut_empty()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().setBeslut(List.of());
      handlaggningUpdate.getUppgift().setUnderlag(List.of());

      var response = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, response);
   }

   @Test
   void should_return_404_on_get_with_unknown_id()
   {
      getHandlaggning(UUID.randomUUID(), 404);
   }

   @Test
   void should_return_handlaggning_on_get()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getHandlaggning().getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.getHandlaggning().setVersion(handlaggningUpdate.getHandlaggning().getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getHandlaggning().getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put_with_uppgift_planerad()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setPlaneradTillTS(OffsetDateTime.now());
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.getHandlaggning().setVersion(handlaggningUpdate.getHandlaggning().getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getHandlaggning().getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put_with_uppgift_utford()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setUtfordTS(OffsetDateTime.now());
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.getHandlaggning().setVersion(handlaggningUpdate.getHandlaggning().getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getHandlaggning().getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put_with_handlaggning_avslutad()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().setAvslutadTS(OffsetDateTime.now());
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.getHandlaggning().setVersion(handlaggningUpdate.getHandlaggning().getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getHandlaggning().getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put_with_uppgift_regelutfall()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      var regelutfall = new Regelutfall();
      regelutfall.setVarde("varde");
      handlaggningUpdate.getUppgift().setRegelutfall(regelutfall);
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.getHandlaggning().setVersion(handlaggningUpdate.getHandlaggning().getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getHandlaggning().getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put_with_uppgift_utforare_null()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setUtforare(null);
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.getHandlaggning().setVersion(handlaggningUpdate.getHandlaggning().getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getHandlaggning().getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put_with_yrkande_beslut_empty()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getHandlaggning().getYrkande().setBeslut(List.of());
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.getHandlaggning().setVersion(handlaggningUpdate.getHandlaggning().getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getHandlaggning().getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put_with_uppgift_uppgiftstatus_null()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setUppgiftStatus(null);
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.getHandlaggning().setVersion(handlaggningUpdate.getHandlaggning().getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getHandlaggning().getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put_with_uppgift_kommentar_null()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setKommentar(null);
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.getHandlaggning().setVersion(handlaggningUpdate.getHandlaggning().getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getHandlaggning().getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }
}
