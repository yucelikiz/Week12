package dev.patika.spring.api;

import dev.patika.spring.entity.Category;
import dev.patika.spring.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category getById(@PathVariable("id") Long id) {
        return categoryService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Category save(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category update(@PathVariable Long id, @RequestBody Category newCategory) {
        return categoryService.update(id, newCategory);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable("id") Long id) {
        return categoryService.deleteById(id);
    }
}
