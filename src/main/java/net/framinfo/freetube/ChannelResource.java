package net.framinfo.freetube;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestPath;

/**
 * Handles channel-related queries
 */
@Slf4j
@Path("/v1/channel")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ChannelResource {

    @GET
    @Path("/{id}")
    public Uni<Response> getById(@RestPath String id) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @GET
    @Path("/subcribed")
    public Uni<Response> getSubscribedChannels() {
        return Uni.createFrom().item(Response.status(200).build());
    }

    // TODO: Add suscribe and unsubscribe methods
}
