package net.framinfo.freetube.dto;

import lombok.Getter;
import lombok.Setter;
import net.framinfo.freetube.models.video.Video;
import net.framinfo.freetube.models.video.hashtag.Hashtag;

import java.util.List;

@Getter @Setter
public class HashtagDTO {

    private Long id;

    private String name;

    private List<Video> videos;

    public HashtagDTO(Hashtag hashtag) {
        this.id = hashtag.getId();
        this.name = hashtag.getName();
        this.videos = hashtag.getVideos();
    }
}
