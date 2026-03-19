package se.fk.github.rimfrost.handlaggning.logic.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import se.fk.github.rimfrost.handlaggning.logic.entity.IndividYrkandeRollEntity;

public interface IndividYrkandeRollRepository
{

   IndividYrkandeRollEntity save(IndividYrkandeRollEntity e);

   List<IndividYrkandeRollEntity> save(List<IndividYrkandeRollEntity> list);

   Optional<IndividYrkandeRollEntity> findById(UUID id);

}
