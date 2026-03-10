package se.fk.github.rimfrost.handlaggning.logic.repository;

import se.fk.github.rimfrost.handlaggning.logic.entity.ProduktvariantEntity;
import se.fk.github.rimfrost.handlaggning.logic.enums.BeloppstypEntity;
import se.fk.github.rimfrost.handlaggning.logic.enums.BerakningsgrundEntity;

import java.util.Optional;
import java.util.UUID;

public interface ProduktvariantRepository
{
   Optional<ProduktvariantEntity> findById(UUID id);

   Optional<BerakningsgrundEntity> findBerakningsgrundById(UUID produktvariantId);

   Optional<BeloppstypEntity> findBeloppstypById(UUID produktvariantId);
}
