package se.fk.github.rimfrost.yrkande.logic.repository;

import java.util.Optional;
import java.util.UUID;
import se.fk.github.rimfrost.yrkande.logic.entity.HandlaggningEntity;

public interface HandlaggningRepository
{
   HandlaggningEntity save(HandlaggningEntity flode);

   Optional<HandlaggningEntity> findById(UUID id);
}
