package net.framinfo.freetube.services.channel;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import net.framinfo.freetube.dto.channel.ChannelDTO;
import net.framinfo.freetube.dto.channel.SelfChannelDTO;
import net.framinfo.freetube.models.channel.Channel;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@ApplicationScoped
public class GetChannelService extends AbstractChannelService{

    public Uni<SelfChannelDTO> runSelf(String token) { //TODO add history to this and DTO
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
