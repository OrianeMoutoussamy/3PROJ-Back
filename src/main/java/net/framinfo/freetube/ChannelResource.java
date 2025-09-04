package net.framinfo.freetube;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import net.framinfo.freetube.models.channel.Channel;
import net.framinfo.freetube.services.channel.GetChannelService;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

/**
 * Handles channel-related queries
 */
@Slf4j
@Path("/v1/channel")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ChannelResource {

    @Inject
    GetChannelService getChannelService;

    @GET
    public Uni<Channel> getSelf(@HeaderParam("Token") String token) {
        return getChannelService.runSelf(token);
    }

    @GET
    @Path("/{id}")
    public Uni<Channel> getById(@RestPath String id) {
        return Uni.createFrom().item(new Channel());
    }

    @GET
    @Path("/subscribed")
    public Uni<List<Channel>> getSubscribedChannels() {
        return Uni.createFrom().item(List.of());
    }

    @PUT
    public Uni<Channel> updateChannel(String body) {
        return Uni.createFrom().item(new Channel());
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
