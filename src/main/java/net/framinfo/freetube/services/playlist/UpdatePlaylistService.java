package net.framinfo.freetube.services.playlist;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.models.playlist.Playlist;

import javax.ws.rs.ForbiddenException;

@ApplicationScoped
public class UpdatePlaylistService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<Playlist> run(String token, String id, Playlist playlist) {
        return sessionDelegate.getChannelFromToken(token)
                .map(it -> it.getId().equals(playlist.getChannelId()) ? playlist : null)
                .onItem().ifNull().failWith(ForbiddenException::new)
                .onItem().ifNotNull().transformToUni(it -> {
                    it.setId(Long.parseLong(id));
                    return it.persist();
                });
    }
}
