package org.javakov.gameskeystore.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ToString
@RequiredArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Игра не найдена")
public class GameNotFound extends RuntimeException {
    private final long gameId;
}
