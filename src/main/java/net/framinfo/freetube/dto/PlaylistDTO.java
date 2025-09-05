package net.framinfo.freetube.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.framinfo.freetube.dto.video.VideoDTO;
import net.framinfo.freetube.models.playlist.Playlist;

import java.time.Instant;
import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
public class PlaylistDTO {

    private Long id;

    private String name;

    private Instant createdAt;

    private Instant updatedAt;

    private List<VideoDTO> videos;

    public PlaylistDTO(Playlist playlist) {
        this.id = playlist.getId();
        this.name = playlist.getName();
        this.createdAt = playlist.getCreatedAt();
        this.updatedAt = playlist.getUpdatedAt();
        this.videos = playlist.getVideos().stream().map(it -> new VideoDTO(it, false)).toList();
    }
}
