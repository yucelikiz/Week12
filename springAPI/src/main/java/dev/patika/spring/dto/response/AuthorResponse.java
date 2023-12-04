package dev.patika.spring.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String counrty;
}
