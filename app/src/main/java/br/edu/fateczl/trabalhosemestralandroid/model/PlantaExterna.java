package br.edu.fateczl.trabalhosemestralandroid.model;

public class PlantaExterna extends Planta{
    private String toleranciaVento;
    private String resistenciaChuva;


    public PlantaExterna() {
    }

    public String getToleranciaVento() {
        return toleranciaVento;
    }

    public void setToleranciaVento(String toleranciaVento) {
        this.toleranciaVento = toleranciaVento;
    }

    public String getResistenciaChuva() {
        return resistenciaChuva;
    }

    public void setResistenciaChuva(String resistenciaChuva) {
        this.resistenciaChuva = resistenciaChuva;
    }
}
