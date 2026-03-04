package se.fk.github.rimfrost.yrkande.presentation.kafka;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import se.fk.rimfrost.VahHandlaggningResponseMessagePayload;

public class VahHandlaggningResponseMessagePayloadDeserializer
      extends ObjectMapperDeserializer<VahHandlaggningResponseMessagePayload>
{

   public VahHandlaggningResponseMessagePayloadDeserializer()
   {
      super(VahHandlaggningResponseMessagePayload.class);
   }

}
