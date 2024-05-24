package org.javakov.gameskeystore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@Tag(name = "Redirect")
@RequiredArgsConstructor
public class RedirectController {

    @GetMapping("/{any}")
    @Operation(summary = "Redirect to Main Page")
    public String redirect() {
        log.info("Переадресация на главную страницу...");
        return "redirect:/games";
    }
}
