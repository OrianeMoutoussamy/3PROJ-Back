package net.framinfo.freetube.models.channel;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
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
}
