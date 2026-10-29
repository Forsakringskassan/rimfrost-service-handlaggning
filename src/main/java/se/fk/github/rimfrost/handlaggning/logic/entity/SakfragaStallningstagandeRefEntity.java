package se.fk.github.rimfrost.handlaggning.logic.entity;

import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface SakfragaStallningstagandeRefEntity
{
   UUID id();

   int version();
}
