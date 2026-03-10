package se.fk.github.rimfrost.handlaggning.logic.repository;

import se.fk.github.rimfrost.handlaggning.logic.entity.IndividEntity;

import java.util.Optional;

public interface IndividRepository
{
   Optional<IndividEntity> findByPersonnummer(String personnummer);
}
