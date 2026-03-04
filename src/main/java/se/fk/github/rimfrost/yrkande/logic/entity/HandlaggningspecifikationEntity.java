package se.fk.github.rimfrost.yrkande.logic.entity;

import java.util.List;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface HandlaggningspecifikationEntity
{
   UUID id();

   String version();

   String bpmn();

   String namn();

   String beskrivning();

   @Value.Default
   default List<UppgiftspecifikationEntity> uppgiftspecifikation()
   {
      return List.of();
   }
}
