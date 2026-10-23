package com.example.demo.controllers;

import com.example.demo.models.PlanSuscripcion;
import com.example.demo.repositories.PlanSuscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planes-suscripcion")
public class PlanSuscripcionController {

    @Autowired
    private PlanSuscripcionRepository repository;

    @GetMapping
    public List<PlanSuscripcion> obtenerTodos() {
        return repository.findAll();
    }

    @PostMapping
    public PlanSuscripcion crear(@RequestBody PlanSuscripcion elemento) {
        return repository.save(elemento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanSuscripcion> actualizar(@PathVariable Long id, @RequestBody PlanSuscripcion detalles) {
        Optional<PlanSuscripcion> existente = repository.findById(id);
        if (existente.isPresent()) {
            PlanSuscripcion actualizado = existente.get();
            actualizado.setNombre(detalles.getNombre());
            actualizado.setPrecio(detalles.getPrecio());
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