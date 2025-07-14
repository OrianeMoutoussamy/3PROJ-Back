package net.framinfo.freetube;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
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
    public Uni<Response> getSelf() {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getById(@RestPath String id) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @GET
    @Path("/subscribed")
    public Uni<Response> getSubscribedChannels() {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @PUT
    public Uni<Response> updateChannel(String body) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @DELETE
    public Uni<Response> deleteChannel() {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @POST
    @Path("/s/{id}")
    public Uni<Response> subscribe(@RestPath String id) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @DELETE
    @Path("/u/{id}")
    public Uni<Response> unsubscribe(@RestPath String id) {
        return Uni.createFrom().item(Response.status(200).build());
    }
}
