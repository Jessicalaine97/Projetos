package model;

import java.util.ArrayList;

public class Tratamento {
    ArrayList<Medicamento> medicamentos;
    private String procedimentosMedicos;

    public Tratamento() {
    }

    public Tratamento(ArrayList<Medicamento> medicamentos, String procedimentosMedicos) {
        this.medicamentos = medicamentos;
        this.procedimentosMedicos = procedimentosMedicos;
    }

    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getProcedimentosMedicos() {
        return procedimentosMedicos;
    }

    public void setProcedimentosMedicos(String procedimentosMedicos) {
        this.procedimentosMedicos = procedimentosMedicos;
    }
}
