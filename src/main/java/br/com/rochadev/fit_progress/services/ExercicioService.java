package br.com.rochadev.fit_progress.services;

import br.com.rochadev.fit_progress.model.CategoriaModel;
import br.com.rochadev.fit_progress.model.ExercicioModel;
import br.com.rochadev.fit_progress.repositories.CategoriaRepo;
import br.com.rochadev.fit_progress.repositories.ExercicioRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercicioService {

    private final ExercicioRepo exercicioRepo;
    private final CategoriaRepo categoriaRepo;

    public ExercicioService(ExercicioRepo exercicioRepo, CategoriaRepo categoriaRepo) {
        this.exercicioRepo = exercicioRepo;
        this.categoriaRepo = categoriaRepo;
    }

    public List<ExercicioModel> listarExercicios() {
        return exercicioRepo.findAll();
    }

    public void salvarExercicio(ExercicioModel exercicio, long id){
        var categoriaExistente = categoriaRepo.findById(id).orElseThrow(()-> new NullPointerException("Categoria não encontrada"));
        categoriaExistente.adicionarExercicio(exercicio);


        categoriaRepo.save(categoriaExistente);
    }

    public ExercicioModel atualizarExercicio(ExercicioModel exercicioAtualizado, long id){
        var exercicioExistente = exercicioRepo.findById(id).orElseThrow(()-> new NullPointerException("Exercício não encontrado!"));
        exercicioExistente.setNome(exercicioAtualizado.getNome());
        exercicioExistente.setNumeroSeries(exercicioAtualizado.getNumeroSeries());
        exercicioExistente.setQuantidadeCarga(exercicioAtualizado.getQuantidadeCarga());
        return exercicioRepo.save(exercicioExistente);
    }

    public void apagarExercicio(long id){
        exercicioRepo.deleteById(id);
    }

}
