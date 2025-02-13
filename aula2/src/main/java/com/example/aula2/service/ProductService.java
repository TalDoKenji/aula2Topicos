package com.example.aula2.service;

import com.example.aula2.model.Product;
import com.example.aula2.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product create(Product product){
        return repository.save(product);
    }

    public List<Product> find(){
        return repository.findAll();
    }

    public void update(Long id, Product productUpdated){
        final Product product = findById(id);
        product.setName(productUpdated.getName());
        product.setPrice(productUpdated.getPrice());
        product.setDescription(productUpdated.getDescription());
        product.setCategories(productUpdated.getCategories());

        repository.save(product);
    }

    public void delete(Long id){
        Product product = findById(id);
        repository.delete(product);
    }

    private Product findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não encontrado"));
    }

}
