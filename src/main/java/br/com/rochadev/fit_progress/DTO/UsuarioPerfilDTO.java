package br.com.rochadev.fit_progress.DTO;

import java.util.List;

public class UsuarioPerfilDTO {
    private String nome;
    private List<CategoriaDTO> categorias;

    // Construtor
    public UsuarioPerfilDTO(String nome, List<CategoriaDTO> categorias) {
        this.nome = nome;
        this.categorias = categorias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }
}
