package net.framinfo.freetube.services.playlist;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import net.framinfo.freetube.dto.PlaylistDTO;
import net.framinfo.freetube.models.playlist.Playlist;
import net.framinfo.freetube.models.playlist.PlaylistVideo;
import net.framinfo.freetube.models.playlist.PlaylistVideoId;

@ApplicationScoped
public class UpdatePlaylistService extends AbstractPlaylistService{

    public Uni<PlaylistDTO> run(String token, String id, Playlist playlist) {
        return this.checkOwnership(token, id)
                .onItem().ifNotNull().transformToUni(ignored -> { //TODO adapt to payload -> pbly not playlist received in payload but DTO..
                    playlist.setId(Long.parseLong(id));
                    return playlist.persist();
                })
                .onItem().ifNotNull().transform(it -> new PlaylistDTO((Playlist) it));
    }

    public Uni<Response> addVideo(String token, String id, String videoId) {
        return this.checkOwnership(token, id)
                .onItem().ifNotNull().transformToUni(it -> {
                    PlaylistVideoId playlistVideoId = new PlaylistVideoId(it.getId(), Long.parseLong(videoId));
                    PlaylistVideo playlistVideo = new PlaylistVideo();
                    playlistVideo.setId(playlistVideoId);
                    return playlistVideo.persistAndFlush();
                })
                .onItem().ifNotNull().transform(ignored -> Response.status(200).build());
    }

    public Uni<Response> removeVideo(String token, String id, String videoId) {
        return this.checkOwnership(token, id)
                .onItem().ifNotNull().transformToUni(it -> PlaylistVideo.delete("id = ?1", new PlaylistVideoId(it.getId(), Long.parseLong(videoId))))
                .onItem().ifNotNull().transform(ignored -> Response.status(200).build());
    }
}
