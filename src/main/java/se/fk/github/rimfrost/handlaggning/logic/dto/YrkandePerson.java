package se.fk.github.rimfrost.handlaggning.logic.dto;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface YrkandePerson
{
   public String persnr();

   public UUID roll();

   public boolean yrkande();
}
