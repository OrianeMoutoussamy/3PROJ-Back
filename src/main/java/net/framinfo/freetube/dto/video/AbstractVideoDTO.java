package net.framinfo.freetube.dto.video;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.framinfo.freetube.dto.HashtagDTO;
import net.framinfo.freetube.dto.channel.ChannelDTO;
import net.framinfo.freetube.models.video.Comment;
import net.framinfo.freetube.models.video.Video;

import java.time.Instant;
import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
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

    protected Long views;

    protected List<HashtagDTO> hashtags;

    public AbstractVideoDTO(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.thumbnail = video.getThumbnail();
        this.createdAt = video.getCreatedAt();
        this.comments = video.getComments();
        this.duration = video.getDuration();
        this.views = (long) video.getViews().size();
        this.likes = video.getReactions().stream().filter(it -> it.getType().equals(1L)).count();
        this.dislikes = video.getReactions().stream().filter(it -> it.getType().equals(-1L)).count();
        this.hashtags = video.getHashtags().stream().map(HashtagDTO::new).toList();
    }
}
