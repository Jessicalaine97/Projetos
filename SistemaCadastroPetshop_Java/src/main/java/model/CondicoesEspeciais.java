package model;

public class CondicoesEspeciais {
    private int id;
    private Raca raca;
    private String alergias;
    private String problemasDeSaude;

    public CondicoesEspeciais(int id, Raca raca, String alergias, String problemasDeSaude) {
        this.id = id;
        this.raca = raca;
        this.alergias = alergias;
        this.problemasDeSaude = problemasDeSaude;
    }

    public CondicoesEspeciais(Raca raca, String alergias, String problemasDeSaude) {
        this.raca = raca;
        this.alergias = alergias;
        this.problemasDeSaude = problemasDeSaude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getProblemasDeSaude() {
        return problemasDeSaude;
    }

    public void setProblemasDeSaude(String problemasDeSaude) {
        this.problemasDeSaude = problemasDeSaude;
    }
}
