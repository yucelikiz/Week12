package dev.patika.spring.service;

import dev.patika.spring.dto.request.AuthorRequest;
import dev.patika.spring.dto.response.AuthorResponse;
import dev.patika.spring.entity.Author;
import dev.patika.spring.mapper.AuthorMapper;
import dev.patika.spring.repository.AuthorRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;
    private final AuthorMapper authorMapper;

    public List<AuthorResponse> findAll(){return authorMapper.asOutput(authorRepo.findAll());}

    public AuthorResponse getById(Long id){
        return authorMapper.asOutput(authorRepo.findById(id).orElseThrow(()-> new RuntimeException(id + "id'li Yazar Bulunamadı!")));
    }

    public AuthorResponse create(AuthorRequest request){
        //Db'de aynı yazarın olup olmadığını kontrol et, eğer yoksa bu yazarı ekle
        Optional<Author> isAuthorExist = authorRepo.findByNameAndBirthDateAndCountry(request.getName(), request.getBirthDate(), request.getCountry());

        if (isAuthorExist.isEmpty()){
            Author authorSaved = authorRepo.save(authorMapper.asEntity(request));
            return authorMapper.asOutput(authorSaved);
        }
        throw new RuntimeException("Bu yazar daha önce sisteme kaydedilmiştir!");
    }

    public AuthorResponse update(Long id, AuthorRequest request){
        Optional<Author> authorFromDb = authorRepo.findById(id);

        Optional<Author> isAuthorExist = authorRepo.findByNameAndBirthDateAndCountry(request.getName(), request.getBirthDate(), request.getCountry());

        if (authorFromDb.isEmpty()) {
            throw new RuntimeException(id + " Güncellemeye çalıştığınız yazar sistemde bulunamadı!");
        }

        if (isAuthorExist.isPresent()){
            throw new RuntimeException("Bu yazar daha önce sisteme kayıt olmuştur!");
        }
        Author author = authorFromDb.get();
        authorMapper.update(author, request);
        return authorMapper.asOutput(authorRepo.save(author));
    }

    public void deleteById(Long id) {
        Optional<Author> authorFromDb = authorRepo.findById(id);
        if (authorFromDb.isPresent()) {
            authorRepo.delete(authorFromDb.get());
        }else {
            throw new RuntimeException(id + " id'li yazar sistemde bulunamadı!");
        }
    }
}
