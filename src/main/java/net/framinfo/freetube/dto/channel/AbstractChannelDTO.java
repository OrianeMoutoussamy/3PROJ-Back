package net.framinfo.freetube.dto.channel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.framinfo.freetube.dto.video.VideoDTO;
import net.framinfo.freetube.models.channel.Channel;

import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
public abstract class AbstractChannelDTO {

    protected Long id;

    protected byte[] profilePicture;

    protected String username;

    protected String description;

    protected List<VideoDTO> videos;

    protected Long subscribersCount;

    public AbstractChannelDTO(Channel channel) {
        this.id = channel.getId();
        this.profilePicture = channel.getProfilePicture();
        this.username = channel.getUsername();
        this.description = channel.getDescription();
        this.videos = channel.getVideos().stream().map(it -> new VideoDTO(it, false)).toList();
        this.subscribersCount = (long) channel.getSubscribers().size();
    }
}
