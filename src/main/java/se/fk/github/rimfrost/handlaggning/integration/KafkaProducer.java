package se.fk.github.rimfrost.handlaggning.integration;

import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Message;
import se.fk.rimfrost.*;

@ApplicationScoped
public class KafkaProducer
{

   @Channel("handlaggning-requests")
   Emitter<HandlaggningRequestMessagePayload> emitter;

   public void sendRequestMessage(String topic, String requestType, UUID handlaggningId)
   {
      var data = new HandlaggningRequestMessageData();
      data.setHandlaggningId(handlaggningId.toString());

      var payload = new HandlaggningRequestMessagePayload();
      payload.setId(handlaggningId.toString());
      payload.setData(data);
      payload.setType(requestType);
      payload.setSource("/service/handlaggning");
      payload.setTime(OffsetDateTime.now());
      payload.setSpecversion(SpecVersion.NUMBER_1_DOT_0);
      payload.setKogitoproctype(KogitoProcType.BPMN);

      var metadata = OutgoingKafkaRecordMetadata.builder()
            .withTopic(topic)
            .build();
      var message = Message.of(payload).addMetadata(metadata);

      emitter.send(message);
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
