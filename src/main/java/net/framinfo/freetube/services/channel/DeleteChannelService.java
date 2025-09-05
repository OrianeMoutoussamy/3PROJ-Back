package net.framinfo.freetube.services.channel;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import net.framinfo.freetube.models.channel.Channel;

import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class DeleteChannelService extends AbstractChannelService {

    public Uni<Response> run(String token) {
        return sessionDelegate.getUserFromToken(token)
                .flatMap(it -> Channel.find("userId = ?1", it.getId()).firstResult())
                .onItem().ifNull().failWith(NotFoundException::new)
                .onItem().ifNotNull().transform(it -> (Channel) it)
                .onItem().ifNotNull().transformToUni(it -> Uni.join().all(it.delete(), it.flush()).andCollectFailures())
                .map(ignored -> Response.status(200).build());
    }
}
