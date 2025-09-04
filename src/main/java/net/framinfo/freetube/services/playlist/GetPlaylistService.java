package net.framinfo.freetube.services.playlist;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.models.playlist.Playlist;

import java.util.List;

@ApplicationScoped
public class GetPlaylistService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<List<Playlist>> runSelf(String token) {
        return sessionDelegate.getChannelFromToken(token)
                .flatMap(it -> Playlist.find("channel_id = ?1", it.getId()).list());
    }

    public Uni<Playlist> run(String id) {
        return Playlist.findById(id);
    }
}
