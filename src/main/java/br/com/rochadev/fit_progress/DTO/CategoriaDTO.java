package br.com.rochadev.fit_progress.DTO;


import java.util.List;

public class CategoriaDTO {
        private String nome;
        private List<ExercicioDTO> exercicios;

        // Construtor
        public CategoriaDTO(String nome, List<ExercicioDTO> exercicios) {
            this.nome = nome;
            this.exercicios = exercicios;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public List<ExercicioDTO> getExercicios() {
            return exercicios;
        }

        public void setExercicios(List<ExercicioDTO> exercicios) {
            this.exercicios = exercicios;
        }
        // Getters e setters
    }

