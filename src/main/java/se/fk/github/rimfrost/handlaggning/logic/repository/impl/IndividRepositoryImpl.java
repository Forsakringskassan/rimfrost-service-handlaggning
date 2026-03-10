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
import se.fk.github.rimfrost.handlaggning.logic.entity.ImmutableIndividEntity;
import se.fk.github.rimfrost.handlaggning.logic.entity.IndividEntity;
import se.fk.github.rimfrost.handlaggning.logic.repository.IndividRepository;

@ApplicationScoped
@Startup
public class IndividRepositoryImpl implements IndividRepository
{
   private final Map<String, IndividEntity> individEntityMap = new HashMap<>();

   @PostConstruct
   void init()
   {
      try (ScanResult scanResult = new ClassGraph().acceptPaths("data/individer").scan())
      {
         scanResult.getResourcesWithExtension("yaml").forEachInputStreamIgnoringIOException(new ResourceList.InputStreamConsumer()
         {
            @Override
            public void accept(Resource resource, InputStream inputStream)
            {
               Individ individ = new Yaml().loadAs(inputStream, Individ.class);
               IndividEntity individEntity = ImmutableIndividEntity.builder()
                     .id(individ.getId())
                     .fornamn(individ.getFornamn())
                     .efternamn(individ.getEfternamn())
                     .build();
               individEntityMap.put(individEntity.id(), individEntity);
            }
         });
      }
   }

   @Override
   public Optional<IndividEntity> findByPersonnummer(String personnummer)
   {
      return individEntityMap.containsKey(personnummer) ? Optional.of(individEntityMap.get(personnummer)) : Optional.empty();
   }

   public static class Individ
   {
      private String id;
      private String fornamn;
      private String efternamn;

      public Individ()
      {
      }

      public void setId(String id)
      {
         this.id = id;
      }

      public void setFornamn(String fornamn)
      {
         this.fornamn = fornamn;
      }

      public void setEfternamn(String efternamn)
      {
         this.efternamn = efternamn;
      }

      public String getId()
      {
         return id;
      }

      public String getFornamn()
      {
         return fornamn;
      }

      public String getEfternamn()
      {
         return efternamn;
      }
   }
}
