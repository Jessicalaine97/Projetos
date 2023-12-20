package br.vianna.vendas.model;

import br.vianna.vendas.model.faces.IImposto;

import java.util.ArrayList;

public class Produto {
    private String nome;
    private double preco;
    private String pesoEmbalagem;
    private Categoria categoria;
    private ArrayList<IImposto> impostos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getPesoEmbalagem() {
        return pesoEmbalagem;
    }

    public void setPesoEmbalagem(String pesoEmbalagem) {
        this.pesoEmbalagem = pesoEmbalagem;
    }

    public Produto() {
        impostos=new ArrayList<>();
    }
    //alt insert construtor
    //alt inset getter setter
    //ctrl shift - fecha todos os metdos e + abre tudo


    public Produto(String nome, double preco, String pesoEmbalagem, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.pesoEmbalagem = pesoEmbalagem;
        this.categoria = categoria;
        impostos=new ArrayList<>();
    }
    
    public double precoVenda(){
        return preco + totalImpostos() + lucro();
    }
    //seleciona - botao direito - refatorar - criar metodo

    private double lucro() {
        return preco * 0.10;
    }

    private double totalImpostos() {
        double totalPagar=0;
        for (IImposto i: impostos) {
            totalPagar +=i.valorImposto(preco);

        }
        return totalPagar;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<IImposto> getImposto() {
        return impostos;
    }

    public void addImposto(IImposto imposto) {
        this.impostos.add(imposto); //
    }
}
