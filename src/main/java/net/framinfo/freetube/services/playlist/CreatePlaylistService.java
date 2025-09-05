package net.framinfo.freetube.services.playlist;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import net.framinfo.freetube.dto.PlaylistDTO;
import net.framinfo.freetube.models.playlist.Playlist;

@ApplicationScoped
public class CreatePlaylistService extends AbstractPlaylistService{

    public Uni<PlaylistDTO> run(String token, Playlist playlist) {
        return sessionDelegate.getChannelFromToken(token)
                .flatMap(it -> {
                    playlist.setChannelId(it.getId());
                    return playlist.persist();
                })
                .map(it -> new PlaylistDTO((Playlist) it));
    }
}
