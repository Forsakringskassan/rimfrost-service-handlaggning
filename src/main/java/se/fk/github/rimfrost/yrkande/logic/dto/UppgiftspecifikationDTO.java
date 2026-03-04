package se.fk.github.rimfrost.yrkande.logic.dto;

import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface UppgiftspecifikationDTO
{
   UUID id();

   String version();

   String namn();

   String uppgiftbeskrivning();

   VerksamhetslogikDTO verksamhetslogik();

   RollDTO roll();

   String applikationsId();

   String applikationsVersion();

   RegelDTO regel();

   String uppgiftsGui();
}
