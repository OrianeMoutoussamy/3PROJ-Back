package net.framinfo.freetube.services.playlist;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.dto.PlaylistDTO;
import net.framinfo.freetube.models.playlist.Playlist;
import net.framinfo.freetube.models.playlist.PlaylistVideo;
import net.framinfo.freetube.models.playlist.PlaylistVideoId;

import javax.ws.rs.ForbiddenException;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class UpdatePlaylistService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<PlaylistDTO> run(String token, String id, Playlist playlist) {
        return sessionDelegate.getChannelFromToken(token)
                .map(it -> it.getId().equals(playlist.getChannelId()) ? playlist : null)
                .onItem().ifNull().failWith(ForbiddenException::new)
                .onItem().ifNotNull().transformToUni(it -> {
                    it.setId(Long.parseLong(id));
                    return it.persist();
                })
                .onItem().ifNotNull().transform(it -> new PlaylistDTO((Playlist) it));
    }

    public Uni<Response> addVideo(String token, String id, String videoId) {
        return this.checkOwnership(token, id, videoId)
                .onItem().ifNotNull().transformToUni(it -> {
                    PlaylistVideoId playlistVideoId = new PlaylistVideoId(it.getId(), Long.parseLong(videoId));
                    PlaylistVideo playlistVideo = new PlaylistVideo();
                    playlistVideo.setId(playlistVideoId);
                    return playlistVideo.persistAndFlush();
                })
                .onItem().ifNotNull().transform(ignored -> Response.status(200).build());
    }

    public Uni<Response> removeVideo(String token, String id, String videoId) {
        return this.checkOwnership(token, id, videoId)
                .onItem().ifNotNull().transformToUni(it -> PlaylistVideo.delete("id = ?1", new PlaylistVideoId(it.getId(), Long.parseLong(videoId))))
                .onItem().ifNotNull().transform(ignored -> Response.status(200).build());
    }

    private Uni<Playlist> checkOwnership(String token, String id, String videoId) {
        AtomicLong channelId = new AtomicLong();
        return sessionDelegate.getChannelFromToken(token)
                .flatMap(it -> {
                    channelId.set(it.getId());
                    return Playlist.findById(id);
                })
                .onItem().ifNull().failWith(NotFoundException::new)
                .map(it -> ((Playlist) it).getChannelId().equals(channelId.get()) ? it : null)
                .onItem().ifNull().failWith(ForbiddenException::new)
                .onItem().ifNotNull().transform(it -> (Playlist) it);
    }
}
