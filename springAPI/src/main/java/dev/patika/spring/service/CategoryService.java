package dev.patika.spring.service;

import dev.patika.spring.entity.Book;
import dev.patika.spring.entity.Category;
import dev.patika.spring.repository.CategoryRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class CategoryService {
    private final CategoryRepo categoryRepo;
    private final BookService bookService;

    public List<Category> findAll() {return categoryRepo.findAll();}

    public Category getById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new RuntimeException(id + "id'li Kategori Bulunamadı!"));
    }

    public Category create(Category request) {
        // Name bizim için unique sayılıyor bu yüzden bu özelliğe sahip category var mı diye
        // DB den bakıyoruz yoksa create isteği atabiliriz.
        Optional<Category> isCategoryExist = categoryRepo.findByName(request.getName());

        if (isCategoryExist.isEmpty()) {
            return categoryRepo.save(request);
        }
        throw new RuntimeException("Bu kategori daha önce sisteme kayıt olmuştur !!!");
    }

    public Category update(Long id, Category request) {

        Optional<Category> categoryFromDb = categoryRepo.findById(id);

        Optional<Category> isCategoryExist = categoryRepo.findByName(request.getName());

        if (categoryFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız kategori sistemde bulunamadı. !!!.");
        }

        if (isCategoryExist.isPresent()) {
            throw new RuntimeException("Bu kategori daha önce sisteme kayıt olmuştur !!!");
        }
        request.setId(id);
        return categoryRepo.save(request);
    }

    public String deleteById(Long id) {
        Optional<Category> categoryFromDb = categoryRepo.findById(id);
        List<Book> booksInCategory = bookService.findByCategoryId(id);

        if (!categoryFromDb.isPresent()) {
            return id + " id'li Kategori sistemde bulunamadı!!!";
        } else if (!booksInCategory.isEmpty()) {
            return id + " id'li Kategoriye ait sistemde kayıtlı kitap mevcut! Silme işlemi yapılamadı.";
        } else {
            categoryRepo.delete(categoryFromDb.get());
            return "Kategori silme işlemi başarılı";
        }
    }
}
