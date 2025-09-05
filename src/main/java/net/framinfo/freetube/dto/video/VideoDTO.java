package net.framinfo.freetube.dto.video;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.framinfo.freetube.dto.channel.ChannelDTO;
import net.framinfo.freetube.models.video.Video;

@Getter @Setter
@RequiredArgsConstructor
public class VideoDTO extends AbstractVideoDTO {

    public VideoDTO(Video video, Boolean subscribed) {
        super(video);
        this.channel = new ChannelDTO(video.getChannel(), subscribed);
    }
}
