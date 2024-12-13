package br.com.rochadev.fit_progress.controllers;

import br.com.rochadev.fit_progress.model.ExercicioModel;
import br.com.rochadev.fit_progress.services.ExercicioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercicio")
public class Exerciciocontroller {
    private final ExercicioService exercicioService;

    public Exerciciocontroller(ExercicioService exercicioService) {
        this.exercicioService = exercicioService;
    }


    @GetMapping
    public ResponseEntity<List<ExercicioModel>> listarExercicios() {
        return ResponseEntity.ok(exercicioService.listarExercicios());
    }

    @PostMapping("/salvar/{id}")
    public ResponseEntity<ExercicioModel> salvar( @RequestBody ExercicioModel exercicioModel, @PathVariable("id") long id) {
        exercicioService.salvarExercicio(exercicioModel, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ExercicioModel> atualizar(@RequestBody ExercicioModel exercicioAtualizado, @PathVariable("id") long id) {
        return ResponseEntity.ok(exercicioService.atualizarExercicio(exercicioAtualizado, id));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> apagar(@PathVariable("id") long id) {
        exercicioService.apagarExercicio(id);
        return ResponseEntity.noContent().build();
    }
}
