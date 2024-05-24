package org.javakov.gameskeystore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.javakov.gameskeystore.dto.GameInDTO;
import org.javakov.gameskeystore.dto.GameOutDTO;
import org.javakov.gameskeystore.service.game.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Tag(name = "Game's API")
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @PostMapping
    @Operation(summary = "Create Game")
    public ResponseEntity<GameOutDTO> createGame(@ModelAttribute GameInDTO gameInDTO) {
        GameOutDTO gameOutDTO = gameService.createGame(gameInDTO);
        return ResponseEntity.ok(gameOutDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Game")
    public ResponseEntity<GameOutDTO> updateGame(@PathVariable Integer id, @ModelAttribute GameInDTO gameInDTO) {
        GameOutDTO gameOutDTO = gameService.updateGame(id, gameInDTO);
        return ResponseEntity.ok(gameOutDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Game")
    public ResponseEntity<Void> deleteGame(@PathVariable Integer id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Game")
    public ResponseEntity<GameOutDTO> getGame(@PathVariable Integer id) {
        GameOutDTO gameOutDTO = gameService.getGame(id);
        return ResponseEntity.ok(gameOutDTO);
    }
}