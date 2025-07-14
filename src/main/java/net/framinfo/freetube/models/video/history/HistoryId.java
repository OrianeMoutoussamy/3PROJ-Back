package net.framinfo.freetube.models.video.history;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class HistoryId implements Serializable {

    @Column(name = "channel_id")
    private long channelId;

    @Column(name = "video_id")
    private long videoId;
}
