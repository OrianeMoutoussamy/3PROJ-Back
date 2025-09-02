package net.framinfo.freetube.models.video;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
public class Reaction extends PanacheEntity implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "video_id")
    private Video video;

    /**
     * Should always be 1 (like) or -1 (dislike)
     */
    @Column()
    @Pattern(regexp="1|-1")
    private Long type;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;
}
