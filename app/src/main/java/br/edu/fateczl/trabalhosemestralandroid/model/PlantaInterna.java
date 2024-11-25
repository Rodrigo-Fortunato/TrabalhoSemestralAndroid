package br.edu.fateczl.trabalhosemestralandroid.model;

public class PlantaInterna extends Planta{
    private int tamanhoVaso;
    private int tempoLuz;


    public PlantaInterna() {
    }

    public int getTamanhoVaso() {
        return tamanhoVaso;
    }

    public void setTamanhoVaso(int tamanhoVaso) {
        this.tamanhoVaso = tamanhoVaso;
    }

    public int getTempoLuz() {
        return tempoLuz;
    }

    public void setTempoLuz(int tempoLuz) {
        this.tempoLuz = tempoLuz;
    }
}
