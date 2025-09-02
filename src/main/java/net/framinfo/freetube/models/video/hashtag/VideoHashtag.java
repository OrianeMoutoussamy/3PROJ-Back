package net.framinfo.freetube.models.video.hashtag;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.framinfo.freetube.models.video.Video;

import java.io.Serializable;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "video_hashtag")
public class VideoHashtag extends PanacheEntityBase implements Serializable {

    @EmbeddedId
    private VideoHashtagId id;

    @JoinColumn(name = "hashtag_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Hashtag hashtag;

    @JoinColumn(name = "video_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Video video;
}
