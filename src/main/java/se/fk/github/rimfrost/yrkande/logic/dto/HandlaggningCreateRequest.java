package se.fk.github.rimfrost.yrkande.logic.dto;

import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface HandlaggningCreateRequest
{
   UUID yrkandeId();
}
