package br.com.rochadev.fit_progress.services;

import br.com.rochadev.fit_progress.model.UsuarioModel;
import br.com.rochadev.fit_progress.repositories.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsuarioService {
    private final UsuarioRepo usuarioRepo;

    public UsuarioService(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public List<UsuarioModel> buscarTodosUsuarios() {
        return usuarioRepo.findAll();
    }

    public UsuarioModel buscarUsuarioId(long id) {
        return usuarioRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Usuário não encontrado!"));
    }

    public UsuarioModel salvarUsuario(UsuarioModel usuario) {
        UsuarioModel usuarioSalvo = usuarioRepo.save(usuario);
        return usuarioSalvo;
    }

    public UsuarioModel editarUsuario(UsuarioModel alterarUsuario, long id) {

        var usuarioExistente = usuarioRepo.findById(id);
        if (usuarioExistente.isPresent()) {
            var user = usuarioExistente.get();
            user.setNome(alterarUsuario.getNome());
            user.setEmail(alterarUsuario.getEmail());
            user.setSenha(alterarUsuario.getSenha());
            return usuarioRepo.save(user);
        }
        return alterarUsuario;
    }

    public void apagarUsuario(long id) {
        usuarioRepo.deleteById(id);
    }


}