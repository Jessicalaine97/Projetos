package model;

public class Medicamento {
    private String nome, dosagem;
    private int intervaloHoras, diasTratamento;

    public Medicamento() {
    }

    public Medicamento(String nome, String dosagem, int intervaloHoras, int diasTratamento) {
        this.nome = nome;
        this.dosagem = dosagem;
        this.intervaloHoras = intervaloHoras;
        this.diasTratamento = diasTratamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public int getIntervaloHoras() {
        return intervaloHoras;
    }

    public void setIntervaloHoras(int intervaloHoras) {
        this.intervaloHoras = intervaloHoras;
    }

    public int getDiasTratamento() {
        return diasTratamento;
    }

    public void setDiasTratamento(int diasTratamento) {
        this.diasTratamento = diasTratamento;
    }
}
