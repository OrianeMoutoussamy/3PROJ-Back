package net.framinfo.freetube.services.video;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.models.video.Video;

import javax.ws.rs.ForbiddenException;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractVideoService {

    @Inject
    protected SessionDelegate sessionDelegate;

    protected Uni<Video> checkOwnership(String token, String id) {
        AtomicLong channelId = new AtomicLong();
        return sessionDelegate.getChannelFromToken(token)
                .flatMap(it -> {
                    channelId.set(it.getId());
                    return Video.findById(id);
                })
                .onItem().ifNull().failWith(NotFoundException::new)
                .map(it -> ((Video) it).getChannelId().equals(channelId.get()) ? it : null)
                .onItem().ifNull().failWith(ForbiddenException::new)
                .onItem().ifNotNull().transform(it -> (Video) it);
    }
}
