package net.framinfo.freetube.services.playlist;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.dto.PlaylistDTO;
import net.framinfo.freetube.models.playlist.Playlist;

import java.util.List;

@ApplicationScoped
public class GetPlaylistService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<List<PlaylistDTO>> runSelf(String token) {
        return sessionDelegate.getChannelFromToken(token)
                .flatMap(it -> Playlist.find("channelId = ?1", it.getId()).list())
                .map(it -> it.stream().map(playlist -> new PlaylistDTO((Playlist) playlist)).toList());
    }

    public Uni<PlaylistDTO> run(String id) {
        return Playlist.findById(id)
                .onItem().ifNull().failWith(NotFoundException::new)
                .onItem().ifNotNull().transform(it -> new PlaylistDTO((Playlist) it));
    }
}
