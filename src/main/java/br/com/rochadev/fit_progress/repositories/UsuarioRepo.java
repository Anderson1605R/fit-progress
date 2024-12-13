package br.com.rochadev.fit_progress.repositories;

import br.com.rochadev.fit_progress.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<UsuarioModel, Long> {
  Optional<UsuarioModel> findByEmail(String email);
  @Query("SELECT u.nome, c.nome, e " +
         "FROM UsuarioModel u " +
         "LEFT JOIN u.categorias c " +
         "LEFT JOIN c.exercicio e " +
         "WHERE u.id = :usuarioId")
  List<Object[]> findUsuarioCategoriasExercicios(@Param("usuarioId") Long usuarioId);


}
