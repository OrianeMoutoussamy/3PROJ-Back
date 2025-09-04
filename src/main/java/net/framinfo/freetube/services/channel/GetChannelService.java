package net.framinfo.freetube.services.channel;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.dto.ChannelDTO;
import net.framinfo.freetube.dto.SelfChannelDTO;
import net.framinfo.freetube.models.channel.Channel;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@ApplicationScoped
public class GetChannelService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<SelfChannelDTO> runSelf(String token) {
        return sessionDelegate.getChannelFromToken(token)
                .map(SelfChannelDTO::new);
    }

    public Uni<List<ChannelDTO>> getSubscriptions(String token) {
        return sessionDelegate.getChannelFromToken(token)
                .map(Channel::getSubscriptions)
                .map(it -> it.stream().map(ti -> new ChannelDTO(ti, true)).toList());
    }

    public Uni<ChannelDTO> run(String token, String id) {
        AtomicBoolean subscribed = new AtomicBoolean(false);
        return sessionDelegate.getChannelFromToken(token)
                .flatMap(it -> {
                    subscribed.set(it.getSubscriptions().stream().anyMatch(sub -> sub.getId().equals(Long.parseLong(id))));
                    return Channel.findById(id);
                })
                .map(it -> new ChannelDTO((Channel) it, subscribed.get()));
    }
}
