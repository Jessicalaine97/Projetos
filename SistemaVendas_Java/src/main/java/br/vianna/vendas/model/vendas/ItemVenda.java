package br.vianna.vendas.model.vendas;

import br.vianna.vendas.model.Produto;

public class ItemVenda {
    private Produto produtos;
    private int quantidade;
    private double precoVenda;

    public ItemVenda() {
    }

    public ItemVenda(Produto produtos, int quantidade, double precoVenda) {
        this.produtos = produtos;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public Produto getProdutos() {
        return produtos;
    }

    public void setProdutos(Produto produtos) {
        this.produtos = produtos;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
}
