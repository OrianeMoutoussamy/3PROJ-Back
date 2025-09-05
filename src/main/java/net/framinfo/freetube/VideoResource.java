package net.framinfo.freetube;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import net.framinfo.freetube.dto.video.AbstractVideoDTO;
import net.framinfo.freetube.dto.video.SelfVideoDTO;
import net.framinfo.freetube.models.video.Video;
import net.framinfo.freetube.services.video.*;
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

    @Inject
    WatchVideoService watchVideoService;

    @Inject
    UploadVideoService uploadVideoService;

    @Inject
    GetVideoService getVideoService;

    @Inject
    UpdateVideoService updateVideoService;

    @Inject
    DeleteVideoService deleteVideoService;

    @Inject
    ReactVideoService reactVideoService;

    @Inject
    CommentVideoService commentVideoService;

    @GET
    @Path("/w/{id}")
    public Uni<Video> watchVideo(@HeaderParam("Token") String token, @RestPath String id) {
        return Uni.createFrom().item(new Video());
    }

    @POST
    public Uni<Response> uploadVideo(@HeaderParam("Token") String token) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @GET
    @Path("/{id}")
    public Uni<? extends AbstractVideoDTO> getVideoById(@HeaderParam("Token") String token, @RestPath String id) {
        return getVideoService.run(token, id);
    }

    @PUT
    @Path("/{id}")
    public Uni<SelfVideoDTO> updateVideo(@HeaderParam("Token") String token, @RestPath String id, Video video) {
        return updateVideoService.run(token, id, video);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteVideo(@HeaderParam("Token") String token, @RestPath String id) {
        return deleteVideoService.run(token, id);
    }

    @PUT
    @Path("/{id}/react")
    public Uni<Response> reactVideo(@HeaderParam("Token") String token, @RestPath String id, String reaction) {
        return reactVideoService.run(token, id, reaction);
    }

    @PUT
    @Path("/{id}/comment")
    public Uni<Response> commentVideo(@HeaderParam("Token") String token, @RestPath String id, String body) {
        return commentVideoService.addComment(token, id, body);
    }

    @DELETE
    @Path("/{id}/comment/{cid}")
    public Uni<Response> deleteComment(@HeaderParam("Token") String token, @RestPath String id, @RestPath String cid) {
        return commentVideoService.deleteComment(token, id, cid);
    }
}
