package se.fk.github.rimfrost.handlaggning.logic.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.immutables.value.Value;

@Value.Immutable
public interface YrkandeCreateRequest
{
   public List<YrkandePerson> person();

   public String formanstyp();

   public OffsetDateTime start();

   public OffsetDateTime slut();

   public List<YrkandeErsattning> ersattning();
}
