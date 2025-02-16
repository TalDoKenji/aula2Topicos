package com.example.aula2.controller;

import com.example.aula2.model.Product;
import com.example.aula2.service.ProductService;
import jakarta.websocket.server.PathParam;

import java.net.URI;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/products")
@RestController()
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> find() {
        return ResponseEntity.ok(service.find());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> findById(@PathParam(value = "id") Long id) {
        return ResponseEntity.ok(service.find());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Product product) {
        Product newProduct = service.create(product);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProduct)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathParam(value = "id") Long id, @RequestBody Product product) {
        service.update(id, product);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathParam(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
