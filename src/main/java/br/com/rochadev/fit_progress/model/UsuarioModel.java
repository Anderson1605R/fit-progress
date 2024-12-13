package br.com.rochadev.fit_progress.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FP_usuarios")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuarios_id")
    private long id;
    @Column(name = "nome", nullable = false)
    @NotBlank
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @Email
    private String email;
    @Column(name = "senha", nullable = false)
    @NotBlank
    private String senha;
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("usuario")
    private List<CategoriaModel> categorias = new ArrayList<>();

    public List<CategoriaModel> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaModel> categorias) {
        this.categorias = categorias;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public void adicionarCategoria(CategoriaModel categoria) {
        categorias.add(categoria);
        categoria.setUsuario(this);
    }


}
