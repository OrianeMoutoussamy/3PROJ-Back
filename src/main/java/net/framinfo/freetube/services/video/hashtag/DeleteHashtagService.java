package net.framinfo.freetube.services.video.hashtag;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.models.video.hashtag.Hashtag;

@ApplicationScoped
public class DeleteHashtagService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<Response> run(String token, String id) {
        return sessionDelegate.getUserFromToken(token)
                .flatMap(ignored -> Hashtag.deleteById(id))
                .map(it -> it ? Response.status(200).build() : null)
                .onItem().ifNull().failWith(NotFoundException::new);
    }
}
