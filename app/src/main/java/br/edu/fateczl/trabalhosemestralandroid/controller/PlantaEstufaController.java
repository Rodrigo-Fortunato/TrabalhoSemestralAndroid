package br.edu.fateczl.trabalhosemestralandroid.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.trabalhosemestralandroid.model.PlantaEstufa;
import br.edu.fateczl.trabalhosemestralandroid.persistence.PlantaEstufaDAO;

public class PlantaEstufaController implements IController<PlantaEstufa> {

    private final PlantaEstufaDAO PLANTAESTUFADAO;

    public PlantaEstufaController(PlantaEstufaDAO plantaestufadao) {
        PLANTAESTUFADAO = plantaestufadao;
    }

    @Override
    public void insert(PlantaEstufa plantaEstufa) throws SQLException {
        if (PLANTAESTUFADAO.open() == null) {
            PLANTAESTUFADAO.open();
        }
        PLANTAESTUFADAO.insert(plantaEstufa);
        PLANTAESTUFADAO.close();
    }

    @Override
    public void update(PlantaEstufa plantaEstufa) throws SQLException {
        if (PLANTAESTUFADAO.open() == null) {
            PLANTAESTUFADAO.open();
        }
        PLANTAESTUFADAO.update(plantaEstufa);
        PLANTAESTUFADAO.close();
    }

    @Override
    public void delete(PlantaEstufa plantaEstufa) throws SQLException {
        if (PLANTAESTUFADAO.open() == null) {
            PLANTAESTUFADAO.open();
        }
        PLANTAESTUFADAO.delete(plantaEstufa);
        PLANTAESTUFADAO.close();
    }

    @Override
    public PlantaEstufa findOne(PlantaEstufa plantaEstufa) throws SQLException {
        if (PLANTAESTUFADAO.open() == null) {
            PLANTAESTUFADAO.open();
        }
        return PLANTAESTUFADAO.findOne(plantaEstufa);

    }

    @Override
    public List<PlantaEstufa> findALL() throws SQLException {
        if (PLANTAESTUFADAO.open() == null) {
            PLANTAESTUFADAO.open();
        }
        return PLANTAESTUFADAO.findAll();

    }
}
