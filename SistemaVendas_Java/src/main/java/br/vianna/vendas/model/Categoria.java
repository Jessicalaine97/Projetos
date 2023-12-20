package br.vianna.vendas.model;

public class Categoria {
    private int id;
    private String nome;
    private boolean temIsencao;

    public Categoria() {
    }

    public Categoria(int id, String nome, boolean temIsencao) {
        this.id = id;
        this.nome = nome;
        this.temIsencao = temIsencao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean isTemIsencao() {
        return temIsencao;
    }

    public void setTemIsencao(boolean temIsencao) {
        this.temIsencao = temIsencao;
    }
}
