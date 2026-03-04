package se.fk.github.rimfrost.yrkande.logic.dto;

import java.util.List;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface HandlaggningspecifikationDTO
{
   UUID id();

   String version();

   String bpmn();

   String namn();

   String beskrivning();

   @Value.Default
   default List<UppgiftspecifikationDTO> uppgiftspecifikation()
   {
      return List.of();
   }
}
