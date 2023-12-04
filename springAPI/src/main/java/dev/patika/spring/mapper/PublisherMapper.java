package dev.patika.spring.mapper;

import dev.patika.spring.dto.response.PublisherResponse;
import dev.patika.spring.entity.Publisher;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PublisherMapper {
    PublisherResponse asOutput(Publisher publisher);

    List<PublisherResponse> asOutput(List<Publisher> publishers);
}
