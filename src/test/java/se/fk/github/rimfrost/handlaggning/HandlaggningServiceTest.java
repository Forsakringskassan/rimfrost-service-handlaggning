package se.fk.github.rimfrost.handlaggning;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

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
   void should_create_handlaggning_on_put_with_unknown_id_and_process_id_uppgift_null()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.setUppgift(null);
      handlaggningUpdate.setProcessinstansId(null);
      handlaggningUpdate.setUnderlag(List.of());

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

      var getResponse = getHandlaggning(handlaggningUpdate.getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.setVersion(handlaggningUpdate.getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put_with_uppgift_planerad()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setPlaneradTs(OffsetDateTime.now());
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.setVersion(handlaggningUpdate.getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put_with_uppgift_utford()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getUppgift().setUtfordTs(OffsetDateTime.now());
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.setVersion(handlaggningUpdate.getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put_with_handlaggning_avslutad()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.setAvslutadTS(OffsetDateTime.now());
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.setVersion(handlaggningUpdate.getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }

   @Test
   void should_update_existing_handlaggning_on_put_with_producerat_resultat_avslagsanledning()
   {
      var handlaggningUpdate = createHandlaggningUpdate();
      handlaggningUpdate.getYrkande().getProduceradeResultat().getFirst().setAvslagsanledning("avslagsanledning");
      var updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      handlaggningUpdate.setVersion(handlaggningUpdate.getVersion() + 1);
      updateResponse = sendHandlaggningUpdate(handlaggningUpdate);
      verifyHandlaggningUpdateResponse(handlaggningUpdate, updateResponse);

      var getResponse = getHandlaggning(handlaggningUpdate.getId());
      verifyHandlaggningGetResponse(handlaggningUpdate, getResponse);
   }
}
