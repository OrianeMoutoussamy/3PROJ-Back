package net.framinfo.freetube.models.video.hashtag;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@Embeddable
public class VideoHashtagId implements Serializable {

    @Column(name = "hashtag_id")
    private Long hashtagId;

    @Column(name = "video_id")
    private Long videoId;
}
