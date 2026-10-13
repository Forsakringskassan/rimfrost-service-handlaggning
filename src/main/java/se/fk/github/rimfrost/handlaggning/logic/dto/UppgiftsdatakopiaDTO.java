package se.fk.github.rimfrost.handlaggning.logic.dto;

import org.immutables.value.Value;

@Value.Immutable
public non-sealed interface UppgiftsdatakopiaDTO extends UppgiftsdataDTO
{
   String informationsobjekttyp();

   String data();
}
