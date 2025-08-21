package net.framinfo.freetube;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import net.framinfo.freetube.models.video.Video;
import org.jboss.resteasy.reactive.RestPath;

/**
 * Handles video-related queries
 */
@Slf4j
@Path("/v1/v")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class VideoResource {

    @GET
    @Path("/{id}")
    public Uni<Video> getVideoById(@RestPath String id) {
        return Uni.createFrom().item(new Video());
    }

    @POST
    public Uni<Response> uploadVideo() {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Video> updateVideo(@RestPath String id) {
        return Uni.createFrom().item(new Video());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteVideo(@RestPath String id) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @PUT
    @Path("/{id}/react")
    public Uni<Response> reactVideo(@RestPath String id, String body) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @PUT
    @Path("/{id}/comment")
    public Uni<Response> commentVideo(@RestPath String id, String body) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @DELETE
    @Path("/{id}/comment/{cid}")
    public Uni<Response> deleteComment(@RestPath String id, @RestPath String cid) {
        return Uni.createFrom().item(Response.status(200).build());
    }
}
