package br.com.rochadev.fit_progress.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FP_categorias")
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorias_id")
    private long id;
    @Column(name = "nome", nullable = false)
    private String nome;

        @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
        private List<ExercicioModel> exercicio =new ArrayList<>();
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
