package se.fk.github.rimfrost.yrkande.logic.entity;

import org.immutables.value.Value;

@Value.Immutable
public interface IndividEntity
{
   String id();

   String fornamn();

   String efternamn();
}
