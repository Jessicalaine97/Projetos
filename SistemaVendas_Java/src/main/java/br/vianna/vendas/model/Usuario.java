package br.vianna.vendas.model;

import br.vianna.vendas.model.e.EtipoUsuario;

import java.time.LocalDate;
import java.time.Period;

public class Usuario {
    private int id;
    private String nome, email, login, senha;
    private LocalDate dataNascimento;
    private LocalDate dataUltimoAcesso;
    protected EtipoUsuario perfil;

    public Usuario() {
    }

    public Usuario(String nome, String email, String login, String senha, LocalDate dataNascimento, LocalDate dataUltimoAcesso, EtipoUsuario perfil) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.dataUltimoAcesso = dataUltimoAcesso;
        this.perfil = perfil;
    }
    public boolean isSenhaStrong(){
        return senha.length()>=8 &&
                (senha.contains("@") || senha.contains("#") || senha.contains("%") || senha.contains("!") ||
                senha.contains("-")) && senha.matches("a-z") && senha.matches("A-Z") &&
                senha.matches("0-9");
    }
    public int idade(){
        LocalDate hoje = LocalDate.now();
        return Period.between(dataNascimento, hoje).getYears();
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }

    public void setDataUltimoAcesso(LocalDate dataUltimoAcesso) {
        this.dataUltimoAcesso = dataUltimoAcesso;
    }

    public EtipoUsuario getPerfil() {
        return perfil;
    }

    //public void setPerfil(EtipoUsuario perfil) {
       // this.perfil = perfil;
    //}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
