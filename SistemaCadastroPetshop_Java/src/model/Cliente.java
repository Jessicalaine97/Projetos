package model;

import model.e.ETipoUsuario;

import java.util.ArrayList;

public class Cliente extends Usuario{
    public Cliente() {
        tipoUsuario=ETipoUsuario.Cliente;
        ArrayList<Animal>animais=new ArrayList<Animal>();
    }

    public Cliente(String nome, String email, String senha, String cpf, ArrayList<Animal> animais, ETipoUsuario tipoUsuario) {
        super(nome, email, senha, cpf, animais, tipoUsuario);
    }
}
