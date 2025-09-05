package net.framinfo.freetube.services.playlist;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import net.framinfo.freetube.models.playlist.Playlist;

@ApplicationScoped
public class DeletePlaylistService extends AbstractPlaylistService{

    public Uni<Response> run(String token, String id) {
        return this.checkOwnership(token, Long.parseLong(id))
                .onItem().ifNotNull().transformToUni(it -> Uni.join().all(it.delete(), it.flush()).andCollectFailures())
                .map(it -> Response.status(200).build());
    }
}
