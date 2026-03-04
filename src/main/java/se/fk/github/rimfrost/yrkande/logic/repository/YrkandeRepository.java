package se.fk.github.rimfrost.yrkande.logic.repository;

import java.util.Optional;
import java.util.UUID;
import se.fk.github.rimfrost.yrkande.logic.entity.YrkandeEntity;

public interface YrkandeRepository
{
   YrkandeEntity save(YrkandeEntity e);

   Optional<YrkandeEntity> findById(UUID id);
}
