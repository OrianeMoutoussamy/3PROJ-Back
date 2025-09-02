package net.framinfo.freetube.models.playlist;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.framinfo.freetube.models.channel.Channel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "playlist")
public class Playlist extends PanacheEntity implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @Column(length = 64)
    private String name;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;
}
