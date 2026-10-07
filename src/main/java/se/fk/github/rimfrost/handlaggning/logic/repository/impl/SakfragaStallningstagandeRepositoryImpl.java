package se.fk.github.rimfrost.handlaggning.logic.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.rimfrost.handlaggning.logic.entity.SakfragaStallningstagandeEntity;
import se.fk.github.rimfrost.handlaggning.logic.repository.SakfragaStallningstagandeRepository;

@ApplicationScoped
public class SakfragaStallningstagandeRepositoryImpl implements SakfragaStallningstagandeRepository
{

   private final Map<UUID, SakfragaStallningstagandeEntity> store = new ConcurrentHashMap<>();

   @Override
   public SakfragaStallningstagandeEntity save(SakfragaStallningstagandeEntity e)
   {
      store.put(e.id(), e);
      return e;
   }

   @Override
   public List<SakfragaStallningstagandeEntity> save(List<SakfragaStallningstagandeEntity> list)
   {
      list.stream().forEach(e -> store.put(e.id(), e));
      return list;
   }

   @Override
   public Optional<SakfragaStallningstagandeEntity> findById(UUID id)
   {
      return Optional.ofNullable(store.get(id));
   }

}
