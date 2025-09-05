package net.framinfo.freetube;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import net.framinfo.freetube.dto.PlaylistDTO;
import net.framinfo.freetube.models.playlist.Playlist;
import net.framinfo.freetube.services.playlist.CreatePlaylistService;
import net.framinfo.freetube.services.playlist.DeletePlaylistService;
import net.framinfo.freetube.services.playlist.GetPlaylistService;
import net.framinfo.freetube.services.playlist.UpdatePlaylistService;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

/**
 * Handles playlist-related queries
 */
@Slf4j
@Path("/v1/playlist")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PlaylistResource {

    @Inject
    GetPlaylistService getPlaylistService;

    @Inject
    CreatePlaylistService createPlaylistService;

    @Inject
    UpdatePlaylistService updatePlaylistService;

    @Inject
    DeletePlaylistService deletePlaylistService;

    @GET
    public Uni<List<PlaylistDTO>> getSelfPlaylists(@HeaderParam("Token") String token) {
        return getPlaylistService.runSelf(token);
    }

    @GET
    @Path("/{id}")
    public Uni<PlaylistDTO> getPlaylistById(@RestPath String id) {
        return getPlaylistService.run(id);
    }

    @POST
    public Uni<PlaylistDTO> createPlaylist(@HeaderParam("Token") String token, Playlist playlist) {
        return createPlaylistService.run(token, playlist);
    }

    @PUT
    @Path("/{id}")
    public Uni<PlaylistDTO> updatePlaylist(@HeaderParam("Token") String token, @RestPath String id, Playlist playlist) {
        return updatePlaylistService.run(token, id, playlist);
    }

    @PUT
    @Path("/{id}/add/{vid}")
    public Uni<Response> addVideoToPlaylist(@HeaderParam("Token") String token, @RestPath String id, @RestPath String vid) {
        return updatePlaylistService.addVideo(token, id, vid);
    }

    @PUT
    @Path("/{id}/del/{vid}")
    public Uni<Response> removeVideoFromPlaylist(@HeaderParam("Token") String token, @RestPath String id, @RestPath String vid) {
        return updatePlaylistService.removeVideo(token, id, vid);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deletePlaylist(@HeaderParam("Token") String token, @RestPath String id) {
        return deletePlaylistService.run(token, id);
    }
}
