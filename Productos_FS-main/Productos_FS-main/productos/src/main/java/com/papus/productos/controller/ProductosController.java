package com.papus.productos.controller;

import com.papus.productos.model.*;
import com.papus.productos.service.ProductosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class ProductosController {

    private ProductosService productosService;

    //GET - Listar
    @GetMapping
    public ResponseEntity<List<Productos>> listar() {
        return ResponseEntity.ok(productosService.findAll());
    }
    
    //GET - buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Productos> obtenerPorId(@PathVariable Long id){
        return productosService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    //POST - crear
    @PostMapping
    public ResponseEntity<Productos> crear(@RequestBody Productos productos) {
        return ResponseEntity.ok(productosService.save(productos));
    }

    //PUT - Actualzizar 
    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizar(@PathVariable Long id, @RequestBody Productos productos) {
        return productosService.findById(id).map(u -> {
            productos.setId(id);
            return ResponseEntity.ok(productosService.save(productos));
        }).orElse(ResponseEntity.notFound().build());
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (productosService.findById(id).isPresent()) {
            productosService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}