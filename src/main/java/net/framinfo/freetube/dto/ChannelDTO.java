package net.framinfo.freetube.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.framinfo.freetube.models.channel.Channel;

@Getter @Setter
@RequiredArgsConstructor
public class ChannelDTO {

    private byte[] profilePicture;

    private String username;

    private String description;

    private boolean isSubscribed;

    public ChannelDTO(Channel channel, boolean isSubscribed) {
        this.username = channel.getUsername();
        this.description = channel.getDescription();
        this.profilePicture = channel.getProfilePicture();
        this.isSubscribed = isSubscribed;
    }
}
