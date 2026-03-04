package se.fk.github.rimfrost.yrkande.logic.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface HandlaggningPatchResponse
{
   HandlaggningDTO handlaggning();
}
