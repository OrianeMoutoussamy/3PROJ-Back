package net.framinfo.freetube.models.video.hashtag;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.framinfo.freetube.models.video.Video;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "hashtag")
public class Hashtag extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(length = 32, unique = true)
    private String name;

    @JoinTable(
            name = "video_hashtag",
            joinColumns = @JoinColumn(
                    name = "hashtag_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "video_id",
                    referencedColumnName = "id"
            )
    )
    @OneToMany(fetch = FetchType.EAGER)
    private List<Video> videos;

    public Hashtag(String name) {
        this.name = name;
    }
}
