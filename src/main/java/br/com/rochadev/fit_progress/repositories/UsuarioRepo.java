package br.com.rochadev.fit_progress.repositories;

import br.com.rochadev.fit_progress.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<UsuarioModel, Long> {


}
