package net.framinfo.freetube.models.channel;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
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

@Getter @Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "channel")
public class Channel extends PanacheEntity implements Serializable {

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
}
