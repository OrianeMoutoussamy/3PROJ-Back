package net.framinfo.freetube.models.video;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
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
@Table(name = "reaction")
public class Reaction extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @Column(name = "channel_id", insertable=false, updatable=false)
    private Long channelId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(name = "video_id", insertable=false, updatable=false)
    private Long videoId;

    /**
     * Should always be 1 (like) or -1 (dislike)
     */
    @Column()
    private Long type;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    public Reaction(Long channelId, Long videoId, Long type) {
        this.channelId = channelId;
        this.videoId = videoId;
        this.type = type;
    }
}
