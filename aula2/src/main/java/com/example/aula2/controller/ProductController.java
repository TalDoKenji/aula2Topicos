package com.example.aula2.controller;

import com.example.aula2.model.Product;
import com.example.aula2.service.ProductService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/products")
public class ProductController {

    @Autowired
    private  ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> find() {
        return ResponseEntity.ok(service.find());
    }

    @PostMapping
    public void create(@RequestBody Product product) {
        service.create(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> findById(@PathParam(value = "id") Long id) {
        return ResponseEntity.ok(service.find());
    }

    @PutMapping("/{id}")
    public void update(@PathParam(value = "id") Long id, @RequestBody Product product) {
        service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathParam(value = "id") Long id) {
        service.delete(id);
    }
}
