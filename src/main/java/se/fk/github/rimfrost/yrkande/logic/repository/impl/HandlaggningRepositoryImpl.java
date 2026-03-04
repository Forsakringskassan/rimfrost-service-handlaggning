package se.fk.github.rimfrost.yrkande.logic.repository.impl;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.rimfrost.yrkande.logic.entity.HandlaggningEntity;
import se.fk.github.rimfrost.yrkande.logic.repository.HandlaggningRepository;

@SuppressWarnings("unused")
@ApplicationScoped
public class HandlaggningRepositoryImpl implements HandlaggningRepository
{
   private final Map<UUID, HandlaggningEntity> store = new ConcurrentHashMap<>();

   @Override
   public HandlaggningEntity save(HandlaggningEntity entity)
   {
      store.put(entity.id(), entity);
      return entity;
   }

   @Override
   public Optional<HandlaggningEntity> findById(UUID id)
   {
      return Optional.ofNullable(store.get(id));
   }

}
