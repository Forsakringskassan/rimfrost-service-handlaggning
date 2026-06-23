package se.fk.github.rimfrost.handlaggning.logic.service;

import se.fk.github.rimfrost.handlaggning.logic.dto.*;

public interface HandlaggningService
{
   HandlaggningGetResponse getHandlaggning(HandlaggningGetRequest request);

   HandlaggningPutResponse putHandlaggning(HandlaggningPutRequest request);
}
