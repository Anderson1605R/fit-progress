package br.com.rochadev.fit_progress.controllers;

import br.com.rochadev.fit_progress.model.UsuarioModel;
import br.com.rochadev.fit_progress.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listar() {
        return ResponseEntity.ok(usuarioService.buscarTodosUsuarios());
    }

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioModel> salvarUsuario(@RequestBody UsuarioModel usuario) {
        var usuarioSalvo = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<UsuarioModel> editar(@PathVariable("id") long id, @RequestBody UsuarioModel usuarioAtualizado) {
        var usuarioAtt = usuarioService.editarUsuario(usuarioAtualizado, id);
        return ResponseEntity.ok(usuarioAtt);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") long id) {
        usuarioService.apagarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}