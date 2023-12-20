package br.vianna.vendas.model.impl.cupom;

import br.vianna.vendas.model.faces.ICupom;

public class CupomPercentual implements ICupom {
    private double percentualCupom;

    public CupomPercentual(double percentualCupom) {
        this.percentualCupom = percentualCupom;
    }

    @Override
    public double desconto(double precoTotal){
        return precoTotal*(precoTotal/100);
    }

    public double getPercentualCupom() {
        return percentualCupom;
    }

    public void setPercentualCupom(double percentualCupom) {
        this.percentualCupom = percentualCupom;
    }
}
