package se.fk.github.rimfrost.handlaggning.logic.service;

import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.github.rimfrost.handlaggning.logic.dto.HandlaggningCreateResponse;
import java.util.UUID;

public interface HandlaggningService
{
   HandlaggningGetResponse getHandlaggning(HandlaggningGetRequest request);

   HandlaggningPutResponse putHandlaggning(HandlaggningPutRequest request);

   void sendHandlaggningDoneMessage(UUID handlaggningID);
}
