package se.fk.github.rimfrost.handlaggning.logic.entity;

import java.util.UUID;
import org.immutables.value.Value;
import se.fk.github.rimfrost.handlaggning.logic.enums.VerksamhetslogikEntity;

@Value.Immutable
public interface UppgiftspecifikationEntity
{
   UUID id();

   String version();

   String namn();

   String uppgiftbeskrivning();

   VerksamhetslogikEntity verksamhetslogik();

   RollEntity roll();

   String applikationsId();

   String applikationsVersion();

   RegelEntity regel();

   String uppgiftsGui();
}
