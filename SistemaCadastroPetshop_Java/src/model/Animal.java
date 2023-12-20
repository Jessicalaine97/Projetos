package model;

import model.e.ETipoAnimal;

public class Animal {
    private String nome, sexo;
    private int idade, peso;
    private ETipoAnimal tipoAnimal;

    public Animal() {
    }

    public Animal(String nome, String sexo, int idade, int peso, ETipoAnimal tipoAnimal) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.peso = peso;
        this.tipoAnimal = tipoAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
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

    public ETipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(ETipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }
    public void imprimeInformacoes(){
        System.out.println("Nome: " + this.nome);
        System.out.println("Sexo: " + this.sexo);
        System.out.println("Idade: " + this.idade);
        System.out.println("Peso: " + this.peso);
    }
}
