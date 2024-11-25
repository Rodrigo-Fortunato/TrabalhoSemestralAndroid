package br.edu.fateczl.trabalhosemestralandroid.model;

import java.time.LocalDate;

public class HistoricoCuidado {

    private int id;
    private LocalDate dataCuidado;
    private String tipoCuidado;
    private String observacao;
    private Planta planta;

    public HistoricoCuidado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataCuidado() {
        return dataCuidado;
    }

    public void setDataCuidado(LocalDate dataCuidado) {
        this.dataCuidado = dataCuidado;
    }

    public String getTipoCuidado() {
        return tipoCuidado;
    }

    public void setTipoCuidado(String tipoCuidado) {
        this.tipoCuidado = tipoCuidado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }
}
