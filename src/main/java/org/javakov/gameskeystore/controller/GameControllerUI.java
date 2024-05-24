package org.javakov.gameskeystore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.javakov.gameskeystore.dto.GameOutDTO;
import org.javakov.gameskeystore.service.game.GameService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Tag(name = "Main Page")
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameControllerUI {
    private final GameService gameService;

    @GetMapping
    @Operation(summary = "All Games")
    public String getAllGames(@RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "100") Integer size,
                              Model model) {
        Page<GameOutDTO> gamePage = gameService.getAllGames(page, size);
        model.addAttribute("title", "Javakov Key Store");
        model.addAttribute("games", gamePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", gamePage.getTotalPages());
        return "index";
    }
}
