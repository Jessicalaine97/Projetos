package model.e;

import java.util.ArrayList;
import java.util.List;

public enum ETipoUsuario {
    Cliente, Funcionario;

    public static List<String> obterOpcoesTipoUsuario() {
        List<String> opcoes = new ArrayList<>();
        for (ETipoUsuario tipo : ETipoUsuario.values()) {
            opcoes.add(tipo.name());
        }
        return opcoes;
    }
}
