package se.fk.github.rimfrost.handlaggning.logic.repository;

import se.fk.github.rimfrost.handlaggning.logic.entity.ErbjudandeFlowInfoEntity;

import java.util.Optional;
import java.util.UUID;

public interface ErbjudandeRepository
{
   Optional<ErbjudandeFlowInfoEntity> getErbjudandeFlowInfoById(UUID erbjudandeId);
}
