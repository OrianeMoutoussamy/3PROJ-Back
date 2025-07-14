package net.framinfo.freetube.models.playlist;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PlaylistVideoId implements Serializable {

    @Column(name = "playlist_id")
    private long playlistId;

    @Column(name = "video_id")
    private long videoId;
}
