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

import br.edu.fateczl.trabalhosemestralandroid.model.PlantaEstufa;

public class PlantaEstufaDAO implements ICRUDDAO<PlantaEstufa>, IOpenCloseDAO<PlantaEstufaDAO> {
    private final Context CONTEXT;
    private GenericDAO genericDAO;
    private SQLiteDatabase database;

    public PlantaEstufaDAO(Context context) {
        CONTEXT = context;
    }

    @Override
    public PlantaEstufaDAO open() throws SQLException {
        genericDAO = new GenericDAO(CONTEXT);
        database = genericDAO.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        genericDAO.close();

    }

    @Override
    public void insert(PlantaEstufa plantaEstufa) throws SQLException {
        ContentValues contentValuesPlanta = getContentValuesPlanta(plantaEstufa);
        int id = (int) database.insert("planta", null, contentValuesPlanta);
        Log.i("valor do ID", String.valueOf(id));

        plantaEstufa.setId(id);
        ContentValues contentValuesPlantaEstufa = getContentValuesPlantaEstufa(plantaEstufa);
        database.insert("planta_estufa", null, contentValuesPlantaEstufa);
    }

    @Override
    public int update(PlantaEstufa plantaEstufa) throws SQLException {
        ContentValues contentValuesPlanta = getContentValuesPlanta(plantaEstufa);
        database.update("planta", contentValuesPlanta, "id = " + plantaEstufa.getId(), null);


        ContentValues contentValuesPlantaEstufa = getContentValuesPlantaEstufa(plantaEstufa);
        return database.update("planta_estufa", contentValuesPlantaEstufa, "id_planta = " + plantaEstufa.getId(), null);
    }

    @Override
    public void delete(PlantaEstufa plantaEstufa) throws SQLException {
        database.delete("planta_estufa", "id_planta = " + plantaEstufa.getId(), null);
        database.delete("planta", "id = " + plantaEstufa.getId(), null);
    }

    @SuppressLint("Range")
    @Override
    public PlantaEstufa findOne(PlantaEstufa plantaEstufa) throws SQLException {
        String sql = "SELECT p.id, p.nome, p.preferencia_luz, p.frequencia_rega, p.sensibilidade_pragas," +
                "p.notas,pe.temperatura_ideal,pe.resist_alteracao_ambiental,pe.humidade_ideal"+
                " FROM planta p JOIN planta_estufa pe ON p.id = pe.id_planta "+
                "WHERE p.id = " + plantaEstufa.getId();

        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        PlantaEstufa estufa = null;
        if (!cursor.isAfterLast()) {
            estufa = new PlantaEstufa();

            estufa.setId(cursor.getInt(cursor.getColumnIndex("id")));
            estufa.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            estufa.setPreferenciaLuz(cursor.getString(cursor.getColumnIndex("preferencia_luz")));
            estufa.setFrequenciaRega(cursor.getInt(cursor.getColumnIndex("frequencia_rega")));
            estufa.setSensibilidadePragas(cursor.getString(cursor.getColumnIndex("sensibilidade_pragas")));
            estufa.setNotas(cursor.getString(cursor.getColumnIndex("notas")));
            estufa.setTemperaturaIdeal(cursor.getInt(cursor.getColumnIndex("temperatura_ideal")));
            estufa.setResistAlteracoesAmbientais(cursor.getString(cursor.getColumnIndex("resist_alteracao_ambiental")));
            estufa.setHumidadeIdeal(cursor.getInt(cursor.getColumnIndex("humidade_ideal")));
        }
        cursor.close();
        return estufa;
    }

    @SuppressLint("Range")
    @Override
    public List<PlantaEstufa> findAll() throws SQLException {

        List<PlantaEstufa> plantasEstufa = new ArrayList<>();
        String sql = "SELECT p.id, p.nome, p.preferencia_luz, p.frequencia_rega, p.sensibilidade_pragas," +
                "p.notas,pe.temperatura_ideal,pe.resist_alteracao_ambiental,pe.humidade_ideal"+
                " FROM planta p JOIN planta_estufa pe ON p.id = pe.id_planta ";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        PlantaEstufa estufa = null;
        while (!cursor.isAfterLast()) {
            estufa = new PlantaEstufa();

            estufa.setId(cursor.getInt(cursor.getColumnIndex("id")));
            estufa.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            estufa.setPreferenciaLuz(cursor.getString(cursor.getColumnIndex("preferencia_luz")));
            estufa.setFrequenciaRega(cursor.getInt(cursor.getColumnIndex("frequencia_rega")));
            estufa.setSensibilidadePragas(cursor.getString(cursor.getColumnIndex("sensibilidade_pragas")));
            estufa.setNotas(cursor.getString(cursor.getColumnIndex("notas")));
            estufa.setTemperaturaIdeal(cursor.getInt(cursor.getColumnIndex("temperatura_ideal")));
            estufa.setResistAlteracoesAmbientais(cursor.getString(cursor.getColumnIndex("resist_alteracao_ambiental")));
            estufa.setHumidadeIdeal(cursor.getInt(cursor.getColumnIndex("humidade_ideal")));
            plantasEstufa.add(estufa);
            cursor.moveToNext();
        }
        cursor.close();
        return plantasEstufa;
    }

    private static ContentValues getContentValuesPlanta(PlantaEstufa plantaEstufa) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", plantaEstufa.getNome());
        contentValues.put("preferencia_luz", plantaEstufa.getPreferenciaLuz());
        contentValues.put("frequencia_rega", plantaEstufa.getFrequenciaRega());
        contentValues.put("sensibilidade_pragas", plantaEstufa.getSensibilidadePragas());
        contentValues.put("notas", plantaEstufa.getNotas());

        return contentValues;
    }


    private static ContentValues getContentValuesPlantaEstufa(PlantaEstufa plantaEstufa) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_planta", plantaEstufa.getId());
        contentValues.put("temperatura_ideal", plantaEstufa.getTemperaturaIdeal());
        contentValues.put("resist_alteracao_ambiental", plantaEstufa.getResistAlteracoesAmbientais());
        contentValues.put("humidade_ideal", plantaEstufa.getHumidadeIdeal());

        return contentValues;
    }
}
