package se.fk.github.rimfrost.yrkande.integration;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import jakarta.enterprise.context.ApplicationScoped;
import se.fk.rimfrost.*;

@ApplicationScoped
public class KafkaProducer
{

   @Channel("vah-handlaggning-requests")
   Emitter<VahHandlaggningRequestMessagePayload> vahEmitter;

   public void sendVahRequestMessage(UUID handlaggningId)
   {
      var data = new VahHandlaggningRequestMessageData();
      data.setHandlaggningId(handlaggningId.toString());

      var payload = new VahHandlaggningRequestMessagePayload();
      payload.setId(handlaggningId.toString());
      payload.setData(data);
      payload.setType("vah-handlaggning-requests");
      payload.setSource("/service/handlaggning");
      payload.setTime(OffsetDateTime.now());
      payload.setSpecversion(SpecVersion.NUMBER_1_DOT_0);
      payload.setKogitoproctype(KogitoProcType.BPMN);
      vahEmitter.send(payload);
   }

   @Channel("handlaggning-done")
   Emitter<HandlaggningDoneMessage> handlaggningDoneMessageEmitter;

   public void sendHandlaggningDone(UUID handlaggningId)
   {
      var payload = new HandlaggningDoneMessage();
      payload.setHandlaggningId(handlaggningId.toString());
      handlaggningDoneMessageEmitter.send(payload);
   }
}
