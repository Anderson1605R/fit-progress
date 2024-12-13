package br.com.rochadev.fit_progress.services;

import br.com.rochadev.fit_progress.DTO.CategoriaDTO;
import br.com.rochadev.fit_progress.DTO.ExercicioDTO;
import br.com.rochadev.fit_progress.DTO.UsuarioPerfilDTO;
import br.com.rochadev.fit_progress.model.ExercicioModel;
import br.com.rochadev.fit_progress.model.UsuarioModel;
import br.com.rochadev.fit_progress.repositories.UsuarioRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Optional<UsuarioModel> buscarUsuarioPorEmail(String email){
        return usuarioRepo.findByEmail(email);
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

    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
    public UsuarioPerfilDTO getUsuarioPerfil(Long usuarioId) {
        // Obtém os resultados da consulta
        List<Object[]> resultados = usuarioRepo.findUsuarioCategoriasExercicios(usuarioId);

        // Mapeia os resultados para DTOs
        String usuarioNome = null;
        Map<String, CategoriaDTO> categoriasMap = new HashMap<>();

        for (Object[] row : resultados) {
            // Extrai dados do Object[]
            if (usuarioNome == null) {
                usuarioNome = (String) row[0];
            }

            String categoriaNome = (String) row[1];
            ExercicioModel exercicio = (ExercicioModel) row[2];

            // Cria ou obtém a categoria no mapa
            CategoriaDTO categoriaDTO = categoriasMap.computeIfAbsent(categoriaNome,
                    nome -> new CategoriaDTO(nome, new ArrayList<>())
            );

            // Adiciona o exercício, se existir
            if (exercicio != null) {
                ExercicioDTO exercicioDTO = new ExercicioDTO(
                        exercicio.getNome(),
                        exercicio.getNumeroSeries(),
                        exercicio.getQuantidadeCarga()
                );
                categoriaDTO.getExercicios().add(exercicioDTO);
            }
        }

        // Constrói o DTO final do usuário
        return new UsuarioPerfilDTO(usuarioNome, new ArrayList<>(categoriasMap.values()));
    }



}