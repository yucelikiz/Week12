package dev.patika.spring.service;

import dev.patika.spring.entity.Book;
import dev.patika.spring.repository.BookRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book getById(Long id) {
        return bookRepo.findById(id).orElseThrow(() -> new RuntimeException(id + "id'li Kitap Bulunamadı!"));
    }

    @Transactional
    public Book create(Book book) {
        Optional<Book> isBookExist = bookRepo.findByNameAndAuthor(book.getName(), book.getAuthor());

        if (isBookExist.isEmpty()) {
            return this.bookRepo.save(book);
        }
        throw new RuntimeException("Bu kitap daha önce sisteme kayıt olmuştur!");
    }

    public Book update(Long id, Book book) {

        Optional<Book> bookFromDb = bookRepo.findById(id);

        if (bookFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız kitap sistemde bulunamadı!");
        }

        book.setId(id);
        return this.bookRepo.save(book);
    }

    public void deleteById(Long id) {
        Optional<Book> bookFromDb = bookRepo.findById(id);
        if (bookFromDb.isPresent()) {
            bookRepo.delete(bookFromDb.get());
        } else {
            throw new RuntimeException(id + "id'li Kitap sistemde bulunamadı!");
        }
    }

    public List<Book> findByCategoryId(Long id) {
        return bookRepo.findByCategoryId(id);
    }
}
