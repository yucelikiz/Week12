package dev.patika.spring.api;

import dev.patika.spring.entity.Book;
import dev.patika.spring.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getById(@PathVariable("id") Long id) {
        return bookService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Book save(@RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book update(@PathVariable Long id, @RequestBody Book newBook) {
        return bookService.update(id, newBook);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }
}
