package net.framinfo.freetube.services.video.hashtag;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.dto.HashtagDTO;
import net.framinfo.freetube.models.video.hashtag.Hashtag;

@ApplicationScoped
public class CreateHashtagService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<HashtagDTO> run(String token, String hashtag) {
        return sessionDelegate.getUserFromToken(token)
                .flatMap(ignored -> new Hashtag(hashtag).persist())
                .onItem().ifNotNull().transform(it -> new HashtagDTO((Hashtag) it));
    }
}
