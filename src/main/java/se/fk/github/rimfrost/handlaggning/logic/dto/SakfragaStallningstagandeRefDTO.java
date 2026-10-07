package se.fk.github.rimfrost.handlaggning.logic.dto;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface SakfragaStallningstagandeRefDTO
{
   UUID id();

   int version();
}
