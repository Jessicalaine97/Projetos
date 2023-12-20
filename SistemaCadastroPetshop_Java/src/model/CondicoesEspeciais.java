package model;

public class CondicoesEspeciais {
    private String alergias, problemasSaude;

    public CondicoesEspeciais() {
    }

    public CondicoesEspeciais(String alergias, String problemasSaude) {
        this.alergias = alergias;
        this.problemasSaude = problemasSaude;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getProblemasSaude() {
        return problemasSaude;
    }

    public void setProblemasSaude(String problemasSaude) {
        this.problemasSaude = problemasSaude;
    }
}
