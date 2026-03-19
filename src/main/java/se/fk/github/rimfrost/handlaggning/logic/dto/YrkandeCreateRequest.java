package se.fk.github.rimfrost.handlaggning.logic.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.immutables.value.Value;

@Value.Immutable
public interface YrkandeCreateRequest
{

   UUID erbjudandedId();

   public OffsetDateTime yrkandeFrom();

   public OffsetDateTime yrkandeTom();

   public List<IndividYrkandeRollCreateRequest> individYrkandeRoller();

   public List<ProduceratResultatCreateRequest> produceradeResultat();

}
