package net.framinfo.freetube.services.video;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotFoundException;
import net.framinfo.freetube.dto.video.AbstractVideoDTO;
import net.framinfo.freetube.dto.video.SelfVideoDTO;
import net.framinfo.freetube.dto.video.VideoDTO;
import net.framinfo.freetube.models.video.Video;

import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class GetVideoService extends AbstractVideoService {

    public Uni<? extends AbstractVideoDTO> run(String token, String videoId) {
        AtomicLong channelId = new AtomicLong();
        return sessionDelegate.getChannelFromToken(token) // Not using abstract method check ownership because channelId is required for further checks.
                .flatMap(it -> {
                    channelId.set(it.getId());
                    return Video.findById(videoId);
                })
                .onItem().ifNull().failWith(NotFoundException::new)
                .onItem().ifNotNull().transform(it -> (Video) it)
                .onItem().ifNotNull().transform(it -> it.getChannelId().equals(channelId.get()) ? it : (it.isPublic() ? it : null))
                .onItem().ifNull().failWith(ForbiddenException::new)
                .onItem().ifNotNull().transform(it -> it.getChannelId().equals(channelId.get())
                        ?
                        new SelfVideoDTO(it, true)
                        :
                        new VideoDTO(it, it.getChannel().getSubscribers().stream().anyMatch(ti -> ti.getId().equals(channelId.get())))
                );
    }
}
