package br.edu.fateczl.trabalhosemestralandroid.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import br.edu.fateczl.trabalhosemestralandroid.model.HistoricoCuidado;
import br.edu.fateczl.trabalhosemestralandroid.persistence.HistoricoCuidadoDAO;

public class HistoricoCuidadoController implements IController<HistoricoCuidado>{

    private final HistoricoCuidadoDAO HISTORICOCUIDADODAO;

    public HistoricoCuidadoController(HistoricoCuidadoDAO historicoCuidadoDAO) {
        this.HISTORICOCUIDADODAO = historicoCuidadoDAO;
    }


    @Override
    public void insert(HistoricoCuidado historicoCuidado) throws SQLException {

    }

    @Override
    public void update(HistoricoCuidado historicoCuidado) throws SQLException {

    }

    @Override
    public void delete(HistoricoCuidado historicoCuidado) throws SQLException {

    }

    @Override
    public HistoricoCuidado findOne(HistoricoCuidado historicoCuidado) throws SQLException {
        return null;
    }

    @Override
    public List<HistoricoCuidado> findALL() throws SQLException {
        return Collections.emptyList();
    }
}
