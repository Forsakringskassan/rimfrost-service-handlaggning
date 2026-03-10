package se.fk.github.rimfrost.handlaggning.logic.dto;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface YrkandeErsattning
{
   UUID produktvariantId();

   int omfattning();

   PeriodDTO period();

   PeriodiseringDTO periodisering();
}
