package net.framinfo.freetube.models.video.history;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@Embeddable
public class HistoryId implements Serializable {

    @Column(name = "channel_id", insertable=false, updatable=false)
    private Long channelId;

    @Column(name = "video_id", insertable=false, updatable=false)
    private Long videoId;
}
