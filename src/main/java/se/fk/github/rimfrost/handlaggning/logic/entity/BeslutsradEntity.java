package se.fk.github.rimfrost.handlaggning.logic.entity;

import java.util.List;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface BeslutsradEntity
{
   UUID id();

   int version();

   String beslutsTyp();

   String beslutsUtfall();

   String avslutsTyp();

   List<SakfragaStallningstagandeRefEntity> sakfragorStallningstagandeRefs();
}
