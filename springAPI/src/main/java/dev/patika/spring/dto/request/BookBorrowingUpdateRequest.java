package dev.patika.spring.dto.request;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookBorrowingUpdateRequest {
    private String borrowerName;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
}
