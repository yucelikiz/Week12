package dev.patika.spring.api;

import dev.patika.spring.dto.request.BookBorrowingRequest;
import dev.patika.spring.dto.request.BookBorrowingUpdateRequest;
import dev.patika.spring.entity.BookBorrowing;
import dev.patika.spring.service.BookBorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrow")
@RequiredArgsConstructor
public class BookBorrowingController {

    private final BookBorrowingService bookBorrowingService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<BookBorrowing> findAll() {
        return bookBorrowingService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookBorrowing getById(@PathVariable("id") Long id) {
        return bookBorrowingService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BookBorrowing save(@RequestBody BookBorrowingRequest bookBorrowingRequest) {
        return bookBorrowingService.create(bookBorrowingRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookBorrowing update(@PathVariable("id") Long id, @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest) {
        System.out.println("bookBorrowingId" + id);
        System.out.println("bookBorrowingUpdateRequest" + bookBorrowingUpdateRequest.getBorrowerName());
        return bookBorrowingService.update(id, bookBorrowingUpdateRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        bookBorrowingService.deleteById(id);
    }

}
