package se.fk.github.rimfrost.kundbehovsflode.logic.dto;

import java.util.List;
import java.util.UUID;

import org.immutables.value.Value;

@Value.Immutable
public interface KundbehovsflodePatchRequest
{
   UUID kundbehovsflodeId();

   List<UpdateErsattningDTO> updateErsattning();
}
