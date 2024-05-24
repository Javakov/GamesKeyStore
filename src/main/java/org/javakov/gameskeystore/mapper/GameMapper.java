package org.javakov.gameskeystore.mapper;

import org.javakov.gameskeystore.dto.GameInDTO;
import org.javakov.gameskeystore.dto.GameOutDTO;
import org.javakov.gameskeystore.entity.GameEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameEntity toEntity(GameInDTO gameInDTO);
    GameOutDTO toDTO(GameEntity gameEntity);
}
