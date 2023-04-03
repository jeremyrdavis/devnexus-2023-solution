package io.arrogantprogrammer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/greeting")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingApi.class);

    @GET
    public List<Greeting> all() {
        return Greeting.listAll();
    }

    @GET
    @Path("/{id}")
    public Greeting getById(@PathParam("id") Long id) {

        LOGGER.info("finding: {}", id);
        return Greeting.findById(id);
    }

    @POST
    @Transactional
    public Greeting addGreeting(Greeting greetingToAdd) {
        greetingToAdd.persist();
        LOGGER.info("persisted: {}", greetingToAdd);
        return greetingToAdd;
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Greeting update(@PathParam("id") Long id, Greeting updatedGreeting) {

        Greeting greeting = Greeting.findById(updatedGreeting.id);
        greeting.setText(updatedGreeting.text);
        greeting.persist();
        LOGGER.info("updated: {}", greeting);
        return greeting;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {

        Greeting greeting = Greeting.findById(id);
        greeting.delete();
        LOGGER.info("deleted: {}", id);
        return;
    }

}
