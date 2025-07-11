package net.framinfo.freetube;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestPath;

/**
 * Handles playlist-related queries
 */
@Slf4j
@Path("/v1/playlist")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PlaylistResource {

    @GET
    public Uni<Response> getSelfPlaylists() {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getPlaylistById(@RestPath String id) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @POST
    public Uni<Response> createPlaylist(String body) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> updatePlaylist(@RestPath String id, String body) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @PUT
    @Path("/{id}/add/{vid}")
    public Uni<Response> addVideoToPlaylist(@RestPath String id, @RestPath String vid) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @PUT
    @Path("/{id}/del/{vid}")
    public Uni<Response> removeVideoFromPlaylist(@RestPath String id, @RestPath String vid) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deletePlaylist(@RestPath String id) {
        return Uni.createFrom().item(Response.status(200).build());
    }
}
