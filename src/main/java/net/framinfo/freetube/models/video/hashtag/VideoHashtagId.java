package net.framinfo.freetube.models.video.hashtag;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class VideoHashtagId implements Serializable {

    @Column(name = "hashtag_id")
    private long hashtagId;

    @Column(name = "video_id")
    private long videoId;
}
