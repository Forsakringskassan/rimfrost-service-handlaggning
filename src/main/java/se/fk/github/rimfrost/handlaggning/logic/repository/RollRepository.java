package se.fk.github.rimfrost.handlaggning.logic.repository;

import se.fk.github.rimfrost.handlaggning.logic.entity.RollEntity;

import java.util.Optional;
import java.util.UUID;

public interface RollRepository
{
   Optional<RollEntity> findById(UUID id);
}
