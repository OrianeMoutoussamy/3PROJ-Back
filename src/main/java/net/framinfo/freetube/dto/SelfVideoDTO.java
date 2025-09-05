package net.framinfo.freetube.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.framinfo.freetube.models.video.Comment;
import net.framinfo.freetube.models.video.Video;

import java.time.Instant;
import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
public class SelfVideoDTO {

    private Long id;

    private ChannelDTO channel;

    private byte[] thumbnail;

    private String title;

    private String description;

    private Float duration;

    private boolean isPublic;

    private Instant createdAt;

    private Instant updatedAt;

    private List<Comment> comments;

    private Long likes;

    private Long dislikes;

    public SelfVideoDTO(Video video, Boolean subscribed) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.thumbnail = video.getThumbnail();
        this.createdAt = video.getCreatedAt();
        this.comments = video.getComments();
        this.duration = video.getDuration();
        this.isPublic = video.isPublic();
        this.updatedAt = video.getUpdatedAt();
        this.channel = new ChannelDTO(video.getChannel(), subscribed);
        this.likes = video.getReactions().stream().filter(it -> it.getType().equals(1L)).count();
        this.dislikes = video.getReactions().stream().filter(it -> it.getType().equals(-1L)).count();
    }
}
