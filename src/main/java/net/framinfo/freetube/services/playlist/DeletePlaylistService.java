package net.framinfo.freetube.services.playlist;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.models.playlist.Playlist;

import javax.ws.rs.ForbiddenException;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class DeletePlaylistService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<Response> run(String token, String id) {
        AtomicLong channelId = new AtomicLong();
        return sessionDelegate.getChannelFromToken(token)
                .flatMap(it -> {
                    channelId.set(it.getId());
                    return Playlist.findById(id);
                })
                .map(it -> ((Playlist) it).getChannelId().equals(channelId.get()) ? it : null)
                .onItem().ifNull().failWith(ForbiddenException::new)
                .onItem().ifNotNull().transformToUni(it -> ((Playlist) it).delete())
                .onItem().ifNotNull().transform(it -> Response.status(200).build());
    }
}
