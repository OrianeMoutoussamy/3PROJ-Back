package net.framinfo.freetube.dto.channel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.framinfo.freetube.dto.video.VideoDTO;
import net.framinfo.freetube.models.channel.Channel;

import java.time.Instant;
import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
public class SelfChannelDTO extends AbstractChannelDTO {

    private Instant createdAt;

    private Instant updatedAt;

    private List<ChannelDTO> subscriptions;

    private List<ChannelDTO> subscribers;

    private List<VideoDTO> history;

    public SelfChannelDTO(Channel channel) {
        super(channel);
        this.createdAt = channel.getCreatedAt();
        this.updatedAt = channel.getUpdatedAt();
        this.subscriptions = channel.getSubscriptions().stream().map(it -> new ChannelDTO(it, true)).toList();
        this.subscribers = channel.getSubscribers().stream().map(it -> new ChannelDTO(it, this.subscriptions.stream().anyMatch(ti -> ti.getId().equals(it.getId())))).toList();
        this.history = channel.getHistory().stream().map(it -> new VideoDTO(it, this.subscriptions.stream().anyMatch(ti -> ti.getId().equals(it.getChannelId())))).toList();
    }
}
