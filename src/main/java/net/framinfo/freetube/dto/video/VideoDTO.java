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
        this.id = video.getId();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.thumbnail = video.getThumbnail();
        this.createdAt = video.getCreatedAt();
        this.comments = video.getComments();
        this.duration = video.getDuration();
        this.channel = new ChannelDTO(video.getChannel(), subscribed);
        this.likes = video.getReactions().stream().filter(it -> it.getType().equals(1L)).count();
        this.dislikes = video.getReactions().stream().filter(it -> it.getType().equals(-1L)).count();
    }
}
