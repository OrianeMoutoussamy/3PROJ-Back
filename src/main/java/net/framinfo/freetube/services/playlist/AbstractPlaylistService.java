package net.framinfo.freetube.services.playlist;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.models.playlist.Playlist;

import javax.ws.rs.ForbiddenException;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractPlaylistService {

    @Inject
    protected SessionDelegate sessionDelegate;

    protected Uni<Playlist> checkOwnership(String token, Long id) {
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
