package net.framinfo.freetube.models.channel;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "subscription")
public class Subscription extends PanacheEntityBase implements Serializable {

    @EmbeddedId
    private SubscriptionId id;

    /**
     * User subscribing to a channel
     */
    @JoinColumn(name = "subscriber_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Channel subscriber;

    /**
     * Channel which user is subscribing to
     */
    @JoinColumn(name = "channel_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Channel channel;
}
