package br.edu.fateczl.trabalhosemestralandroid.model;

public abstract class Planta {
    private int id;
    private String nome;
    private String preferenciaLuz;
    private int frequenciaRega;
    private String notas;
    private String sensibilidadePragas;


    public Planta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreferenciaLuz() {
        return preferenciaLuz;
    }

    public void setPreferenciaLuz(String preferenciaLuz) {
        this.preferenciaLuz = preferenciaLuz;
    }

    public int getFrequenciaRega() {
        return frequenciaRega;
    }

    public void setFrequenciaRega(int frequenciaRega) {
        this.frequenciaRega = frequenciaRega;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getSensibilidadePragas() {
        return sensibilidadePragas;
    }

    public void setSensibilidadePragas(String sensibilidadePragas) {
        this.sensibilidadePragas = sensibilidadePragas;
    }
}
