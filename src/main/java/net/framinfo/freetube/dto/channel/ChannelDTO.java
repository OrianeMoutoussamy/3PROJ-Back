package net.framinfo.freetube.dto.channel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.framinfo.freetube.models.channel.Channel;

@Getter @Setter
@RequiredArgsConstructor
public class ChannelDTO extends AbstractChannelDTO {

    private boolean isSubscribed;

    public ChannelDTO(Channel channel, boolean isSubscribed) {
        super(channel);
        this.isSubscribed = isSubscribed;
    }
}
