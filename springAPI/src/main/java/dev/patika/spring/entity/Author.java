package dev.patika.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "author")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Book> bookList;

}