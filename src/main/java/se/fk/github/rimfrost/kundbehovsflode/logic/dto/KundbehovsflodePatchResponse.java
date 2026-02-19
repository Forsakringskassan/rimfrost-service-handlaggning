package se.fk.github.rimfrost.kundbehovsflode.logic.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface KundbehovsflodePatchResponse
{
   KundbehovsflodeDTO kundbehovsflode();
}
