package net.framinfo.freetube.services.video;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import net.framinfo.freetube.dto.video.SelfVideoDTO;
import net.framinfo.freetube.models.video.Video;

@ApplicationScoped
public class UpdateVideoService extends AbstractVideoService {

    public Uni<SelfVideoDTO> run(String token, String id, Video video) {
        return this.checkOwnership(token, Long.parseLong(id))
                .onItem().ifNotNull().transformToUni(ignored -> { //TODO adapt to payload -> pbly not video received in payload but DTO..
                    video.setId(Long.parseLong(id));
                    return video.persist();
                })
                .onItem().ifNotNull().transform(it -> new SelfVideoDTO((Video) it));
    }
}
