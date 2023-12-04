package dev.patika.spring.repository;

import dev.patika.spring.entity.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookBorrowingRepo extends JpaRepository<BookBorrowing, Long> {
}
