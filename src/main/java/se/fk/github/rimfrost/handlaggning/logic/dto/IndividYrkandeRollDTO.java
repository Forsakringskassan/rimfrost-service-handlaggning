package se.fk.github.rimfrost.handlaggning.logic.dto;

import java.util.UUID;

import org.immutables.value.Value;

@Value.Immutable
public interface IndividYrkandeRollDTO
{

   UUID individId();

   UUID yrkandeRollId();

}
