package se.fk.github.rimfrost.handlaggning.logic.repository.impl;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.Resource;
import io.github.classgraph.ResourceList;
import io.github.classgraph.ScanResult;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.yaml.snakeyaml.Yaml;
import se.fk.github.rimfrost.handlaggning.logic.entity.ImmutableRollEntity;
import se.fk.github.rimfrost.handlaggning.logic.entity.RollEntity;
import se.fk.github.rimfrost.handlaggning.logic.repository.RollRepository;

@ApplicationScoped
@Startup
public class RollRepositoryImpl implements RollRepository
{
   private final Map<UUID, RollEntity> rollMap = new ConcurrentHashMap<>();

   @PostConstruct
   void init()
   {
      try (ScanResult scanResult = new ClassGraph().acceptPaths("data/roller").scan())
      {
         scanResult.getResourcesWithExtension("yaml").forEachInputStreamIgnoringIOException(new ResourceList.InputStreamConsumer()
         {
            @Override
            public void accept(Resource resource, InputStream inputStream)
            {
               Roll roll = new Yaml().loadAs(inputStream, Roll.class);
               var rollEntity = ImmutableRollEntity.builder()
                     .id(roll.getId())
                     .namn(roll.getNamn())
                     .version(roll.getVersion())
                     .build();
               rollMap.put(rollEntity.id(), rollEntity);
            }
         });
      }
   }

   @Override
   public Optional<RollEntity> findById(UUID id)
   {
      return rollMap.containsKey(id) ? Optional.of(rollMap.get(id)) : Optional.empty();
   }

   public static class Roll
   {
      private UUID id;
      private String namn;
      private String version;

      public Roll()
      {
      }

      public void setId(UUID id)
      {
         this.id = id;
      }

      public void setNamn(String namn)
      {
         this.namn = namn;
      }

      public void setVersion(String version)
      {
         this.version = version;
      }

      public UUID getId()
      {
         return id;
      }

      public String getNamn()
      {
         return namn;
      }

      public String getVersion()
      {
         return version;
      }
   }
}
