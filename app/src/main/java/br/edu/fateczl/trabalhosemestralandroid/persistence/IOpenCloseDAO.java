package br.edu.fateczl.trabalhosemestralandroid.persistence;

import java.sql.SQLException;

public interface IOpenCloseDAO<T> {

    public T open() throws SQLException;
    public void close();
}
