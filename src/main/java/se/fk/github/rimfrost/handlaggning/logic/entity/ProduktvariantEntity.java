package se.fk.github.rimfrost.handlaggning.logic.entity;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface ProduktvariantEntity
{
   UUID id();

   String namn();
}
