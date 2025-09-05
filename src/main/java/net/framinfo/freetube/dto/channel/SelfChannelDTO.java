package net.framinfo.freetube.dto.channel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.framinfo.freetube.models.channel.Channel;

import java.time.Instant;
import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
public class SelfChannelDTO {

    private Long id;

    private byte[] profilePicture;

    private String username;

    private String description;

    private Instant createdAt;

    private Instant updatedAt;

    private List<ChannelDTO> subscriptions;

    private List<ChannelDTO> subscribers;

    public SelfChannelDTO(Channel channel) {
        this.id = channel.getId();
        this.username = channel.getUsername();
        this.description = channel.getDescription();
        this.createdAt = channel.getCreatedAt();
        this.updatedAt = channel.getUpdatedAt();
        this.profilePicture = channel.getProfilePicture();
        this.subscriptions = channel.getSubscriptions().stream().map(it -> new ChannelDTO(it, true)).toList();
        this.subscribers = channel.getSubscribers().stream().map(it -> new ChannelDTO(it, this.subscriptions.stream().anyMatch(ti -> ti.getUsername().equals(it.getUsername())))).toList();
    }
}
