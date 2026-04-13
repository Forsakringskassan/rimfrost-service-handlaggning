package se.fk.github.rimfrost.handlaggning.logic.entity;

import org.immutables.value.Value;

@Value.Immutable
public interface IdtypEntity
{
   String typId();

   String varde();
}
