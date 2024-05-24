package org.javakov.gameskeystore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameInDTO {
    @Size(max = 100)
    @NotBlank(message = "Название игры обязательно")
    String name;

    @Size(max = 10)
    @NotBlank(message = "Цена игры обязательно")
    @Pattern(regexp = "\\d{1,10}", message = "Некорректный формат цены")
    Integer price;

    MultipartFile multipartFile;;
}
