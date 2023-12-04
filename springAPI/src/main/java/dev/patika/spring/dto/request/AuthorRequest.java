package dev.patika.spring.dto.request;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorRequest {

    private String name;
    private LocalDate birthDate;
    private String country;
}
