package dev.patika.spring.mapper;

import dev.patika.spring.dto.request.BookForBorrowingRequest;
import dev.patika.spring.entity.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookForBorrowingMapper {

    Book asEntity(BookForBorrowingRequest bookForBorrowingRequest);
}
