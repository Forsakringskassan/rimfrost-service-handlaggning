package se.fk.github.rimfrost.kundbehovsflode.presentation;

import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeCreateRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeCreateResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeGetRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodeGetResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePutRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePutResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePatchRequest;
import se.fk.github.rimfrost.kundbehovsflode.logic.dto.KundbehovsflodePatchResponse;
import se.fk.github.rimfrost.kundbehovsflode.logic.service.KundbehovsflodeService;
import se.fk.github.rimfrost.kundbehovsflode.presentation.util.PresentationMapper;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.KundbehovsflodeControllerApi;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.GetKundbehovsflodeResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostKundbehovsflodeRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostKundbehovsflodeResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutKundbehovsflodeRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutKundbehovsflodeResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PatchKundbehovsflodeResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UpdateErsattning;

@ApplicationScoped
@Path("/kundbehovsflode")
public class KundbehovsflodeController implements KundbehovsflodeControllerApi
{
   @Inject
   KundbehovsflodeService kundbehovflodesService;

   @Inject
   PresentationMapper mapper;

   @Override
   @GET
   @Path("/{kundbehovsflodeId}")
   @Produces(
   {
         "application/json"
   })
   public GetKundbehovsflodeResponse getKundbehovsflode(UUID kundbehovsflodeId)
   {
      KundbehovsflodeGetRequest kundbehovsflodeGetRequest = mapper.toKundbehovsflodeGetRequest(kundbehovsflodeId);
      KundbehovsflodeGetResponse kundbehovsflodeGetResponse = kundbehovflodesService
            .getKundbehovsflode(kundbehovsflodeGetRequest);
      GetKundbehovsflodeResponse response = mapper.toGetKundbehovsflodeResponse(kundbehovsflodeGetResponse);
      return response;
   }

   @Override
   @POST
   @Consumes(
   {
         "application/json"
   })
   @Produces(
   {
         "application/json"
   })
   public PostKundbehovsflodeResponse postKundbehovsflode(PostKundbehovsflodeRequest postKundbehovsflodeRequest)
   {
      KundbehovsflodeCreateRequest kundbehovsflodeCreateRequest = mapper
            .toKundbehovsflodeCreateRequest(postKundbehovsflodeRequest);
      KundbehovsflodeCreateResponse kundbehovsflodeCreateResponse = kundbehovflodesService
            .createKundbehovsflode(kundbehovsflodeCreateRequest);
      PostKundbehovsflodeResponse response = mapper.toPostKundbehovsflodeResponse(kundbehovsflodeCreateResponse);
      return response;
   }

   @Override
   @PUT
   @Path("/{kundbehovsflodeId}")
   @Consumes(
   {
         "application/json"
   })
   @Produces(
   {
         "application/json"
   })
   public PutKundbehovsflodeResponse putKundbehovsflode(UUID kundbehovsflodeId,
         PutKundbehovsflodeRequest putKundbehovsflodeRequest)
   {
      KundbehovsflodePutRequest kundbehovsflodePutRequest = mapper
            .toKundbehovsflodePutRequest(kundbehovsflodeId, putKundbehovsflodeRequest);
      KundbehovsflodePutResponse kundbehovsflodePutResponse = kundbehovflodesService
            .putKundbehovsflode(kundbehovsflodePutRequest);
      PutKundbehovsflodeResponse response = mapper.toPutKundbehovsflodeResponse(kundbehovsflodePutResponse);
      return response;
   }

   @Override
   @PATCH
   @Path("/{kundbehovsflodeId}")
   @Consumes(
   {
         "application/json"
   })
   @Produces(
   {
         "application/json"
   })
   public PatchKundbehovsflodeResponse patchKundbehovsflode(UUID kundbehovsflodeId,
         List<UpdateErsattning> updateErsattning)
   {
      KundbehovsflodePatchRequest kundbehovsflodePatchRequest = mapper.toKundbehovsflodePatchRequest(kundbehovsflodeId,
            updateErsattning);
      KundbehovsflodePatchResponse kundbehovsflodePatchResponse = kundbehovflodesService
            .patchKundbehovsflode(kundbehovsflodePatchRequest);
      return mapper.toPatchKundbehovsflodeResponse(kundbehovsflodePatchResponse);
   }
}
