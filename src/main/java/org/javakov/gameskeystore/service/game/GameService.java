package org.javakov.gameskeystore.service.game;

import org.javakov.gameskeystore.dto.GameInDTO;
import org.javakov.gameskeystore.dto.GameOutDTO;
import org.springframework.data.domain.Page;

public interface GameService {
    GameOutDTO createGame(GameInDTO gameInDTO);
    GameOutDTO updateGame(int id, GameInDTO gameInDTO);
    void deleteGame(int id);
    GameOutDTO getGame(int id);
    Page<GameOutDTO> getAllGames(int page, int size);
}
