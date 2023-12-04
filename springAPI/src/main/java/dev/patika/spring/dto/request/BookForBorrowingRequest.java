package dev.patika.spring.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookForBorrowingRequest {
    private Long id;
    private String name;
    private int publicationYear;
    private int stock;
}
