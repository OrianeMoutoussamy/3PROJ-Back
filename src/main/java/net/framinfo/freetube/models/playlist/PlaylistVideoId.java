package net.framinfo.freetube.models.playlist;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@Embeddable
public class PlaylistVideoId implements Serializable {

    @Column(name = "playlist_id", insertable=false, updatable=false)
    private Long playlistId;

    @Column(name = "video_id", insertable=false, updatable=false)
    private Long videoId;
}
