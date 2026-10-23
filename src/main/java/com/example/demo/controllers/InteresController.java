package com.example.demo.controllers;

import com.example.demo.models.Interes;
import com.example.demo.repositories.InteresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/intereses")
public class InteresController {

    @Autowired
    private InteresRepository repository;

    @GetMapping
    public List<Interes> obtenerTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Interes crear(@RequestBody Interes elemento) {
        return repository.save(elemento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interes> actualizar(@PathVariable Long id, @RequestBody Interes detalles) {
        Optional<Interes> existente = repository.findById(id);
        if (existente.isPresent()) {
            Interes actualizado = existente.get();
            actualizado.setNombre(detalles.getNombre());
            actualizado.setCategoria(detalles.getCategoria());
            return ResponseEntity.ok(repository.save(actualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
