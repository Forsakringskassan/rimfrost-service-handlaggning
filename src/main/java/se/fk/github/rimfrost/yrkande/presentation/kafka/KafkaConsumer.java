package se.fk.github.rimfrost.yrkande.presentation.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import se.fk.github.logging.callerinfo.model.MDCKeys;
import se.fk.github.rimfrost.yrkande.logic.service.impl.HandlaggningServiceImpl;
import se.fk.rimfrost.VahHandlaggningResponseMessagePayload;
import java.util.UUID;

@SuppressWarnings("unused")
@ApplicationScoped
public class KafkaConsumer
{

   private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

   @Inject
   HandlaggningServiceImpl handlaggningService;

   @SuppressWarnings("unused")
   @Incoming("vah-handlaggning-responses")
   public void onVahHandlaggningResponse(VahHandlaggningResponseMessagePayload vahHandlaggningResponseMessagePayload)
   {
      MDC.put(MDCKeys.PROCESSID.name(), vahHandlaggningResponseMessagePayload.getData().getHandlaggningId());
      LOGGER.info(
            "VahHandlaggningResponseMessagePayload received with HandlaggningId: "
                  + vahHandlaggningResponseMessagePayload.getData().getHandlaggningId());
      handlaggningService.sendHandlaggningDoneMessage(
            UUID.fromString(vahHandlaggningResponseMessagePayload.getData().getHandlaggningId()));
   }

}
