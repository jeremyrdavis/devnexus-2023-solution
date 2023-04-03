package io.arrogantprogrammer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/jaxrsgreeting")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JAXRSGreetingApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(JAXRSGreetingApi.class);

    @GET
    public Response all() {
        return Response.ok().entity(Greeting.listAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {

        LOGGER.info("finding: {}", id);
        return Response.ok().entity(Greeting.findById(id)).build();
    }

    @POST
    @Transactional
    public Response addGreeting(Greeting greetingToAdd) {
        greetingToAdd.persist();
        LOGGER.info("persisted: {}", greetingToAdd);
        return Response.created(URI.create("/" + greetingToAdd.id)).entity(greetingToAdd).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Greeting updatedGreeting) {

        Greeting greeting = Greeting.findById(updatedGreeting.id);
        greeting.setText(updatedGreeting.text);
        greeting.persist();
        LOGGER.info("updated: {}", greeting);
        return Response.ok().entity(greeting).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {

        Greeting greeting = Greeting.findById(id);
        greeting.delete();
        LOGGER.info("deleted: {}", id);
        return Response.ok().build();
    }
}
