package net.framinfo.freetube.services.channel;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import net.framinfo.freetube.dto.channel.ChannelDTO;
import net.framinfo.freetube.models.channel.Channel;

import javax.ws.rs.ForbiddenException;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class UpdateChannelService extends AbstractChannelService{

    public Uni<ChannelDTO> run(String token, Channel channel) { //TODO adapt to payload -> pbly not channel received in payload but DTO..
        AtomicLong userId = new AtomicLong();
        return sessionDelegate.getUserFromToken(token)
                .flatMap(it -> {
                    userId.set(it.getId());
                    return this.checkOwnership(token, channel.getId().toString());
                })
                .map(it -> it.getId().equals(channel.getUserId()) ? channel : null)
                .onItem().ifNull().failWith(ForbiddenException::new)
                .onItem().ifNotNull().transformToUni(it -> it.persist())
                .onItem().ifNotNull().transform(it -> new ChannelDTO((Channel) it, true));
    }
}
