package dev.patika.spring.dto.request;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookBorrowingRequest {
    private String borrowerName;
    private String borrowerMail;
    private LocalDate borrowingDate;
    private BookForBorrowingRequest bookForBorrowingRequest;
}
