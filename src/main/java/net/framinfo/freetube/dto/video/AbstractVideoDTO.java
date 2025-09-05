package net.framinfo.freetube.dto.video;

import lombok.Getter;
import lombok.Setter;
import net.framinfo.freetube.dto.channel.ChannelDTO;
import net.framinfo.freetube.models.video.Comment;

import java.time.Instant;
import java.util.List;

@Getter @Setter
public abstract class AbstractVideoDTO {

    protected Long id;

    protected ChannelDTO channel;

    protected byte[] thumbnail;

    protected String title;

    protected String description;

    protected Float duration;

    protected Instant createdAt;

    protected List<Comment> comments;

    protected Long likes;

    protected Long dislikes;
}
