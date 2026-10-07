package se.fk.github.rimfrost.handlaggning;

import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslut;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Beslutsrad;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Handlaggning;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.HandlaggningUpdate;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Idtyp;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.RollIYrkande;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.SakfragaStallningstagandeContainer;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.SakfragaStallningstagandeRef;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Underlag;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgift;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UppgiftSpecifikation;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Uppgiftsdata;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.Yrkande;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class HandlaggningTestData
{
   public static HandlaggningUpdate createHandlaggningUpdate()
   {
      Handlaggning handlaggning = createHandlaggning();

      HandlaggningUpdate handlaggningUpdate = new HandlaggningUpdate();
      handlaggningUpdate.setHandlaggning(handlaggning);
      handlaggningUpdate.setUppgift(createUppgift(handlaggning.getId()));

      return handlaggningUpdate;
   }

   private static Handlaggning createHandlaggning()
   {
      Handlaggning handlaggning = new Handlaggning();

      handlaggning.setId(UUID.randomUUID());
      handlaggning.setVersion(1);
      handlaggning.setYrkande(createYrkande());
      handlaggning.setHandlaggningIdTyp(UUID.randomUUID().toString());
      handlaggning.setHandlaggningIdVarde(UUID.randomUUID().toString());
      handlaggning.setSkapadTS(OffsetDateTime.now());
      handlaggning.setHandlaggningspecifikationId(UUID.randomUUID());

      return handlaggning;
   }

   private static Yrkande createYrkande()
   {
      UUID yrkandeId = UUID.randomUUID();

      Idtyp idtyp = new Idtyp();
      idtyp.setTypId(UUID.randomUUID().toString());
      idtyp.setVarde(UUID.randomUUID().toString());

      RollIYrkande rollIYrkande = new RollIYrkande();
      rollIYrkande.setId(UUID.randomUUID());
      rollIYrkande.setIndivid(idtyp);
      rollIYrkande.setYrkandeRollId(UUID.randomUUID().toString());
      rollIYrkande.setAvserYrkande(yrkandeId);

      SakfragaStallningstagandeContainer sakfragaStallningstagande = new SakfragaStallningstagandeContainer();
      sakfragaStallningstagande.setId(UUID.randomUUID());
      sakfragaStallningstagande.setObjektTypId(UUID.randomUUID().toString());
      sakfragaStallningstagande.setData("{}");

      SakfragaStallningstagandeRef sakfragaStallningstagandeRef = new SakfragaStallningstagandeRef();
      sakfragaStallningstagandeRef.setId(sakfragaStallningstagande.getId());
      sakfragaStallningstagandeRef.setVersion(1);

      Beslutsrad beslutsrad = new Beslutsrad();
      beslutsrad.setId(UUID.randomUUID());
      beslutsrad.setVersion(1);
      beslutsrad.setAvslutsTyp(UUID.randomUUID().toString());
      beslutsrad.setBeslutsTyp(UUID.randomUUID().toString());
      beslutsrad.setBeslutsUtfall(UUID.randomUUID().toString());
      beslutsrad.setSakfragorStallningstaganden(List.of(sakfragaStallningstagandeRef));

      Beslut beslut = new Beslut();
      beslut.setId(UUID.randomUUID());
      beslut.setVersion(1);
      beslut.setDatum(OffsetDateTime.now());
      beslut.setBeslutsfattare(idtyp);
      beslut.setBeslutsrader(List.of(beslutsrad));

      Yrkande yrkande = new Yrkande();
      yrkande.setId(yrkandeId);
      yrkande.setVersion(1);
      yrkande.setIngangtypId(UUID.randomUUID().toString());
      yrkande.setYrkandedatum(OffsetDateTime.now());
      yrkande.setYrkandestatus(UUID.randomUUID().toString());
      yrkande.setYrkandeFrom(OffsetDateTime.now());
      yrkande.setYrkandeTom(OffsetDateTime.now());
      yrkande.setAvsikt(UUID.randomUUID().toString());
      yrkande.setRollerIYrkande(List.of(rollIYrkande));
      yrkande.setSakfragorStallningstaganden(List.of(sakfragaStallningstagande));
      yrkande.setBeslut(List.of(beslut));

      return yrkande;
   }

   private static Uppgift createUppgift(UUID handlaggningId)
   {
      Idtyp idtyp = new Idtyp();
      idtyp.setTypId(UUID.randomUUID().toString());
      idtyp.setVarde(UUID.randomUUID().toString());

      UppgiftSpecifikation uppgiftSpecifikation = new UppgiftSpecifikation();
      uppgiftSpecifikation.setId(UUID.randomUUID());
      uppgiftSpecifikation.setVersion(1);

      Uppgift uppgift = new Uppgift();
      uppgift.setId(UUID.randomUUID());
      uppgift.setVersion(1);
      uppgift.setSkapadTS(OffsetDateTime.now());
      uppgift.setAktivitetId(UUID.randomUUID());
      uppgift.setUppgiftspecifikation(uppgiftSpecifikation);
      uppgift.setUppgiftStatus(UUID.randomUUID().toString());
      uppgift.setKommentar(UUID.randomUUID().toString());
      uppgift.setFsSAinformation(UUID.randomUUID().toString());
      uppgift.setUtforare(idtyp);
      uppgift.setHandlaggningId(handlaggningId);
      uppgift.setUnderlag(List.of(createUnderlag()));
      uppgift.setResultat(List.of(createUppgiftsdata()));

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

   private static Uppgiftsdata createUppgiftsdata()
   {
      Uppgiftsdata uppgiftsdata = new Uppgiftsdata();
      uppgiftsdata.setInformationsobjektId(UUID.randomUUID().toString());
      uppgiftsdata.setVersion(1);

      return uppgiftsdata;
   }
}
