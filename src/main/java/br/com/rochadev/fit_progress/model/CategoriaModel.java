package br.com.rochadev.fit_progress.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FP_categorias")
public class CategoriaModel {
    public void setExercicio(List<ExercicioModel> exercicio) {
        this.exercicio = exercicio;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorias_id")
    private long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private UsuarioModel usuario;
    @JsonManagedReference

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("categoria")
    private List<ExercicioModel> exercicio = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public List<ExercicioModel> getExercicio() {
        return exercicio;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarExercicio(ExercicioModel exercicios) {
        exercicio.add(exercicios);
        exercicios.setCategoria(this);


    }

}
