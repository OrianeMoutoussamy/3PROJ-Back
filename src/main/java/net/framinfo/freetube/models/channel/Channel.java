package net.framinfo.freetube.models.channel;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.framinfo.freetube.models.auth.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "channel")
public class Channel extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "user_id", insertable=false, updatable=false)
    private Long userId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;

    @Column(length = 64, unique = true)
    private String username;

    @Column(length = 256)
    private String description;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    @JoinTable(
            name = "subscription",
            joinColumns = @JoinColumn(
                    name = "subscriber_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "channel_id",
                    referencedColumnName = "id"
            )
    )
    @OneToMany(fetch = FetchType.EAGER)
    private List<Channel> subscriptions;

    @JoinTable(
            name = "subscription",
            joinColumns = @JoinColumn(
                    name = "channel_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "subscriber_id",
                    referencedColumnName = "id"
            )
    )
    @OneToMany(fetch = FetchType.EAGER)
    private List<Channel> subscribers;
}
