package dev.patika.spring.mapper;

import dev.patika.spring.dto.request.AuthorRequest;
import dev.patika.spring.dto.response.AuthorResponse;
import dev.patika.spring.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AuthorMapper {
    Author asEntity(AuthorRequest authorRequest);

    AuthorResponse asOutput(Author author);

    List<AuthorResponse> asOutput(List<Author> author);

    void update(@MappingTarget Author entity, AuthorRequest request);
}
