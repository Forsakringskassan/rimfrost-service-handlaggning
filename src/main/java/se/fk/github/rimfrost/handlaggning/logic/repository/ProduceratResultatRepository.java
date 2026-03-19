package se.fk.github.rimfrost.handlaggning.logic.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import se.fk.github.rimfrost.handlaggning.logic.entity.ProduceratResultatEntity;

public interface ProduceratResultatRepository
{

   ProduceratResultatEntity save(ProduceratResultatEntity e);

   List<ProduceratResultatEntity> save(List<ProduceratResultatEntity> list);

   Optional<ProduceratResultatEntity> findById(UUID id);

}
