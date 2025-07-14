package net.framinfo.freetube.models.channel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class SubscriptionId implements Serializable {

    @Column(name = "subscriber_id")
    private long subscriberId;

    @Column(name = "channel_id")
    private long channelId;
}
