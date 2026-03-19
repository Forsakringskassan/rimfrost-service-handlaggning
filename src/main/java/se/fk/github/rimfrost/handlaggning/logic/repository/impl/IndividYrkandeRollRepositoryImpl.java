package se.fk.github.rimfrost.handlaggning.logic.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.rimfrost.handlaggning.logic.entity.IndividYrkandeRollEntity;
import se.fk.github.rimfrost.handlaggning.logic.repository.IndividYrkandeRollRepository;

@ApplicationScoped
public class IndividYrkandeRollRepositoryImpl implements IndividYrkandeRollRepository
{

   private final Map<UUID, IndividYrkandeRollEntity> store = new ConcurrentHashMap<>();

   @Override
   public IndividYrkandeRollEntity save(IndividYrkandeRollEntity e)
   {
      store.put(e.id(), e);
      return e;
   }

   @Override
   public List<IndividYrkandeRollEntity> save(List<IndividYrkandeRollEntity> list)
   {
      list.stream().forEach(e -> store.put(e.id(), e));
      return list;
   }

   @Override
   public Optional<IndividYrkandeRollEntity> findById(UUID id)
   {
      return Optional.ofNullable(store.get(id));
   }

}
