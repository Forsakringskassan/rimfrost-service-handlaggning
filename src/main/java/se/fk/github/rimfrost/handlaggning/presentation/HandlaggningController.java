package se.fk.github.rimfrost.handlaggning.presentation;

import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.fk.github.rimfrost.handlaggning.logic.dto.*;
import se.fk.github.rimfrost.handlaggning.logic.service.HandlaggningService;
import se.fk.github.rimfrost.handlaggning.presentation.util.PresentationMapper;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.HandlaggningControllerApi;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.GetHandlaggningResponse;
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
   PresentationMapper mapper;

   Logger logger = LoggerFactory.getLogger(HandlaggningController.class);

   @Override
   @GET
   @Path("/handlaggning/{handlaggningId}")
   @Produces(
   {
         "application/json"
   })
   public GetHandlaggningResponse getHandlaggning(UUID handlaggningId)
   {
      var handlaggningGetRequest = mapper.toHandlaggningGetRequest(handlaggningId);
      var handlaggningGetResponse = handlaggningService.getHandlaggning(handlaggningGetRequest);
      return mapper.toGetHandlaggningResponse(handlaggningGetResponse);
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
         @Valid @NotNull PutHandlaggningRequest putHandlaggningRequest)
   {
      HandlaggningPutRequest handlaggningPutRequest = mapper
            .toHandlaggningPutRequest(handlaggningId, putHandlaggningRequest);
      HandlaggningPutResponse handlaggningPutResponse = handlaggningService
            .putHandlaggning(handlaggningPutRequest);
      return mapper.toPutHandlaggningResponse(handlaggningPutResponse);
   }
}
