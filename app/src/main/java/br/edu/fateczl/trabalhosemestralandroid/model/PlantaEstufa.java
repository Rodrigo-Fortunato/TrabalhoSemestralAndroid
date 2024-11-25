package br.edu.fateczl.trabalhosemestralandroid.model;

public class PlantaEstufa extends Planta{

    private float temperaturaIdeal;
    private String resistAlteracoesAmbientais;
    private int humidadeIdeal;

    public PlantaEstufa() {
    }

    public float getTemperaturaIdeal() {
        return temperaturaIdeal;
    }

    public void setTemperaturaIdeal(float temperaturaIdeal) {
        this.temperaturaIdeal = temperaturaIdeal;
    }

    public String getResistAlteracoesAmbientais() {
        return resistAlteracoesAmbientais;
    }

    public void setResistAlteracoesAmbientais(String resistAlteracoesAmbientais) {
        this.resistAlteracoesAmbientais = resistAlteracoesAmbientais;
    }

    public int getHumidadeIdeal() {
        return humidadeIdeal;
    }

    public void setHumidadeIdeal(int humidadeIdeal) {
        this.humidadeIdeal = humidadeIdeal;
    }
}
