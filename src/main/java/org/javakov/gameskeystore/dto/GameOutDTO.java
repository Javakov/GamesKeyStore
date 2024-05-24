package org.javakov.gameskeystore.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameOutDTO {
    int id;
    String name;
    Integer price;
    String imgUrl;
}
