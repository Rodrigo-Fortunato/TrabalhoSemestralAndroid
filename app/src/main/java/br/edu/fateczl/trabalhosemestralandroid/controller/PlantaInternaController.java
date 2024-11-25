package br.edu.fateczl.trabalhosemestralandroid.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.trabalhosemestralandroid.model.PlantaInterna;
import br.edu.fateczl.trabalhosemestralandroid.persistence.PlantaInternaDAO;

public class PlantaInternaController implements IController<PlantaInterna>{

    private final PlantaInternaDAO PLANTAINTERNADAO;

    public PlantaInternaController(PlantaInternaDAO plantainternadao) {
        PLANTAINTERNADAO = plantainternadao;
    }

    @Override
    public void insert(PlantaInterna plantaInterna) throws SQLException {
        if (PLANTAINTERNADAO.open() == null){
            PLANTAINTERNADAO.open();
        }
        PLANTAINTERNADAO.insert(plantaInterna);
        PLANTAINTERNADAO.close();
    }

    @Override
    public void update(PlantaInterna plantaInterna) throws SQLException {
        if (PLANTAINTERNADAO.open() == null){
            PLANTAINTERNADAO.open();
        }
        PLANTAINTERNADAO.update(plantaInterna);
        PLANTAINTERNADAO.close();
    }

    @Override
    public void delete(PlantaInterna plantaInterna) throws SQLException {
        if (PLANTAINTERNADAO.open() == null){
            PLANTAINTERNADAO.open();
        }
        PLANTAINTERNADAO.delete(plantaInterna);
        PLANTAINTERNADAO.close();
    }

    @Override
    public PlantaInterna findOne(PlantaInterna plantaInterna) throws SQLException {
        if (PLANTAINTERNADAO.open() == null){
            PLANTAINTERNADAO.open();
        }

        return PLANTAINTERNADAO.findOne(plantaInterna);
    }

    @Override
    public List<PlantaInterna> findALL() throws SQLException {
        if (PLANTAINTERNADAO.open() == null){
            PLANTAINTERNADAO.open();
        }

        return PLANTAINTERNADAO.findAll();
    }
}
