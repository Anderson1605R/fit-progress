package br.com.rochadev.fit_progress.DTO;

public class ExercicioDTO {
    private String nome;
    private int numeroSeries;
    private int quantidadeCarga;

    // Construtor
    public ExercicioDTO(String nome, int numeroSeries, int quantidadeCarga) {
        this.nome = nome;
        this.numeroSeries = numeroSeries;
        this.quantidadeCarga = quantidadeCarga;
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
}
