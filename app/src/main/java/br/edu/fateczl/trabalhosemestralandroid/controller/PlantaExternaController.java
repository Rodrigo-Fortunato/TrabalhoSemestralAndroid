package br.edu.fateczl.trabalhosemestralandroid.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.trabalhosemestralandroid.model.PlantaExterna;
import br.edu.fateczl.trabalhosemestralandroid.persistence.PlantaExternaDAO;

public class PlantaExternaController implements IController<PlantaExterna> {

    private final PlantaExternaDAO PLANTAEXTERNADAO;

    public PlantaExternaController(PlantaExternaDAO plantaexternadao) {
        PLANTAEXTERNADAO = plantaexternadao;
    }

    @Override
    public void insert(PlantaExterna plantaExterna) throws SQLException {
        if (PLANTAEXTERNADAO.open() == null) {
            PLANTAEXTERNADAO.open();
        }
        PLANTAEXTERNADAO.insert(plantaExterna);
        PLANTAEXTERNADAO.close();
    }

    @Override
    public void update(PlantaExterna plantaExterna) throws SQLException {
        if (PLANTAEXTERNADAO.open() == null) {
            PLANTAEXTERNADAO.open();
        }
        PLANTAEXTERNADAO.update(plantaExterna);
        PLANTAEXTERNADAO.close();
    }

    @Override
    public void delete(PlantaExterna plantaExterna) throws SQLException {
        if (PLANTAEXTERNADAO.open() == null) {
            PLANTAEXTERNADAO.open();
        }
        PLANTAEXTERNADAO.delete(plantaExterna);
        PLANTAEXTERNADAO.close();
    }

    @Override
    public PlantaExterna findOne(PlantaExterna plantaExterna) throws SQLException {
        if (PLANTAEXTERNADAO.open() == null) {
            PLANTAEXTERNADAO.open();
        }

        return PLANTAEXTERNADAO.findOne(plantaExterna);
    }

    @Override
    public List<PlantaExterna> findALL() throws SQLException {
        if (PLANTAEXTERNADAO.open() == null) {
            PLANTAEXTERNADAO.open();
        }

        return PLANTAEXTERNADAO.findAll();

    }
}
