package org.javakov.gameskeystore.service.game;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javakov.gameskeystore.dto.GameInDTO;
import org.javakov.gameskeystore.dto.GameOutDTO;
import org.javakov.gameskeystore.entity.GameEntity;
import org.javakov.gameskeystore.exception.GameNotFound;
import org.javakov.gameskeystore.mapper.GameMapper;
import org.javakov.gameskeystore.repository.GameRepository;
import org.javakov.gameskeystore.service.firebase.FirebaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final FirebaseService firebaseService;
    private final GameMapper gameMapper;

    @Override
    @Transactional
    public GameOutDTO createGame(GameInDTO gameInDTO) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setName(gameInDTO.getName());
        gameEntity.setPrice(gameInDTO.getPrice());
        gameEntity.setImgUrl(firebaseService.uploadImg(gameInDTO.getMultipartFile()));

        gameRepository.saveAndFlush(gameEntity);

        return gameMapper.toDTO(gameEntity);
    }

    @Override
    @Transactional
    public GameOutDTO updateGame(int id, GameInDTO gameInDTO) {
        GameEntity gameEntity = gameRepository.findById(id)
                .map(existingGame -> {
                    String imageURL = existingGame.getImgUrl();

                    if (!gameInDTO.getMultipartFile().isEmpty()) {
                        imageURL = firebaseService.updateImg(existingGame.getImgUrl(), gameInDTO.getMultipartFile());
                    }

                    existingGame.setName(gameInDTO.getName());
                    existingGame.setPrice(gameInDTO.getPrice());
                    existingGame.setImgUrl(imageURL);

                    return gameRepository.saveAndFlush(existingGame);
                })
                .orElseThrow(() -> new GameNotFound(id));

        return gameMapper.toDTO(gameEntity);
    }

    @Override
    @Transactional
    public void deleteGame(int id) {
        GameEntity gameEntity = gameRepository.findById(id).orElseThrow(() -> new GameNotFound(id));
        gameRepository.deleteById(id);
        log.info("Deleted game: {}", gameEntity.getImgUrl());
        firebaseService.deleteImg(gameEntity.getImgUrl());
    }

    @Override
    public GameOutDTO getGame(int id) {
        return gameRepository.findById(id)
                .map(gameMapper::toDTO)
                .orElseThrow(() -> new GameNotFound(id));
    }

    @Override
    public Page<GameOutDTO> getAllGames(Pageable pageable) {
        Page<GameEntity> gamePage = gameRepository.findAll(pageable);
        return gamePage.map(gameMapper::toDTO);
    }

    @Override
    public Page<GameOutDTO> searchGamesByName(String name, Pageable pageable) {
        Page<GameEntity> gamePage = gameRepository.findByNameContainingIgnoreCase(name, pageable);
        return gamePage.map(gameMapper::toDTO);
    }
}
