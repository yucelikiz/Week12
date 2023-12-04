package dev.patika.spring.service;

import dev.patika.spring.dto.request.BookBorrowingRequest;
import dev.patika.spring.dto.request.BookBorrowingUpdateRequest;
import dev.patika.spring.entity.Book;
import dev.patika.spring.entity.BookBorrowing;
import dev.patika.spring.mapper.BookBorrowingMapper;
import dev.patika.spring.repository.BookBorrowingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookBorrowingService {

    private final BookBorrowingRepo bookBorrowingRepo;
    private final BookService bookService;
    private final BookBorrowingMapper bookBorrowingMapper;

    public List<BookBorrowing> findAll() {
        return this.bookBorrowingRepo.findAll();
    }

    public BookBorrowing getById(Long id) {
        return bookBorrowingRepo.findById(id).orElseThrow(() -> new RuntimeException(id + "id'li ödünç alımı bulunamadı!"));
    }


    public BookBorrowing create(BookBorrowingRequest bookBorrowingRequest) {

        if (bookBorrowingRequest.getBookForBorrowingRequest().getStock() <= 0) {
            throw new RuntimeException("Ödünç almak istediğiniz kitabın stoğu yoktur!");
        }

        BookBorrowing bookBorrowing = bookBorrowingMapper.asEntity(bookBorrowingRequest);


        Book book = bookService.getById(bookBorrowingRequest.getBookForBorrowingRequest().getId());
        book.setStock(book.getStock() - 1);

        Book bookUpdated = bookService.update(bookBorrowingRequest.getBookForBorrowingRequest().getId(), book);
        bookBorrowing.setBook(bookUpdated);
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    public BookBorrowing update(Long id, BookBorrowingUpdateRequest bookBorrowingUpdateRequest) {

        Optional<BookBorrowing> bookBorrowingFromDb = bookBorrowingRepo.findById(id);

        if (bookBorrowingFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız ödünç alım sistemde bulunamadı!");
        }

        if (bookBorrowingUpdateRequest.getReturnDate() != null) {
            Book book = bookBorrowingFromDb.get().getBook();
            book.setStock(book.getStock() + 1);

            Book bookUpdated = bookService.update(book.getId(), book);

        }
        BookBorrowing bookBorrowing = bookBorrowingFromDb.get();
        System.out.println(bookBorrowing.getId());
        bookBorrowingMapper.update(bookBorrowing, bookBorrowingUpdateRequest);
        return bookBorrowingRepo.save(bookBorrowing);
    }

    public void deleteById(Long id) {
        Optional<BookBorrowing> bookBorrowingFromDb = bookBorrowingRepo.findById(id);
        if (bookBorrowingFromDb.isPresent()) {
            BookBorrowing bookBorrowing = bookBorrowingFromDb.get();
            if (bookBorrowing.getReturnDate() == null) {
                Book book = bookBorrowing.getBook();
                book.setStock(book.getStock() + 1);
                Book bookUpdated = bookService.update(bookBorrowing.getBook().getId(), book);
                bookBorrowing.setBook(bookUpdated);
            }
            bookBorrowingRepo.delete(bookBorrowing);

        } else {
            throw new RuntimeException(id + "id'li ödünç alımı sistemde bulunamadı!");
        }
    }

}
