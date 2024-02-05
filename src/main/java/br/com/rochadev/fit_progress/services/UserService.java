package br.com.rochadev.fit_progress.services;

import br.com.rochadev.fit_progress.model.UserModel;
import br.com.rochadev.fit_progress.repositories.UserRepo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    private final UserRepo ur;

    public UserService(UserRepo ur) {
        this.ur = ur;
    }

    public UserModel save(UserModel newUser){
        return ur.save(newUser);
    }
}
