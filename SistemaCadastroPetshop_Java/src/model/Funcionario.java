package model;

import model.e.ETipoUsuario;

import java.util.ArrayList;

public class Funcionario extends Usuario {
    public Funcionario() {
        tipoUsuario=ETipoUsuario.Funcionario;
    }

    public Funcionario(String nome, String email, String senha, String cpf, ArrayList<Animal> animais, ETipoUsuario tipoUsuario) {
        super(nome, email, senha, cpf, animais, tipoUsuario);
    }
}
