package se.fk.github.rimfrost.handlaggning.logic.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.rimfrost.handlaggning.logic.entity.ProduceratResultatEntity;
import se.fk.github.rimfrost.handlaggning.logic.repository.ProduceratResultatRepository;

@ApplicationScoped
public class ProduceratResultatRepositoryImpl implements ProduceratResultatRepository
{

   private final Map<UUID, ProduceratResultatEntity> store = new ConcurrentHashMap<>();

   @Override
   public ProduceratResultatEntity save(ProduceratResultatEntity e)
   {
      store.put(e.id(), e);
      return e;
   }

   @Override
   public List<ProduceratResultatEntity> save(List<ProduceratResultatEntity> list)
   {
      list.stream().forEach(e -> store.put(e.id(), e));
      return list;
   }

   @Override
   public Optional<ProduceratResultatEntity> findById(UUID id)
   {
      return Optional.ofNullable(store.get(id));
   }

}
