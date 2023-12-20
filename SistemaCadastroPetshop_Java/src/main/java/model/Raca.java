package model;

import model.e.ETipoAnimal;

public class Raca {
    private int id;
    private ETipoAnimal tipoAnimal;
    private String nome;

    public Raca(int id, ETipoAnimal tipoAnimal, String nome) {
        this.id = id;
        this.tipoAnimal = tipoAnimal;
        this.nome = nome;
    }
    
    public Raca(ETipoAnimal tipoAnimal, String nome) {
        this.tipoAnimal = tipoAnimal;
        this.nome = nome;
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
    
    public ETipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(ETipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    @Override
    public String toString() {
        return "Raca [id=" + id + ", nome=" + nome + "]";
    }
}
