package br.com.rochadev.fit_progress.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FP_exercicios")
public class ExercicioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "series", nullable = false)
    private int numeroSeries;
    @Column(name = "carga", nullable = false)
    private int quantidadeCarga;

    @ManyToOne
    @JoinColumn(name = "categorias_id")
    private CategoriaModel categoria;

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

    public int getNumeroSeries() {
        return numeroSeries;
    }

    public void setNumeroSeries(int numeroSeries) {
        this.numeroSeries = numeroSeries;
    }

    public int getQuantidadeCarga() {
        return quantidadeCarga;
    }

    public void setQuantidadeCarga(int quantidadeCarga) {
        this.quantidadeCarga = quantidadeCarga;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }


}
