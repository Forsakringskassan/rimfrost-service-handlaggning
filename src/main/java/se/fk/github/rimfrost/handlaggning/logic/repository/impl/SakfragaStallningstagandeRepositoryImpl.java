package se.fk.github.rimfrost.handlaggning.logic.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.rimfrost.handlaggning.logic.entity.SakfragaStallningstagandeContainerEntity;
import se.fk.github.rimfrost.handlaggning.logic.repository.SakfragaStallningstagandeRepository;

@ApplicationScoped
public class SakfragaStallningstagandeRepositoryImpl implements SakfragaStallningstagandeRepository
{

   private final Map<UUID, SakfragaStallningstagandeContainerEntity> store = new ConcurrentHashMap<>();

   @Override
   public SakfragaStallningstagandeContainerEntity save(SakfragaStallningstagandeContainerEntity e)
   {
      store.put(e.id(), e);
      return e;
   }

   @Override
   public List<SakfragaStallningstagandeContainerEntity> save(List<SakfragaStallningstagandeContainerEntity> list)
   {
      list.stream().forEach(e -> store.put(e.id(), e));
      return list;
   }

   @Override
   public Optional<SakfragaStallningstagandeContainerEntity> findById(UUID id)
   {
      return Optional.ofNullable(store.get(id));
   }

}
