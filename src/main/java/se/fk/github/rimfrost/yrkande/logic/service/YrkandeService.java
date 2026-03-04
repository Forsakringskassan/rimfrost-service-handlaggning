package se.fk.github.rimfrost.yrkande.logic.service;

import se.fk.github.rimfrost.yrkande.logic.dto.YrkandeCreateRequest;
import se.fk.github.rimfrost.yrkande.logic.dto.YrkandeCreateResponse;
import se.fk.github.rimfrost.yrkande.logic.dto.YrkandeGetRequest;
import se.fk.github.rimfrost.yrkande.logic.dto.YrkandeGetResponse;

public interface YrkandeService
{
   YrkandeCreateResponse createYrkande(YrkandeCreateRequest request);

   YrkandeGetResponse getById(YrkandeGetRequest request);
}
