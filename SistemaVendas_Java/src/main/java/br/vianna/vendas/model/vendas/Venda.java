package br.vianna.vendas.model.vendas;

import br.vianna.vendas.model.Produto;
import br.vianna.vendas.model.Usuario;
import br.vianna.vendas.model.faces.ICupom;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Venda {
    private LocalDateTime dataVenda;
    //private ArrayList<Produto> produtos;
    private Cliente comprador;
    private ArrayList<ItemVenda> produtos;
    private ICupom cupom;

    public Venda() {
        this(LocalDateTime.now(), null);
    }

    public Venda(LocalDateTime dataVenda, Cliente comprador) {
        this.dataVenda = dataVenda;
        this.comprador = comprador;
        this.produtos=new ArrayList<>();
    }
    public double totalVenda(){
        double precoTotal=0;
        for(ItemVenda iv: produtos){
            precoTotal += iv.getPrecoVenda() * iv.getQuantidade();
        }
        return precoTotal - ((cupom!=null)?cupom.desconto(precoTotal):0);
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Cliente getComprador() {
        return comprador;
    }

    public void setComprador(Cliente comprador) {
        this.comprador = comprador;
    }

    public ArrayList<ItemVenda> getProdutos() {
        return produtos;
    }

    public void addProdutos(ItemVenda item) {
        this.produtos.add(item);
    }

    public ICupom getCupom() {
        return cupom;
    }

    public void setCupom(ICupom cupom) {
        this.cupom = cupom;
    }
}

