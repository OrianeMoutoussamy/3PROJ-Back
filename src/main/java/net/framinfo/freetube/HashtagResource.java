package net.framinfo.freetube;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import net.framinfo.freetube.dto.HashtagDTO;
import net.framinfo.freetube.services.video.hashtag.CreateHashtagService;
import net.framinfo.freetube.services.video.hashtag.DeleteHashtagService;
import net.framinfo.freetube.services.video.hashtag.GetHashtagService;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

/**
 * Handles hashtags-related queries
 */
@Slf4j
@Path("/v1/hashtag")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class HashtagResource {

    @Inject
    GetHashtagService getHashtagService;

    @Inject
    CreateHashtagService createHashtagService;

    @Inject
    DeleteHashtagService deleteHashtagService;

    @GET
    public Uni<List<HashtagDTO>> getHashtags(@HeaderParam("Token") String token) {
        return getHashtagService.run(token);
    }

    @POST
    public Uni<HashtagDTO> createHashtag(@HeaderParam("Token") String token, String hashtag) {
        return createHashtagService.run(token, hashtag);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteHashtag(@HeaderParam("Token") String token, @RestPath String id) {
        return deleteHashtagService.run(token, id);
    }
}
