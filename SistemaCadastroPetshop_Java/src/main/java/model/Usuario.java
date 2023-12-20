package model;

import model.e.ETipoSexo;
import model.e.ETipoUsuario;

public class Usuario {
    private int id;
    private String nome, email, senha;
    private String cpf;
    private ETipoUsuario tipoUsuario;
    private ETipoSexo sexo;;

    public Usuario(int id, String nome, String email, String senha, String cpf, ETipoUsuario tipoUsuario, ETipoSexo sexo) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }
    
    public Usuario(String nome, String email, String senha, String cpf, ETipoUsuario tipoUsuario, ETipoSexo sexo) {
        this.nome = nome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
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

    public ETipoSexo getSexo() {
        return sexo;
    }

    public void setSexo (ETipoSexo sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public ETipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(ETipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
