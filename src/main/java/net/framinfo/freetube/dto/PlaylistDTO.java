package net.framinfo.freetube.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.framinfo.freetube.models.playlist.Playlist;
import net.framinfo.freetube.models.video.Video;

import java.time.Instant;
import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
public class PlaylistDTO {

    private Long id;

    private String name;

    private Instant createdAt;

    private Instant updatedAt;

    private List<Video> videos; //TODO change to VideoDTO

    public PlaylistDTO(Playlist playlist) {
        this.id = playlist.getId();
        this.name = playlist.getName();
        this.createdAt = playlist.getCreatedAt();
        this.updatedAt = playlist.getUpdatedAt();
        this.videos = playlist.getVideos();
    }
}
