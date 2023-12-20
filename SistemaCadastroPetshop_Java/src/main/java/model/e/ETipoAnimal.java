package model.e;

import java.util.ArrayList;
import java.util.List;

public enum ETipoAnimal {
    Cachorro, Gato;

    public static List<String> obterOpcoesTipoAnimal() {
        List<String> opcoes = new ArrayList<>();
        for (ETipoAnimal tipo : ETipoAnimal.values()) {
            opcoes.add(tipo.name());
        }
        return opcoes;
    }
}
