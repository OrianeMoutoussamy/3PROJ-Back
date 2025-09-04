package net.framinfo.freetube.services.channel;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.models.channel.Channel;

@ApplicationScoped
public class GetChannelService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<Channel> runSelf(String token) {
        return sessionDelegate.getUserFromToken(token)
                .flatMap(it -> Channel.find("user_id = ?1", it.getId()).firstResult());
    }
}
