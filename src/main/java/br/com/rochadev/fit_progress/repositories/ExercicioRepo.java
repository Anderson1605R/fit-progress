package br.com.rochadev.fit_progress.repositories;

import br.com.rochadev.fit_progress.model.ExercicioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioRepo extends JpaRepository<ExercicioModel,Long> {
}
