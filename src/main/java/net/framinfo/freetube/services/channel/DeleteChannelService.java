package net.framinfo.freetube.services.channel;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.models.channel.Channel;

import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class DeleteChannelService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<Response> run(String token) {
        return sessionDelegate.getUserFromToken(token)
                .flatMap(it -> Channel.find("user_id = ?1", it.getId()).firstResult())
                .onItem().ifNull().failWith(NotFoundException::new)
                .onItem().ifNotNull().transformToUni(it -> ((Channel) it).delete())
                .onItem().ifNotNull().transform(ignored -> Response.status(200).build());
    }
}
