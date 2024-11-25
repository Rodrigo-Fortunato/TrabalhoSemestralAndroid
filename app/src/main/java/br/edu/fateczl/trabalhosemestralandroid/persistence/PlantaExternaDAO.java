package br.edu.fateczl.trabalhosemestralandroid.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.trabalhosemestralandroid.model.PlantaExterna;

public class PlantaExternaDAO implements IOpenCloseDAO<PlantaExternaDAO>,ICRUDDAO<PlantaExterna> {
    private final Context CONTEXT;
    private GenericDAO genericDAO;
    private SQLiteDatabase database;

    public PlantaExternaDAO(Context context) {
        CONTEXT = context;
    }

    @Override
    public PlantaExternaDAO open() throws SQLException {
        genericDAO = new GenericDAO(CONTEXT);
        database = genericDAO.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        genericDAO.close();
    }

    @Override
    public void insert(PlantaExterna plantaExterna) throws SQLException {
        ContentValues contentValuesPlanta = getContentValuesPlanta(plantaExterna);
        int id = (int) database.insert("planta", null, contentValuesPlanta);
        Log.i("valor do ID", String.valueOf(id));

        plantaExterna.setId(id);
        ContentValues contentValuesPlantaExterna = getContentValuesPlantaExterna(plantaExterna);
        database.insert("planta_externa", null, contentValuesPlantaExterna);
    }

    @Override
    public int update(PlantaExterna plantaExterna) throws SQLException {
        ContentValues contentValuesPlanta = getContentValuesPlanta(plantaExterna);
        database.update("planta", contentValuesPlanta, "id = " + plantaExterna.getId(), null);


        ContentValues contentValuesPlantaExterna = getContentValuesPlantaExterna(plantaExterna);
        return database.update("planta_externa", contentValuesPlantaExterna, "id_planta = " + plantaExterna.getId(), null);
    }

    @Override
    public void delete(PlantaExterna plantaExterna) throws SQLException {
        database.delete("planta_externa", "id_planta = " + plantaExterna.getId(), null);
        database.delete("planta", "id = " + plantaExterna.getId(), null);
    }

    @SuppressLint("Range")
    @Override
    public PlantaExterna findOne(PlantaExterna plantaExterna) throws SQLException {
        String sql = "SELECT p.id, p.nome, p.preferencia_luz, p.frequencia_rega, p.sensibilidade_pragas," +
                "p.notas,pex.resistencia_chuva,pex.tolerancia_vento FROM planta p JOIN planta_externa pex ON p.id = pex.id_planta "+
                "WHERE p.id = " + plantaExterna.getId();

        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        PlantaExterna externa = null;
        if (!cursor.isAfterLast()) {
            externa = new PlantaExterna();

            externa.setId(cursor.getInt(cursor.getColumnIndex("id")));
            externa.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            externa.setPreferenciaLuz(cursor.getString(cursor.getColumnIndex("preferencia_luz")));
            externa.setFrequenciaRega(cursor.getInt(cursor.getColumnIndex("frequencia_rega")));
            externa.setSensibilidadePragas(cursor.getString(cursor.getColumnIndex("sensibilidade_pragas")));
            externa.setNotas(cursor.getString(cursor.getColumnIndex("notas")));
            externa.setResistenciaChuva(cursor.getString(cursor.getColumnIndex("resistencia_chuva")));
            externa.setToleranciaVento(cursor.getString(cursor.getColumnIndex("tolerancia_vento")));
        }
        cursor.close();
        return externa;
    }

    @SuppressLint("Range")
    @Override
    public List<PlantaExterna> findAll() throws SQLException {
        List<PlantaExterna> plantasExterna = new ArrayList<>();
        String sql = "SELECT p.id, p.nome, p.preferencia_luz, p.frequencia_rega, p.sensibilidade_pragas," +
                "p.notas,pex.resistencia_chuva,pex.tolerancia_vento FROM planta p JOIN planta_externa pex ON p.id = pex.id_planta";

        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        PlantaExterna externa = null;
        while (!cursor.isAfterLast()) {
            externa = new PlantaExterna();

            externa.setId(cursor.getInt(cursor.getColumnIndex("id")));
            externa.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            externa.setPreferenciaLuz(cursor.getString(cursor.getColumnIndex("preferencia_luz")));
            externa.setFrequenciaRega(cursor.getInt(cursor.getColumnIndex("frequencia_rega")));
            externa.setSensibilidadePragas(cursor.getString(cursor.getColumnIndex("sensibilidade_pragas")));
            externa.setNotas(cursor.getString(cursor.getColumnIndex("notas")));
            externa.setResistenciaChuva(cursor.getString(cursor.getColumnIndex("resistencia_chuva")));
            externa.setToleranciaVento(cursor.getString(cursor.getColumnIndex("tolerancia_vento")));

            plantasExterna.add(externa);
            cursor.moveToNext();
        }
        cursor.close();
        return plantasExterna;
    }
    private static ContentValues getContentValuesPlanta(PlantaExterna plantaExterna) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", plantaExterna.getNome());
        contentValues.put("preferencia_luz", plantaExterna.getPreferenciaLuz());
        contentValues.put("frequencia_rega", plantaExterna.getFrequenciaRega());
        contentValues.put("sensibilidade_pragas", plantaExterna.getSensibilidadePragas());
        contentValues.put("notas", plantaExterna.getNotas());

        return contentValues;
    }


    private static ContentValues getContentValuesPlantaExterna(PlantaExterna plantaExterna) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_planta", plantaExterna.getId());
        contentValues.put("resistencia_chuva", plantaExterna.getResistenciaChuva());
        contentValues.put("tolerancia_vento", plantaExterna.getToleranciaVento());

        return contentValues;
    }
}
