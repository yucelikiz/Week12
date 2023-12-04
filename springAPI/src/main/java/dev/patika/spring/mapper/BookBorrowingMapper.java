package dev.patika.spring.mapper;

import dev.patika.spring.dto.request.BookBorrowingRequest;
import dev.patika.spring.dto.request.BookBorrowingUpdateRequest;
import dev.patika.spring.entity.BookBorrowing;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface BookBorrowingMapper {
    BookBorrowing asEntity(BookBorrowingRequest bookBorrowingRequest);

    BookBorrowing asEntity(BookBorrowingUpdateRequest bookBorrowingUpdateRequest);

    void update(@MappingTarget BookBorrowing entity, BookBorrowingUpdateRequest bookBorrowingUpdateRequest);
}
