package com.example.dsc1.models;

public class Disciplina implements Comparable<Disciplina> {

    int id;
    String nome;
    Double nota;

    public Disciplina() {
        id = -1;
        nome = null;
        nota = 0.0;
    }

    public Disciplina(String nome, Double nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Disciplina other = (Disciplina) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int compareTo(Disciplina disciplinaCompare) {
        return this.nota.compareTo(disciplinaCompare.getNota());
    }

}