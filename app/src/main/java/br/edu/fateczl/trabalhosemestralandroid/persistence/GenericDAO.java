package br.edu.fateczl.trabalhosemestralandroid.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class GenericDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "PLANTCOLLECTION.DB";
    private static final int DATABASE_VER = 1;
    private static final String CREATE_TABLE_PLANTA  ="CREATE TABLE planta("+
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
            "nome VARCHAR(100) NOT NULL, "+
            "preferencia_luz VARCHAR(15) NOT NULL,"+
            "frequencia_rega INT NOT NULL,"+
            "sensibilidade_pragas VARCHAR(5) NOT NULL,"+
            "notas VARCHAR(200))";
    private static final String CREATE_TABLE_PLANTAINTERNA  ="CREATE TABLE planta_interna("+
            "id_planta INTEGER NOT NULL PRIMARY KEY, "+
            "tamanho_vaso INT NOT NULL, "+
            "tempo_luz INT NOT NULL,"+
            "FOREIGN KEY (id_planta) REFERENCES planta(id))";
    private static final String CREATE_TABLE_PLANTAEXTERNA  ="CREATE TABLE planta_externa("+
            "id_planta INTEGER NOT NULL PRIMARY KEY, "+
            "tolerancia_vento VARCHAR(15) NOT NULL, "+
            "resistencia_chuva INT NOT NULL,"+
            "FOREIGN KEY (id_planta) REFERENCES planta(id))";
    private static final String CREATE_TABLE_PLANTAESTUFA  ="CREATE TABLE planta_estufa("+
            "id_planta INTEGER NOT NULL PRIMARY KEY, "+
            "temperatura_ideal DECIMAL(4,2) NOT NULL, "+
            "resist_alteracao_ambiental VARCHAR(5) NOT NULL,"+
            "humidade_ideal int NOT NULL,"+
            "FOREIGN KEY (id_planta) REFERENCES planta(id))";
    private static final String CREATE_TABLE_HISTORICOCUIDADOS  ="CREATE TABLE historico_cuidados("+
            "id INTEGER NOT NULL PRIMARY KEY, "+
            "id_planta INT NOT NULL, "+
            "tipo_cuidado VARCHAR(20) NOT NULL, "+
            "data_cuidado VARCHAR(10) NOT NULL,"+
            "observacao VARCHAR(200) NOT NULL,"+
            "FOREIGN KEY (id_planta) REFERENCES planta(id))";

    public GenericDAO(Context context) {
        super(context, DATABASE, null, DATABASE_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PLANTA);
        sqLiteDatabase.execSQL(CREATE_TABLE_PLANTAINTERNA);
        sqLiteDatabase.execSQL(CREATE_TABLE_PLANTAEXTERNA);
        sqLiteDatabase.execSQL(CREATE_TABLE_PLANTAESTUFA);
        sqLiteDatabase.execSQL(CREATE_TABLE_HISTORICOCUIDADOS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS planta ");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS planta_interna ");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS planta_externa ");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS planta_estufa ");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS historico_cuidados");

            onCreate(sqLiteDatabase);
        }

    }
}
