package net.framinfo.freetube.dto.video;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.framinfo.freetube.dto.channel.ChannelDTO;
import net.framinfo.freetube.models.video.Video;

import java.time.Instant;

@Getter @Setter
@RequiredArgsConstructor
public class SelfVideoDTO extends AbstractVideoDTO {

    private boolean isPublic;

    private Instant updatedAt;

    public SelfVideoDTO(Video video) {
        super(video);
        this.channel = new ChannelDTO(video.getChannel(), true);
        this.isPublic = video.isPublic();
        this.updatedAt = video.getUpdatedAt();
    }
}
