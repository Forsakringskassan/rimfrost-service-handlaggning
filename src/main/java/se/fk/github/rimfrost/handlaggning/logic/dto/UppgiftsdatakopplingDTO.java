package se.fk.github.rimfrost.handlaggning.logic.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface UppgiftsdatakopplingDTO extends UppgiftsdataDTO
{
   String informationsobjektId();

   int informationsobjektversion();
}
