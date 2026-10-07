package se.fk.github.rimfrost.handlaggning.logic.entity;

import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface SakfragaStallningstagandeEntity
{
   UUID id();

   String objektTypId();

   String data();
}
