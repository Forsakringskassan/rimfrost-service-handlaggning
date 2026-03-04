package se.fk.github.rimfrost.yrkande.logic.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface ProduceratResultatDTO
{
   UUID id();

   String version();

   OffsetDateTime franOchMed();

   OffsetDateTime tillOchMed();

   ErsattningsstatusDTO status();
}
