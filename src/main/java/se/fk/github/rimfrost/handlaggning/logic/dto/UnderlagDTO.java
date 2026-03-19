package se.fk.github.rimfrost.handlaggning.logic.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface UnderlagDTO
{

   String typ();

   int version();

   String data();

}
