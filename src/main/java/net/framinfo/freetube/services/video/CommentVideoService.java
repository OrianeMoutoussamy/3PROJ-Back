package net.framinfo.freetube.services.video;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import net.framinfo.freetube.models.video.Comment;

import javax.ws.rs.ForbiddenException;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class CommentVideoService extends AbstractVideoService {

    public Uni<Response> addComment(String token, String videoId, String comment) {
        return sessionDelegate.getChannelFromToken(token)
                .flatMap(it -> new Comment(it.getId(), Long.parseLong(videoId), comment).persistAndFlush())
                .map(it -> Response.status(200).build());
    }

    public Uni<Response> deleteComment(String token, String videoId, String commentId) {
        AtomicLong channelId = new AtomicLong();
        return sessionDelegate.getChannelFromToken(token)
                .flatMap(it -> {
                    channelId.set(it.getId());
                    return Comment.findById(commentId);
                })
                .onItem().ifNull().failWith(NotFoundException::new)
                .map(it -> ((Comment) it).getChannelId().equals(channelId.get()) ? it : null)
                .onItem().ifNull().failWith(ForbiddenException::new)
                .onItem().ifNotNull().transform(it -> (Comment) it)
                .onItem().ifNotNull().transformToUni(it -> it.delete())
                .map(it -> Response.status(200).build());
    }
}
