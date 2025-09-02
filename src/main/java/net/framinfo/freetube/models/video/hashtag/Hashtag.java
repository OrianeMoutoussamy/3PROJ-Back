package net.framinfo.freetube.models.video.hashtag;

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
@Table(name = "hashtag")
public class Hashtag extends PanacheEntity implements Serializable {

    @Column(length = 32, unique = true)
    private String name;
}
