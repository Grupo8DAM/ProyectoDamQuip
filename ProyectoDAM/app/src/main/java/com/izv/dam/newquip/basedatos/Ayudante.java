package com.izv.dam.newquip.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.izv.dam.newquip.contrato.ContratoBaseDatos;

public class Ayudante extends SQLiteOpenHelper {

    //sqlite
    //tipos de datos https://www.sqlite.org/datatype3.html
    //fechas https://www.sqlite.org/lang_datefunc.html
    //trigger https://www.sqlite.org/lang_createtrigger.html

    private static final int VERSION = 1;

    public Ayudante(Context context) {

        super(context, ContratoBaseDatos.BASEDATOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String sql;
        sql="create table if not exists " + ContratoBaseDatos.TablaNota.TABLA +
                " (" +
                ContratoBaseDatos.TablaNota._ID + " integer primary key autoincrement , " +
                ContratoBaseDatos.TablaNota.TITULO + " text, " +
                ContratoBaseDatos.TablaNota.NOTA + " text " +
                ")";*/

        String sqlA;
        sqlA="create table if not exists " + ContratoBaseDatos.Arbol.TABLA +
                " (" +
                ContratoBaseDatos.Arbol._ID + " integer primary key autoincrement , " +
                ContratoBaseDatos.Arbol.TITULO + " text, " +
                ContratoBaseDatos.Arbol.TIPO + " integer " +
                ")";

        String sqlB;
        sqlB="create table if not exists " + ContratoBaseDatos.Elementos.TABLA +
                " (" +
                ContratoBaseDatos.Elementos._ID + " integer primary key autoincrement , " +
                ContratoBaseDatos.Elementos.CONTENIDO + " text, " +
                ContratoBaseDatos.Elementos.IDPADRE + " integer, " +
                ContratoBaseDatos.Elementos.IMAGEN + " text" +
                ")";

        Log.v("sqlA",sqlA);
        Log.v("sqlB",sqlB);

        db.execSQL(sqlA);
        db.execSQL(sqlB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists " + ContratoBaseDatos.TablaNota.TABLA;
        db.execSQL(sql);
        Log.v("sql",sql);
    }
}