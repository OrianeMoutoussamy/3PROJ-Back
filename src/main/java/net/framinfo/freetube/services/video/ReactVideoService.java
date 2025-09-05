package net.framinfo.freetube.services.video;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import net.framinfo.freetube.models.video.Reaction;

import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ReactVideoService extends AbstractVideoService {

    public Uni<Response> run(String token, String id, String reaction) {
        Long reactLong = Long.parseLong(reaction);
        AtomicLong channelId = new AtomicLong();
        return sessionDelegate.getChannelFromToken(token)
                .flatMap(it -> {
                    channelId.set(it.getId());
                    return Reaction.find("channelId = ?1 and videoId = ?2 and type = ?3", it.getId(), id, reactLong).firstResult();
                })
                .onItem().ifNull().switchTo(this.addReaction(channelId.get(), Long.parseLong(id), reactLong))
                .onItem().ifNotNull().transformToUni(it -> ((Reaction) it).delete())
                .onItem().ifNotNull().transform(ignored -> Response.status(200).build());

    }

    private Uni<Response> addReaction(Long channelId, Long videoId, Long reaction) {
        return Reaction.delete("channelId = ?1 and videoId = ?2")
                .flatMap(ignored -> new Reaction(channelId, videoId, reaction).persistAndFlush())
                .onItem().ifNotNull().transform(ignored -> Response.status(200).build());
    }
}
