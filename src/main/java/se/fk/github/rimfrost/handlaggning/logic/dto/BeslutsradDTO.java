package se.fk.github.rimfrost.handlaggning.logic.dto;

import org.immutables.value.Value;

import java.util.List;
import java.util.UUID;

@Value.Immutable
public interface BeslutsradDTO
{
   UUID id();

   int version();

   UUID beslutsTyp();

   UUID beslutsUtfall();

   UUID avslutsTyp();

   List<ProduceratResultatRefDTO> produceratResultatRefs();
}
