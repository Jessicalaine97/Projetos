package br.vianna.vendas.model.impl;

import br.vianna.vendas.model.e.EEstado;
import br.vianna.vendas.model.faces.IImposto;

public class ICMS implements IImposto {

    public ICMS(EEstado estado) {
        this.estado = estado;
    }

    public EEstado getEstado() {
        return estado;
    }

    public void setEstado(EEstado estado) {
        this.estado = estado;
    }

    private EEstado estado;
    @Override
    public double valorImposto(double preco) {
        switch(estado) {
            case GO:
                return preco * 0.0023;
            case AM:
                return preco * 0.0011;
            case ES:
                return preco * 0.0021;
            case MG:
                return preco * 0.0058;
            case RJ:
                return preco * 0.0022;
            case RS:
                return preco * 0.0020;
            case SP:
                return preco * 0.003;
        }
        return 0;
    }
}
