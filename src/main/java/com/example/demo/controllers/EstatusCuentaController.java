package com.example.demo.controllers;

import com.example.demo.models.EstatusCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estatus-cuenta")
public class EstatusCuentaController {

    @Autowired
    private EstatusCuentaRepository repository;

    @GetMapping
    public List<EstatusCuenta> obtenerTodos() {
        return repository.findAll();
    }

    @PostMapping
    public EstatusCuenta crear(@RequestBody EstatusCuenta elemento) {
        if (elemento.getDescripcion() == null || elemento.getDescripcion().isEmpty()) {
            elemento.setDescripcion("Estatus asignado al perfil del usuario ratilla");
        }
        return repository.save(elemento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstatusCuenta> actualizar(@PathVariable Long id, @RequestBody EstatusCuenta detalles) {
        Optional<EstatusCuenta> existente = repository.findById(id);
        if (existente.isPresent()) {
            EstatusCuenta actualizado = existente.get();
            actualizado.setNombre(detalles.getNombre());
            actualizado.setDescripcion(detalles.getDescripcion());
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