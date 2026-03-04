package se.fk.github.rimfrost.yrkande.logic.entity;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.immutables.value.Value;
import se.fk.github.rimfrost.yrkande.logic.enums.ErsattningsstatusEntity;

@Value.Immutable
public interface ProduceratResultatEntity
{
   UUID id();

   String version();

   OffsetDateTime franOchMed();

   OffsetDateTime tillOchMed();

   ErsattningsstatusEntity status();
}
