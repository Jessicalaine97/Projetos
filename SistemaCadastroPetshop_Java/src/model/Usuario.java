package model;

import model.e.ETipoUsuario;

import java.util.ArrayList;

public class Usuario {
    private String nome, email, senha, cpf;
    private ArrayList<Animal> animais;

    protected ETipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String cpf, ArrayList<Animal> animais, ETipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.animais = animais;
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isSenhaStrong(){
        return senha.length() >=8 &&
                (senha.contains("@") || senha.contains("#") ||
                        senha.contains("%") || senha.contains("&") ||
                        senha.contains("-")) &&
                senha.matches("[a-z]")&&
                senha.matches("[A-Z]")&&
                senha.matches("[0-9]");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        nome = nome;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Animal> getAnimais() {
        return animais;
    }

    public void addAnimal(Animal animal) {
        this.animais.add(animal);
    }

    public ETipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(ETipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public void imprimeInformacoes(){
        System.out.println("Nome: " + this.getNome());
        System.out.println("Email: " + this.getEmail());
        System.out.println("CPF: " + this.getCpf());
    }
}
