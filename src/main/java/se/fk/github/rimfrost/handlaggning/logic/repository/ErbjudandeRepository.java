package se.fk.github.rimfrost.handlaggning.logic.repository;

import se.fk.github.rimfrost.handlaggning.logic.entity.ErbjudandeFlowInfoEntity;

import java.util.Optional;

public interface ErbjudandeRepository
{
   Optional<ErbjudandeFlowInfoEntity> getErbjudandeFlowInfoByErbjudandetyp(String erbjudandetyp);
}
