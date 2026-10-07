package se.fk.github.rimfrost.handlaggning.logic.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import se.fk.github.rimfrost.handlaggning.logic.entity.SakfragaStallningstagandeEntity;

public interface SakfragaStallningstagandeRepository
{

   SakfragaStallningstagandeEntity save(SakfragaStallningstagandeEntity e);

   List<SakfragaStallningstagandeEntity> save(List<SakfragaStallningstagandeEntity> list);

   Optional<SakfragaStallningstagandeEntity> findById(UUID id);

}
