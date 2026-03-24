package se.fk.github.rimfrost.handlaggning.presentation;

import java.util.List;
import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.github.rimfrost.handlaggning.logic.service.HandlaggningService;
import se.fk.github.rimfrost.handlaggning.logic.service.YrkandeService;
import se.fk.github.rimfrost.handlaggning.presentation.util.PresentationMapper;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.HandlaggningControllerApi;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.GetHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostHandlaggningRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostHandlaggningResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostYrkandeRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostYrkandeResponse;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutHandlaggningRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PutHandlaggningResponse;

@SuppressWarnings("unused")
@ApplicationScoped
@Path("")
public class HandlaggningController implements HandlaggningControllerApi
{
   @Inject
   HandlaggningService handlaggningService;

   @Inject
   YrkandeService yrkandeService;

   @Inject
   PresentationMapper mapper;

   @Override
   @GET
   @Path("/handlaggning/{handlaggningId}")
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
   @Path("/handlaggning")
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
   @Path("/handlaggning/{handlaggningId}")
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
   @POST
   @Path("/yrkande")
   @Consumes(
   {
         "application/json"
   })
   @Produces(
   {
         "application/json"
   })
   public PostYrkandeResponse postYrkande(PostYrkandeRequest postYrkandeRequest)
   {
      YrkandeCreateRequest yrkandeCreateRequest = mapper.toYrkandeCreateRequest(postYrkandeRequest);
      YrkandeCreateResponse yrkandeCreateResponse = yrkandeService.createYrkande(yrkandeCreateRequest);
      PostYrkandeResponse response = mapper.toPostYrkandeResponse(yrkandeCreateResponse);
      return response;
   }
}
