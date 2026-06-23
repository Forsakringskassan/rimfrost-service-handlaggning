package se.fk.github.rimfrost.handlaggning;

import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.HandlaggningUpdate;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Idtyp;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.IndividYrkandeRoll;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.ProduceratResultat;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Underlag;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgift;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftSpecifikation;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkande;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class HandlaggningTestData
{
   public static HandlaggningUpdate createHandlaggningUpdate()
   {
      HandlaggningUpdate handlaggningUpdate = new HandlaggningUpdate();

      handlaggningUpdate.setId(UUID.randomUUID());
      handlaggningUpdate.setVersion(1);
      handlaggningUpdate.setYrkande(createYrkande());
      handlaggningUpdate.setProcessinstansId(UUID.randomUUID());
      handlaggningUpdate.setSkapadTS(OffsetDateTime.now());
      handlaggningUpdate.setHandlaggningspecifikationId(UUID.randomUUID());
      handlaggningUpdate.underlag(List.of(createUnderlag()));
      handlaggningUpdate.setUppgift(createUppgift());

      return handlaggningUpdate;
   }

   private static Yrkande createYrkande()
   {
      Idtyp idtyp = new Idtyp();
      idtyp.setTypId(UUID.randomUUID().toString());
      idtyp.setVarde(UUID.randomUUID().toString());

      IndividYrkandeRoll individyrkandeRoll = new IndividYrkandeRoll();
      individyrkandeRoll.setIndivid(idtyp);
      individyrkandeRoll.setYrkandeRollId(UUID.randomUUID().toString());

      ProduceratResultat produceratResultat = new ProduceratResultat();
      produceratResultat.setId(UUID.randomUUID());
      produceratResultat.setVersion(1);
      produceratResultat.setFrom(OffsetDateTime.now());
      produceratResultat.setTom(OffsetDateTime.now());
      produceratResultat.setYrkandestatus(UUID.randomUUID().toString());
      produceratResultat.setTyp(UUID.randomUUID().toString());
      produceratResultat.setData("{}");

      Yrkande yrkande = new Yrkande();
      yrkande.setId(UUID.randomUUID());
      yrkande.setVersion(1);
      yrkande.setErbjudandeId(UUID.randomUUID().toString());
      yrkande.setYrkandedatum(OffsetDateTime.now());
      yrkande.setYrkandestatus(UUID.randomUUID().toString());
      yrkande.setYrkandeFrom(OffsetDateTime.now());
      yrkande.setYrkandeTom(OffsetDateTime.now());
      yrkande.setAvsikt(UUID.randomUUID().toString());
      yrkande.setIndividYrkandeRoller(List.of(individyrkandeRoll));
      yrkande.setProduceradeResultat(List.of(produceratResultat));

      return yrkande;
   }

   private static Uppgift createUppgift()
   {
      UppgiftSpecifikation uppgiftSpecifikation = new UppgiftSpecifikation();
      uppgiftSpecifikation.setId(UUID.randomUUID());
      uppgiftSpecifikation.setVersion(1);

      Uppgift uppgift = new Uppgift();
      uppgift.setId(UUID.randomUUID());
      uppgift.setVersion(1);
      uppgift.setSkapadTs(OffsetDateTime.now());
      uppgift.setAktivitetId(UUID.randomUUID());
      uppgift.setUppgiftspecifikation(uppgiftSpecifikation);
      uppgift.setUppgiftStatus(UUID.randomUUID().toString());
      uppgift.setFsSAinformation(UUID.randomUUID().toString());

      return uppgift;
   }

   private static Underlag createUnderlag()
   {
      Underlag underlag = new Underlag();
      underlag.setTyp("test");
      underlag.setVersion(1);
      underlag.setData("test");

      return underlag;
   }
}
