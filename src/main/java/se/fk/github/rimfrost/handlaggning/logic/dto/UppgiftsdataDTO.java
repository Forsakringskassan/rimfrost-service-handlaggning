package se.fk.github.rimfrost.handlaggning.logic.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface UppgiftsdataDTO
{
   String informationsobjektId();

   int version();
}
