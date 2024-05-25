package org.javakov.gameskeystore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Tag(name = "Redirect")
@RequiredArgsConstructor
public class RedirectController {

    @GetMapping("/{any}")
    @Operation(summary = "Redirect to Main Page")
    public String redirect() {
        return "redirect:/";
    }
}
