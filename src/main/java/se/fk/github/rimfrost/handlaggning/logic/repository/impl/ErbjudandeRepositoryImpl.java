package se.fk.github.rimfrost.handlaggning.logic.repository.impl;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.Resource;
import io.github.classgraph.ResourceList;
import io.github.classgraph.ScanResult;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.yaml.snakeyaml.Yaml;
import se.fk.github.rimfrost.handlaggning.logic.entity.ErbjudandeFlowInfoEntity;
import se.fk.github.rimfrost.handlaggning.logic.entity.ImmutableErbjudandeFlowInfoEntity;
import se.fk.github.rimfrost.handlaggning.logic.repository.ErbjudandeRepository;

@ApplicationScoped
@Startup
public class ErbjudandeRepositoryImpl implements ErbjudandeRepository
{
   private final Map<String, ErbjudandeFlowInfoEntity> erbjudandeFlowInfoMap = new HashMap<>();

   @PostConstruct
   void init()
   {
      try (ScanResult scanResult = new ClassGraph().acceptPaths("data/erbjudandeFlowInfo").scan())
      {
         scanResult.getResourcesWithExtension("yaml").forEachInputStreamIgnoringIOException(new ResourceList.InputStreamConsumer()
         {
            @Override
            public void accept(Resource resource, InputStream inputStream)
            {
               ErbjudandeFlowInfo erbjudandeFlowInfo = new Yaml().loadAs(inputStream, ErbjudandeFlowInfo.class);
               ErbjudandeFlowInfoEntity erbjudandeFlowInfoEntity = ImmutableErbjudandeFlowInfoEntity.builder()
                     .erbjudandetyp(erbjudandeFlowInfo.getErbjudandetyp())
                     .bpmn(erbjudandeFlowInfo.getBpmn())
                     .namn(erbjudandeFlowInfo.getNamn())
                     .beskrivning(erbjudandeFlowInfo.getBeskrivning())
                     .kafkaTopic(erbjudandeFlowInfo.getKafkaTopic())
                     .kafkaRequestType(erbjudandeFlowInfo.getKafkaRequestType())
                     .build();
               erbjudandeFlowInfoMap.put(erbjudandeFlowInfoEntity.erbjudandetyp(), erbjudandeFlowInfoEntity);
            }
         });
      }
   }

   @Override
   public Optional<ErbjudandeFlowInfoEntity> getErbjudandeFlowInfoByErbjudandetyp(String erbjudandetyp)
   {
      return erbjudandeFlowInfoMap.containsKey(erbjudandetyp) ? Optional.of(erbjudandeFlowInfoMap.get(erbjudandetyp))
            : Optional.empty();
   }

   public static class ErbjudandeFlowInfo
   {
      private String erbjudandetyp;
      private String bpmn;
      private String namn;
      private String beskrivning;
      private String kafkaTopic;
      private String kafkaRequestType;

      public ErbjudandeFlowInfo()
      {
      }

      public void setErbjudandetyp(String erbjudandetyp)
      {
         this.erbjudandetyp = erbjudandetyp;
      }

      public void setBpmn(String bpmn)
      {
         this.bpmn = bpmn;
      }

      public void setNamn(String namn)
      {
         this.namn = namn;
      }

      public void setBeskrivning(String beskrivning)
      {
         this.beskrivning = beskrivning;
      }

      public void setKafkaTopic(String kafkaTopic)
      {
         this.kafkaTopic = kafkaTopic;
      }

      public void setKafkaRequestType(String kafkaRequestType)
      {
         this.kafkaRequestType = kafkaRequestType;
      }

      public String getErbjudandetyp()
      {
         return erbjudandetyp;
      }

      public String getBpmn()
      {
         return bpmn;
      }

      public String getNamn()
      {
         return namn;
      }

      public String getBeskrivning()
      {
         return beskrivning;
      }

      public String getKafkaTopic()
      {
         return kafkaTopic;
      }

      public String getKafkaRequestType()
      {
         return kafkaRequestType;
      }
   }
}
