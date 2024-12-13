package br.com.rochadev.fit_progress.DTO;

import java.util.List;

public class UsuarioDTO {
    private long id;
    private String nome;
    private String email;


    // Construtores
    public UsuarioDTO(long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;

    }


    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
