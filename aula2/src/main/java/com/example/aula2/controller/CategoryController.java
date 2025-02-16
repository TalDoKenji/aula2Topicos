package com.example.aula2.controller;

import com.example.aula2.model.Category;
import com.example.aula2.service.CategoryService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/category")
@RestController()
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping()
    public ResponseEntity<List<Category>> find(){

        return ResponseEntity.ok(service.find());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable(value = "id") Long id){
        service.findById(id);
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Category> create(@RequestBody Category category, UriComponentsBuilder uriComponentsBuilder) {
        Category newCategory = service.create(category);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCategory)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id, @RequestBody Category category){
        service.update(id, category);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id")Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
