package se.fk.github.rimfrost.yrkande.presentation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import se.fk.github.rimfrost.yrkande.logic.dto.YrkandeCreateRequest;
import se.fk.github.rimfrost.yrkande.logic.dto.YrkandeCreateResponse;
import se.fk.github.rimfrost.yrkande.logic.service.YrkandeService;
import se.fk.github.rimfrost.yrkande.presentation.util.PresentationMapper;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.YrkandeControllerApi;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostYrkandeRequest;
import se.fk.rimfrost.jaxrsspec.controllers.generatedsource.model.PostYrkandeResponse;

@ApplicationScoped
@Path("/yrkande")
public class YrkandeController implements YrkandeControllerApi
{

   @Inject
   YrkandeService yrkandeService;

   @Inject
   PresentationMapper mapper;

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
   public PostYrkandeResponse postYrkande(@Valid @NotNull PostYrkandeRequest postYrkandeRequest)
   {
      YrkandeCreateRequest yrkandeCreateRequest = mapper.toYrkandeCreateRequest(postYrkandeRequest);
      YrkandeCreateResponse yrkandeCreateResponse = yrkandeService.createYrkande(yrkandeCreateRequest);
      PostYrkandeResponse response = mapper.toPostYrkandeResponse(yrkandeCreateResponse);
      return response;
   }

}
