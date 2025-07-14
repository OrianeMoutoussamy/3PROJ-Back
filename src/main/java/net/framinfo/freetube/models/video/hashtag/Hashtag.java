package net.framinfo.freetube.models.video.hashtag;

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
@Table(name = "hashtag")
public class Hashtag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(length = 32, unique = true)
    private String name;
}
