package se.fk.github.rimfrost.yrkande.logic.entity;

import java.util.UUID;
import org.immutables.value.Value;
import se.fk.github.rimfrost.yrkande.logic.enums.RollEntity;

@Value.Immutable
public interface YrkanderollEntity
{
   UUID id();

   IndividEntity individ();

   RollEntity roll();

   boolean yrkande();
}
