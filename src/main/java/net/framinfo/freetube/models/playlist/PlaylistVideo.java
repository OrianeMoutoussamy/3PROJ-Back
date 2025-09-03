package net.framinfo.freetube.models.playlist;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.framinfo.freetube.models.video.Video;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "playlist_video")
public class PlaylistVideo extends PanacheEntityBase implements Serializable {

    @EmbeddedId
    private PlaylistVideoId id;

    @Column(name = "added_at", nullable = false)
    @CreationTimestamp
    private Instant addedAt;

    @JoinColumn(name = "playlist_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Playlist playlist;

    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Video video;
}
