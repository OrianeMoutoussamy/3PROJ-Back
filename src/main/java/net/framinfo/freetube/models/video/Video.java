package net.framinfo.freetube.models.video;


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
@Table(name = "video")
public class Video extends PanacheEntity implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @Lob
    private byte[] thumbnail;

    @Column(length = 64)
    private String title;

    @Column(length = 256)
    private String description;

    @Column
    private Float duration;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;
}
