package dev.patika.spring.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublisherResponse {
    private Long id;
    private String name;
    private Integer establishmentYear;
}
