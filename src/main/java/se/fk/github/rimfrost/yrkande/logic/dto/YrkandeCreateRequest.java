package se.fk.github.rimfrost.yrkande.logic.dto;

import java.time.OffsetDateTime;
import org.immutables.value.Value;

@Value.Immutable
public interface YrkandeCreateRequest
{
   public String persnr();

   public String formanstyp();

   public OffsetDateTime start();

   public OffsetDateTime slut();
}
