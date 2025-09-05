package net.framinfo.freetube.services.channel;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.models.channel.Channel;

import javax.ws.rs.ForbiddenException;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractChannelService {

    @Inject
    protected SessionDelegate sessionDelegate;

    protected Uni<Channel> checkOwnership(String token, String id) {
        AtomicLong channelId = new AtomicLong();
        return sessionDelegate.getChannelFromToken(token)
                .flatMap(it -> {
                    channelId.set(it.getId());
                    return Channel.findById(id);
                })
                .onItem().ifNull().failWith(NotFoundException::new)
                .map(it -> ((Channel) it).getId().equals(channelId.get()) ? it : null)
                .onItem().ifNull().failWith(ForbiddenException::new)
                .onItem().ifNotNull().transform(it -> (Channel) it);
    }
}
