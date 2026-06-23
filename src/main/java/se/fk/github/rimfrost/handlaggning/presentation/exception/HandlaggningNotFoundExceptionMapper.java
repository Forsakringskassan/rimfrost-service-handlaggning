package se.fk.github.rimfrost.handlaggning.presentation.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import se.fk.github.rimfrost.handlaggning.logic.exception.HandlaggningNotFoundException;

@Provider
public class HandlaggningNotFoundExceptionMapper implements ExceptionMapper<HandlaggningNotFoundException>
{
   @Override
   public Response toResponse(HandlaggningNotFoundException exception)
   {
      return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
   }
}
