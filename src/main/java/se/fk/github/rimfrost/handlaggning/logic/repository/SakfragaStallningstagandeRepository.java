package se.fk.github.rimfrost.handlaggning.logic.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import se.fk.github.rimfrost.handlaggning.logic.entity.SakfragaStallningstagandeContainerEntity;

public interface SakfragaStallningstagandeRepository
{

   SakfragaStallningstagandeContainerEntity save(SakfragaStallningstagandeContainerEntity e);

   List<SakfragaStallningstagandeContainerEntity> save(List<SakfragaStallningstagandeContainerEntity> list);

   Optional<SakfragaStallningstagandeContainerEntity> findById(UUID id);

}
