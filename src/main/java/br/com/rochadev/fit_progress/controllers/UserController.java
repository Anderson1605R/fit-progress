package br.com.rochadev.fit_progress.controllers;

import br.com.rochadev.fit_progress.model.UserModel;
import br.com.rochadev.fit_progress.services.UserService;
import jakarta.validation.Valid;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService us;

    public UserController(UserService us) {
        this.us = us;
    }

    @GetMapping
    public String getUsers(){
        return "Olá";
    }

    @PostMapping("/save")
    public ResponseEntity<UserModel> save(@RequestBody @Valid UserModel user){
        var savedUser = us.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
