package br.com.rochadev.fit_progress.repositories;

import br.com.rochadev.fit_progress.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepo extends JpaRepository<CategoriaModel, Long> {
}
