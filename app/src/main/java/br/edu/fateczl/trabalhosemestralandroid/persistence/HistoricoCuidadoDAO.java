package br.edu.fateczl.trabalhosemestralandroid.persistence;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import br.edu.fateczl.trabalhosemestralandroid.model.HistoricoCuidado;

public class HistoricoCuidadoDAO implements ICRUDDAO<HistoricoCuidado>,IOpenCloseDAO<HistoricoCuidadoDAO>{
    @Override
    public HistoricoCuidadoDAO open() throws SQLException {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void insert(HistoricoCuidado historicoCuidado) throws SQLException {

    }

    @Override
    public int update(HistoricoCuidado historicoCuidado) throws SQLException {
        return 0;
    }

    @Override
    public void delete(HistoricoCuidado historicoCuidado) throws SQLException {

    }

    @Override
    public HistoricoCuidado findOne(HistoricoCuidado historicoCuidado) throws SQLException {
        return null;
    }

    @Override
    public List<HistoricoCuidado> findAll() throws SQLException {
        return Collections.emptyList();
    }
}
