package net.framinfo.freetube;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import net.framinfo.freetube.dto.ChannelDTO;
import net.framinfo.freetube.dto.SelfChannelDTO;
import net.framinfo.freetube.models.channel.Channel;
import net.framinfo.freetube.services.channel.DeleteChannelService;
import net.framinfo.freetube.services.channel.GetChannelService;
import net.framinfo.freetube.services.channel.SubscriptionService;
import net.framinfo.freetube.services.channel.UpdateChannelService;
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

    @Inject
    UpdateChannelService updateChannelService;

    @Inject
    DeleteChannelService deleteChannelService;

    @Inject
    SubscriptionService subscriptionService;

    @GET
    public Uni<SelfChannelDTO> getSelf(@HeaderParam("Token") String token) {
        return getChannelService.runSelf(token);
    }

    @GET
    @Path("/{id}")
    public Uni<ChannelDTO> getById(@HeaderParam("Token") String token, @RestPath String id) {
        return getChannelService.run(token, id);
    }

    @GET
    @Path("/subscribed")
    public Uni<List<ChannelDTO>> getSubscribedChannels(@HeaderParam("Token") String token) {
        return getChannelService.getSubscriptions(token);
    }

    @PUT
    public Uni<ChannelDTO> updateChannel(@HeaderParam("Token") String token, Channel channel) {
        return updateChannelService.run(token, channel);
    }

    @DELETE
    public Uni<Response> deleteChannel(@HeaderParam("Token") String token) {
        return deleteChannelService.run(token);
    }

    @POST
    @Path("/s/{id}")
    public Uni<Response> subscribe(@HeaderParam("Token") String token, @RestPath String id) {
        return subscriptionService.subscribe(token, id);
    }

    @DELETE
    @Path("/u/{id}")
    public Uni<Response> unsubscribe(@HeaderParam("Token") String token, @RestPath String id) {
        return subscriptionService.unsubscribe(token, id);
    }
}
