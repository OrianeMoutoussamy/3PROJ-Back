package net.framinfo.freetube.models.video;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.framinfo.freetube.models.channel.Channel;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "comment")
public class Comment extends PanacheEntity implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(length = 256)
    private String content;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;
}
