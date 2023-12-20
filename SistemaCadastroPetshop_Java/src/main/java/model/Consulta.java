package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Consulta {
    private int id;
    private Date data;
    private String exames;
    private Animal animal;

    public Consulta(int id, Date data, String exames, Animal animal) {
        this.id = id;
        this.data = data;
        this.exames = exames;
        this.animal = animal;
    }

    public Consulta(Date data, String exames, Animal animal) {
        this.data = data;
        this.exames = exames;
        this.animal = animal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getExames() {
        return exames;
    }

    public void setExames(String exames) {
        this.exames = exames;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data) + " - " + animal.toString(); 
    }
}
