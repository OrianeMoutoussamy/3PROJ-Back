package net.framinfo.freetube.models.video.history;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.framinfo.freetube.models.channel.Channel;
import net.framinfo.freetube.models.video.Video;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "history")
public class History extends PanacheEntityBase implements Serializable {

    @EmbeddedId
    private HistoryId id;

    @Column(name = "seen_at", nullable = false)
    @CreationTimestamp
    private Instant seenAt;
}
