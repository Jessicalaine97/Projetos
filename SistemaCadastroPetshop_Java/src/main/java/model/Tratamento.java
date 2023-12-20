package model;

public class Tratamento {
    private int id;
    private Consulta consulta;
    private String medicamentos;
    private String procedimentos;

    public Tratamento(int id, Consulta consulta, String medicamentos, String procedimentos) {
        this.id = id;
        this.consulta = consulta;
        this.medicamentos = medicamentos;
        this.procedimentos = procedimentos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(String procedimentos) {
        this.procedimentos = procedimentos;
    }

    @Override
    public String toString() {
        return "Tratamento{" +
                "id=" + id +
                ", consulta=" + consulta +
                ", medicamentos='" + medicamentos + '\'' +
                ", procedimentos='" + procedimentos + '\'' +
                '}';
    }
}
