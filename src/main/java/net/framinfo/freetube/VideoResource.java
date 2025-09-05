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
    @Path("/w/{id}")
    public Uni<Video> watchVideo(@HeaderParam("Token") String token, @RestPath String id) {
        return Uni.createFrom().item(new Video());
    }

    @GET
    @Path("/{id}")
    public Uni<Video> getVideoById(@HeaderParam("Token") String token, @RestPath String id) {
        return Uni.createFrom().item(new Video());
    }

    @POST
    public Uni<Response> uploadVideo(@HeaderParam("Token") String token) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Video> updateVideo(@HeaderParam("Token") String token, @RestPath String id) {
        return Uni.createFrom().item(new Video());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteVideo(@HeaderParam("Token") String token, @RestPath String id) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @PUT
    @Path("/{id}/react")
    public Uni<Response> reactVideo(@HeaderParam("Token") String token, @RestPath String id, String reaction) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @PUT
    @Path("/{id}/comment")
    public Uni<Response> commentVideo(@HeaderParam("Token") String token, @RestPath String id, String body) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @DELETE
    @Path("/{id}/comment/{cid}")
    public Uni<Response> deleteComment(@HeaderParam("Token") String token, @RestPath String id, @RestPath String cid) {
        return Uni.createFrom().item(Response.status(200).build());
    }
}
