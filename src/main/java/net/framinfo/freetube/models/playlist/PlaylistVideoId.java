package net.framinfo.freetube.models.playlist;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@RequiredArgsConstructor
@Embeddable
public class PlaylistVideoId implements Serializable {

    @Column(name = "playlist_id")
    private Long playlistId;

    @Column(name = "video_id")
    private Long videoId;

    public PlaylistVideoId(Long playlistId, Long videoId) {
        this.playlistId = playlistId;
        this.videoId = videoId;
    }
}
