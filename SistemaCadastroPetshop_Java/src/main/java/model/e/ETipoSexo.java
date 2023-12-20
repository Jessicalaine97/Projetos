package model.e;

import java.util.ArrayList;
import java.util.List;

public enum ETipoSexo {
    F, M;

    public static List<String> obterOpcoesSexo() {
        List<String> opcoes = new ArrayList<>();
        for (ETipoSexo tipo : ETipoSexo.values()) {
            opcoes.add(tipo.name());
        }
        return opcoes;
    }
}
