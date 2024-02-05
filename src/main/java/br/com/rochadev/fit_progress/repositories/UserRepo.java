package br.com.rochadev.fit_progress.repositories;

import br.com.rochadev.fit_progress.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {

}
