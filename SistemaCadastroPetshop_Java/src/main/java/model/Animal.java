package model;

import model.e.ETipoSexo;

public class Animal {
    private int id,idade, peso;
    private String nome, cpfDono;
    private Raca raca;
    private ETipoSexo sexo;

    public Animal(int id, String nome, ETipoSexo sexo, int idade, int peso, Raca raca, String cpfDono) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.cpfDono = cpfDono;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public Animal(String nome, ETipoSexo sexo, int idade, int peso, Raca raca, String cpfDono) {
        this.nome = nome;
        this.sexo = sexo;
        this.cpfDono = cpfDono;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ETipoSexo getSexo() {
        return sexo;
    }

    public void setSexo (ETipoSexo sexo) {
        this.sexo = sexo;
    }
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpfDono() {
        return cpfDono;
    }

    public void setCpfDono(String cpfDono) {
        this.cpfDono = cpfDono;
    }
    
    @Override
    public String toString() {
        return nome; 
    }
}
