package se.fk.github.rimfrost.yrkande.logic.service;

import se.fk.github.rimfrost.yrkande.logic.dto.*;
import se.fk.github.rimfrost.yrkande.logic.dto.HandlaggningCreateResponse;
import java.util.UUID;

public interface HandlaggningService
{
   HandlaggningCreateResponse createHandlaggning(HandlaggningCreateRequest request);

   HandlaggningGetResponse getHandlaggning(HandlaggningGetRequest request);

   HandlaggningPutResponse putHandlaggning(HandlaggningPutRequest request);

   HandlaggningPatchResponse patchHandlaggning(HandlaggningPatchRequest request);

   void sendHandlaggningDoneMessage(UUID handlaggningID);
}
