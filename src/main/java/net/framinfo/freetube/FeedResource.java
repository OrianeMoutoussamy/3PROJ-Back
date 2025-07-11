package net.framinfo.freetube;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles the homepage and feed generation
 */
@Slf4j
@Path("/v1/feed")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class FeedResource {

    @GET
    @Path("/home")
    public Uni<Response> getHomepageFeed() {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @GET
    @Path("/trending")
    public Uni<Response> getTrendingFeed() {
        return Uni.createFrom().item(Response.status(200).build());
    }
}
