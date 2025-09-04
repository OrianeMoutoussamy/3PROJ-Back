package net.framinfo.freetube.models.auth;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "ftsession")
public class Session extends PanacheEntityBase implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 256, unique = true)
    private String token;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "expire_at", nullable = false)
    private Instant expireAt;

    public Session(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
        this.expireAt = Instant.now().plusSeconds(3600*24); // 24hr tokens
    }
}
