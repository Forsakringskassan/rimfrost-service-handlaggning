package se.fk.github.rimfrost.kundbehovsflode.logic.service;

import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeCreateRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeCreateResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeGetRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeGetResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePutRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePutResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePatchRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePatchResponse;

import java.util.UUID;

public interface KundbehovsflodeService
{
   KundbehovsflodeCreateResponse createKundbehovsflode(KundbehovsflodeCreateRequest request);

   KundbehovsflodeGetResponse getKundbehovsflode(KundbehovsflodeGetRequest request);

   KundbehovsflodePutResponse putKundbehovsflode(KundbehovsflodePutRequest request);

   KundbehovsflodePatchResponse patchKundbehovsflode(KundbehovsflodePatchRequest request);

   void sendKundbehovsflodeDoneMessage(UUID kundbehovsflodeID);
}
