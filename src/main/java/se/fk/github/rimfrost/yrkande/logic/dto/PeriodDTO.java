package se.fk.github.rimfrost.yrkande.logic.dto;

import java.time.OffsetDateTime;
import org.immutables.value.Value;

@Value.Immutable
public interface PeriodDTO
{
   OffsetDateTime start();

   OffsetDateTime slut();
}
