package br.vianna.vendas.model.impl.cupom;

import br.vianna.vendas.model.faces.ICupom;

public class CupomFixo implements ICupom {
    private double valorDesconto;

    public CupomFixo(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    @Override
    public double desconto(double precoTotal){
        return(valorDesconto < precoTotal)?valorDesconto:0;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
}
