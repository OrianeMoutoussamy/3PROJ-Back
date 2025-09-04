package net.framinfo.freetube.models.channel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@RequiredArgsConstructor
@Embeddable
public class SubscriptionId implements Serializable {

    @Column(name = "subscriber_id")
    private Long subscriberId;

    @Column(name = "channel_id")
    private Long channelId;

    public SubscriptionId(Long subscriberId, Long channelId) {
        this.subscriberId = subscriberId;
        this.channelId = channelId;
    }
}
