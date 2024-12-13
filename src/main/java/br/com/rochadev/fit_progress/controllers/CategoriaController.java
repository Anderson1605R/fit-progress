package br.com.rochadev.fit_progress.controllers;

import br.com.rochadev.fit_progress.model.CategoriaModel;
import br.com.rochadev.fit_progress.repositories.CategoriaRepo;
import br.com.rochadev.fit_progress.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaModel>> listaDeCategorias() {
        return ResponseEntity.ok(categoriaService.buscarTodasCategorias());
    }

    @PostMapping("/salvar/{id}")
    public ResponseEntity<CategoriaModel> salvar(@Valid @RequestBody CategoriaModel categoriaModel, @PathVariable("id") long id) {
        categoriaService.salvarCategoria(categoriaModel, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<CategoriaModel> atualizar(@RequestBody CategoriaModel categoriaAtualizada, @PathVariable("id") long id){
        return ResponseEntity.ok(categoriaService.atualizarCategoria(categoriaAtualizada, id));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") long id){
        categoriaService.apagarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
