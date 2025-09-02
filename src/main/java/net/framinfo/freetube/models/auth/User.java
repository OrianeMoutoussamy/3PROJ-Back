package net.framinfo.freetube.models.auth;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user")
public class User extends PanacheEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(length = 64, unique = true)
    private String email;

    @Column(length = 256, unique = true)
    private String password;
}
