package br.com.rochadev.fit_progress.controllers;

import br.com.rochadev.fit_progress.model.UsuarioModel;
import br.com.rochadev.fit_progress.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> usuarioPorID(@PathVariable("id") long id){
        return ResponseEntity.ok(usuarioService.buscarUsuarioId(id));

    }
    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validar(@RequestParam String email, @RequestParam String senha){
        Optional<UsuarioModel> optionalUsuario = usuarioService.buscarUsuarioPorEmail(email);

        if(optionalUsuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        UsuarioModel usuario = optionalUsuario.get();
        boolean valid = usuarioService.passwordEncoder().matches(senha, usuario.getSenha());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    };

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioModel> salvarUsuario(@RequestBody UsuarioModel usuario) {
        usuario.setSenha(usuarioService.passwordEncoder().encode(usuario.getSenha()));
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