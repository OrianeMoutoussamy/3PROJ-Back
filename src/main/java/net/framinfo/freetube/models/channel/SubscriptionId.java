package net.framinfo.freetube.models.channel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@Embeddable
public class SubscriptionId implements Serializable {

    @Column(name = "subscriber_id", insertable=false, updatable=false)
    private Long subscriberId;

    @Column(name = "channel_id", insertable=false, updatable=false)
    private Long channelId;
}
