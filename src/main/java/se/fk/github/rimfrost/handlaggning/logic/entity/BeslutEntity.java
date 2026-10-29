package se.fk.github.rimfrost.handlaggning.logic.entity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface BeslutEntity
{
   UUID id();

   int version();

   OffsetDateTime datum();

   IdtypEntity beslutsfattare();

   List<BeslutsradEntity> beslutsrader();
}
