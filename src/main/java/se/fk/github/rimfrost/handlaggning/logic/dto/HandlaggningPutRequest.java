package se.fk.github.rimfrost.handlaggning.logic.dto;

import org.immutables.value.Value;
import jakarta.annotation.Nullable;

@Value.Immutable
public interface HandlaggningPutRequest
{
   HandlaggningDTO handlaggning();

   @Nullable
   UppgiftDTO uppgift();
}
