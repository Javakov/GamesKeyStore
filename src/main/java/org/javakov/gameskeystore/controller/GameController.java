package org.javakov.gameskeystore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.javakov.gameskeystore.dto.GameInDTO;
import org.javakov.gameskeystore.dto.GameOutDTO;
import org.javakov.gameskeystore.service.game.GameService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Game's API")
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @Value("${api.key}")
    private String apiKey;

    @PostMapping
    @Operation(summary = "Create Game")
    public ResponseEntity<GameOutDTO> createGame(@RequestHeader("X-API-KEY") String apiKey, @ModelAttribute GameInDTO gameInDTO) {
        if (!apiKey.equals(this.apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        GameOutDTO gameOutDTO = gameService.createGame(gameInDTO);
        return ResponseEntity.ok(gameOutDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Game")
    public ResponseEntity<GameOutDTO> updateGame(@RequestHeader("X-API-KEY") String apiKey, @PathVariable Integer id, @ModelAttribute GameInDTO gameInDTO) {
        if (!apiKey.equals(this.apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        GameOutDTO gameOutDTO = gameService.updateGame(id, gameInDTO);
        return ResponseEntity.ok(gameOutDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Game")
    public ResponseEntity<Void> deleteGame(@RequestHeader("X-API-KEY") String apiKey, @PathVariable Integer id) {
        if (!apiKey.equals(this.apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

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