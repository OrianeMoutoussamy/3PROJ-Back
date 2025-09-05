package net.framinfo.freetube.services.playlist;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class DeletePlaylistService extends AbstractPlaylistService{

    public Uni<Response> run(String token, String id) {
        return this.checkOwnership(token, id)
                .onItem().ifNotNull().transformToUni(it -> it.delete())
                .onItem().ifNotNull().transform(it -> Response.status(200).build());
    }
}
