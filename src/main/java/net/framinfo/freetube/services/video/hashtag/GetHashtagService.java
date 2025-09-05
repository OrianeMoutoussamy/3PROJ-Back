package net.framinfo.freetube.services.video.hashtag;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.dto.HashtagDTO;
import net.framinfo.freetube.models.video.hashtag.Hashtag;

import java.util.List;

@ApplicationScoped
public class GetHashtagService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<List<HashtagDTO>> run(String token) {
        return sessionDelegate.getUserFromToken(token)
                .flatMap(ignored -> Hashtag.listAll())
                .map(it -> it.stream().map(ti -> new HashtagDTO((Hashtag) ti)).toList());
    }
}
