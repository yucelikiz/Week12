package dev.patika.spring.repository;

import dev.patika.spring.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepo extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByNameAndEstablishmentYear(String name, Integer year);
}
