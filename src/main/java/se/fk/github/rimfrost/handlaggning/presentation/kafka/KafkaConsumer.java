package se.fk.github.rimfrost.handlaggning.presentation.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import se.fk.github.logging.callerinfo.model.MDCKeys;
import se.fk.github.rimfrost.handlaggning.logic.service.impl.HandlaggningServiceImpl;
import se.fk.rimfrost.HandlaggningResponseMessagePayload;
import java.util.UUID;

@SuppressWarnings("unused")
@ApplicationScoped
public class KafkaConsumer
{

   private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

   @Inject
   HandlaggningServiceImpl handlaggningService;

   @SuppressWarnings("unused")
   @Incoming("handlaggning-responses")
   public void onHandlaggningResponse(HandlaggningResponseMessagePayload handlaggningResponseMessagePayload)
   {
      MDC.put(MDCKeys.PROCESSID.name(), handlaggningResponseMessagePayload.getData().getHandlaggningId());
      LOGGER.info(
            "HandlaggningResponseMessagePayload received with HandlaggningId: "
                  + handlaggningResponseMessagePayload.getData().getHandlaggningId());
      handlaggningService.sendHandlaggningDoneMessage(
            UUID.fromString(handlaggningResponseMessagePayload.getData().getHandlaggningId()));
   }

}
