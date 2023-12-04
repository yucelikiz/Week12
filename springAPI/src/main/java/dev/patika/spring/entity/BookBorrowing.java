package dev.patika.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "book_borrowing")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "borrower_name", nullable = false)
    private String borrowerName;

    @Column(name = "borrower_mail", nullable = false)
    private String borrowerMail;

    @Temporal(TemporalType.DATE)
    @Column(name = "borrowing_date", nullable = false)
    private LocalDate borrowingDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

}
