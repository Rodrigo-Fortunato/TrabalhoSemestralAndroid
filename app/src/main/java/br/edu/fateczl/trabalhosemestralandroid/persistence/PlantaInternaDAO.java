package br.edu.fateczl.trabalhosemestralandroid.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.fateczl.trabalhosemestralandroid.model.PlantaInterna;

public class PlantaInternaDAO implements ICRUDDAO<PlantaInterna>, IOpenCloseDAO<PlantaInternaDAO> {

    private final Context CONTEXT;
    private GenericDAO genericDAO;
    private SQLiteDatabase database;

    public PlantaInternaDAO(Context context) {
        CONTEXT = context;
    }

    @Override
    public PlantaInternaDAO open() throws SQLException {
        genericDAO = new GenericDAO(CONTEXT);
        database = genericDAO.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        genericDAO.close();
    }

    @Override
    public void insert(PlantaInterna plantaInterna) throws SQLException {
        ContentValues contentValuesPlanta = getContentValuesPlanta(plantaInterna);
        int id = (int) database.insert("planta", null, contentValuesPlanta);
        Log.i("valor do ID", String.valueOf(id));

        plantaInterna.setId(id);
        ContentValues contentValuesPlantaInterna = getContentValuesPlantaInterna(plantaInterna);
        database.insert("planta_interna", null, contentValuesPlantaInterna);

    }

    @Override
    public int update(PlantaInterna plantaInterna) throws SQLException {
        ContentValues contentValuesPlanta = getContentValuesPlanta(plantaInterna);
        database.update("planta", contentValuesPlanta, "id = " + plantaInterna.getId(), null);


        ContentValues contentValuesPlantaInterna = getContentValuesPlantaInterna(plantaInterna);
        return database.update("planta_interna", contentValuesPlantaInterna, "id_planta = " + plantaInterna.getId(), null);
    }

    @Override
    public void delete(PlantaInterna plantaInterna) throws SQLException {
        database.delete("planta_interna", "id_planta = " + plantaInterna.getId(), null);
        database.delete("planta", "id = " + plantaInterna.getId(), null);
    }

    @SuppressLint("Range")
    @Override
    public PlantaInterna findOne(PlantaInterna plantaInterna) throws SQLException {
        String sql = "SELECT p.id, p.nome, p.preferencia_luz, p.frequencia_rega, p.sensibilidade_pragas, " +
                "p.notas,pi.tamanho_vaso,pi.tempo_luz FROM planta p JOIN planta_interna pi ON p.id = pi.id_planta "+
                "WHERE p.id = " + plantaInterna.getId();

        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        PlantaInterna interna = null;
        if (!cursor.isAfterLast()) {
            interna = new PlantaInterna();

            interna.setId(cursor.getInt(cursor.getColumnIndex("id")));
            interna.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            interna.setPreferenciaLuz(cursor.getString(cursor.getColumnIndex("preferencia_luz")));
            interna.setFrequenciaRega(cursor.getInt(cursor.getColumnIndex("frequencia_rega")));
            interna.setSensibilidadePragas(cursor.getString(cursor.getColumnIndex("sensibilidade_pragas")));
            interna.setNotas(cursor.getString(cursor.getColumnIndex("notas")));
            interna.setTamanhoVaso(cursor.getInt(cursor.getColumnIndex("tamanho_vaso")));
            interna.setTempoLuz(cursor.getInt(cursor.getColumnIndex("tempo_luz")));
        }
        cursor.close();
        return interna;
    }

    @SuppressLint("Range")
    @Override
    public List<PlantaInterna> findAll() throws SQLException {
        List<PlantaInterna> plantaInternas = new ArrayList<>();

        String sql = "SELECT p.id, p.nome, p.preferencia_luz, p.frequencia_rega, p.sensibilidade_pragas, " +
                "p.notas,pi.tamanho_vaso,pi.tempo_luz FROM planta p JOIN planta_interna pi ON p.id = pi.id_planta";

        Cursor cursor = database.rawQuery(sql,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()){
            PlantaInterna interna = new PlantaInterna();
            interna.setId(cursor.getInt(cursor.getColumnIndex("id")));
            interna.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            interna.setPreferenciaLuz(cursor.getString(cursor.getColumnIndex("preferencia_luz")));
            interna.setFrequenciaRega(cursor.getInt(cursor.getColumnIndex("frequencia_rega")));
            interna.setSensibilidadePragas(cursor.getString(cursor.getColumnIndex("sensibilidade_pragas")));
            interna.setNotas(cursor.getString(cursor.getColumnIndex("notas")));
            interna.setTamanhoVaso(cursor.getInt(cursor.getColumnIndex("tamanho_vaso")));
            interna.setTempoLuz(cursor.getInt(cursor.getColumnIndex("tempo_luz")));

            plantaInternas.add(interna);
            cursor.moveToNext();
        }

        cursor.close();
        return plantaInternas;


    }


    private static ContentValues getContentValuesPlanta(PlantaInterna plantaInterna) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", plantaInterna.getNome());
        contentValues.put("preferencia_luz", plantaInterna.getPreferenciaLuz());
        contentValues.put("frequencia_rega", plantaInterna.getFrequenciaRega());
        contentValues.put("sensibilidade_pragas", plantaInterna.getSensibilidadePragas());
        contentValues.put("notas", plantaInterna.getNotas());

        return contentValues;
    }


    private static ContentValues getContentValuesPlantaInterna(PlantaInterna plantaInterna) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_planta", plantaInterna.getId());
        contentValues.put("tamanho_vaso", plantaInterna.getTamanhoVaso());
        contentValues.put("tempo_luz", plantaInterna.getTempoLuz());

        return contentValues;
    }

}
