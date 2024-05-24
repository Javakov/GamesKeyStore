package org.javakov.gameskeystore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "games")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "price", length = 10)
    private Integer price;

    @Column(name = "img_url", length = 500)
    private String imgUrl;
}
