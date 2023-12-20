package br.vianna.vendas.model.vendas;

import br.vianna.vendas.model.Usuario;
import br.vianna.vendas.model.e.EtipoUsuario;

import java.time.LocalDate;

public class Cliente extends Usuario {

    public Cliente(String nome, String email, String login, String senha, LocalDate dataNascimento,
                   LocalDate dataUltimoAcesso) {
        super(nome, email, login, senha, dataNascimento, dataUltimoAcesso, EtipoUsuario.CLIENTE);
    }
}
