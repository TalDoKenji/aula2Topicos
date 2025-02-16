package com.example.aula2.service;

import com.example.aula2.model.Category;
import com.example.aula2.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository repository;

    public List<Category> find(){
        return repository.findAll();
    }

    public Category findById(Long id){
        return repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Categoria não encontrada"));
    }

    public Category create(Category category){
        return repository.save(category);
    }

    public void update(Long id,Category categoryUpdated){
        Category category = findById(id);
        category.setName(categoryUpdated.getName());

        repository.save(category);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
