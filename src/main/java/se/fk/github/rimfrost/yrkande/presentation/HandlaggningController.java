package se.fk.github.rimfrost.yrkande.presentation;

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
import se.fk.github.rimfrost.yrkande.logic.dto.*;
import se.fk.github.rimfrost.yrkande.logic.dto.HandlaggningCreateRequest;
import se.fk.github.rimfrost.yrkande.logic.service.HandlaggningService;
import se.fk.github.rimfrost.yrkande.presentation.util.PresentationMapper;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.HandlaggningControllerApi;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.GetHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostHandlaggningRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutHandlaggningRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PatchHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.UpdateErsattning;

@SuppressWarnings("unused")
@ApplicationScoped
@Path("/handlaggning")
public class HandlaggningController implements HandlaggningControllerApi
{
   @Inject
   HandlaggningService handlaggningService;

   @Inject
   PresentationMapper mapper;

   @Override
   @GET
   @Path("/{handlaggningId}")
   @Produces(
   {
         "application/json"
   })
   public GetHandlaggningResponse getHandlaggning(UUID handlaggningId)
   {
      HandlaggningGetRequest handlaggningGetRequest = mapper.toHandlaggningGetRequest(handlaggningId);
      HandlaggningGetResponse handlaggningGetResponse = handlaggningService
            .getHandlaggning(handlaggningGetRequest);
      GetHandlaggningResponse response = mapper.toGetHandlaggningResponse(handlaggningGetResponse);
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
   public PostHandlaggningResponse postHandlaggning(PostHandlaggningRequest postHandlaggningRequest)
   {
      HandlaggningCreateRequest handlaggningCreateRequest = mapper
            .toHandlaggningCreateRequest(postHandlaggningRequest);
      HandlaggningCreateResponse handlaggningCreateResponse = handlaggningService
            .createHandlaggning(handlaggningCreateRequest);
      PostHandlaggningResponse response = mapper.toPostHandlaggningResponse(handlaggningCreateResponse);
      return response;
   }

   @Override
   @PUT
   @Path("/{handlaggningId}")
   @Consumes(
   {
         "application/json"
   })
   @Produces(
   {
         "application/json"
   })
   public PutHandlaggningResponse putHandlaggning(UUID handlaggningId,
         PutHandlaggningRequest putHandlaggningRequest)
   {
      HandlaggningPutRequest handlaggningPutRequest = mapper
            .toHandlaggningPutRequest(handlaggningId, putHandlaggningRequest);
      HandlaggningPutResponse handlaggningPutResponse = handlaggningService
            .putHandlaggning(handlaggningPutRequest);
      PutHandlaggningResponse response = mapper.toPutHandlaggningResponse(handlaggningPutResponse);
      return response;
   }

   @Override
   @PATCH
   @Path("/{handlaggningId}/ersattning")
   @Consumes(
   {
         "application/json"
   })
   @Produces(
   {
         "application/json"
   })
   public PatchHandlaggningResponse patchHandlaggning(UUID handlaggningId,
         List<UpdateErsattning> updateErsattning)
   {
      HandlaggningPatchRequest handlaggningPatchRequest = mapper.toHandlaggningPatchRequest(handlaggningId,
            updateErsattning);
      HandlaggningPatchResponse handlaggningPatchResponse = handlaggningService
            .patchHandlaggning(handlaggningPatchRequest);
      return mapper.toPatchHandlaggningResponse(handlaggningPatchResponse);
   }
}
