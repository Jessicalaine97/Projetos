package model;

import model.e.ETipoAnimal;

import java.time.LocalDateTime;

public class Consulta {
    private LocalDateTime data;
    private String exames;

    public Consulta() {
    }

    public Consulta(LocalDateTime data, String exames) {
        this.data = data;
        this.exames = exames;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getExames() {
        return exames;
    }

    public void setExames(String exames) {
        this.exames = exames;
    }
}
