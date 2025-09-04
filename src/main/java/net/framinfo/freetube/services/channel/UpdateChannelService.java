package net.framinfo.freetube.services.channel;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.dto.ChannelDTO;
import net.framinfo.freetube.models.channel.Channel;

import javax.ws.rs.ForbiddenException;

@ApplicationScoped
public class UpdateChannelService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<ChannelDTO> run(String token, Channel channel) {
        return sessionDelegate.getUserFromToken(token)
                .map(it -> it.getId().equals(channel.getUserId()) ? channel : null)
                .onItem().ifNull().failWith(ForbiddenException::new)
                .onItem().ifNotNull().transformToUni(it -> it.persist())
                .onItem().ifNotNull().transform(it -> new ChannelDTO((Channel) it, true));
    }
}
