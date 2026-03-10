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
import java.util.UUID;
import org.yaml.snakeyaml.Yaml;
import se.fk.github.rimfrost.handlaggning.logic.entity.ImmutableProduktvariantEntity;
import se.fk.github.rimfrost.handlaggning.logic.entity.ProduktvariantEntity;
import se.fk.github.rimfrost.handlaggning.logic.enums.BeloppstypEntity;
import se.fk.github.rimfrost.handlaggning.logic.enums.BerakningsgrundEntity;
import se.fk.github.rimfrost.handlaggning.logic.repository.ProduktvariantRepository;

@ApplicationScoped
@Startup
public class ProduktvariantRepositoryImpl implements ProduktvariantRepository
{
   private final Map<UUID, Produktvariant> produktvariantMap = new HashMap<>();

   @PostConstruct
   void init()
   {
      try (ScanResult scanResult = new ClassGraph().acceptPaths("data/produktvarianter").scan())
      {
         scanResult.getResourcesWithExtension("yaml").forEachInputStreamIgnoringIOException(new ResourceList.InputStreamConsumer()
         {
            @Override
            public void accept(Resource resource, InputStream inputStream)
            {
               Produktvariant produktvariant = new Yaml().loadAs(inputStream, Produktvariant.class);
               produktvariantMap.put(produktvariant.getId(), produktvariant);
            }
         });
      }
   }

   @Override
   public Optional<ProduktvariantEntity> findById(UUID id)
   {
      if (!produktvariantMap.containsKey(id))
      {
         return Optional.empty();
      }

      var produktvariant = produktvariantMap.get(id);
      var produktvariantEntity = ImmutableProduktvariantEntity.builder()
            .id(produktvariant.getId())
            .namn(produktvariant.getNamn())
            .build();
      return Optional.of(produktvariantEntity);
   }

   @Override
   public Optional<BerakningsgrundEntity> findBerakningsgrundById(UUID produktvariantId)
   {
      return produktvariantMap.containsKey(produktvariantId)
            ? Optional.of(produktvariantMap.get(produktvariantId).getBerakningsgrund())
            : Optional.empty();
   }

   @Override
   public Optional<BeloppstypEntity> findBeloppstypById(UUID produktvariantId)
   {
      return produktvariantMap.containsKey(produktvariantId)
            ? Optional.of(produktvariantMap.get(produktvariantId).getBeloppstyp())
            : Optional.empty();
   }

   public static class Produktvariant
   {
      private UUID id;
      private String namn;
      private BeloppstypEntity beloppstyp;
      private BerakningsgrundEntity berakningsgrund;

      public Produktvariant()
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

      public void setBeloppstyp(BeloppstypEntity beloppstyp)
      {
         this.beloppstyp = beloppstyp;
      }

      public void setBerakningsgrund(BerakningsgrundEntity berakningsgrund)
      {
         this.berakningsgrund = berakningsgrund;
      }

      public UUID getId()
      {
         return id;
      }

      public String getNamn()
      {
         return namn;
      }

      public BeloppstypEntity getBeloppstyp()
      {
         return beloppstyp;
      }

      public BerakningsgrundEntity getBerakningsgrund()
      {
         return berakningsgrund;
      }
   }
}
