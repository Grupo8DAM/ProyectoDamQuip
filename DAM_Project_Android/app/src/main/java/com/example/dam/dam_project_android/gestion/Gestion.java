package com.example.dam.dam_project_android.gestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dam.dam_project_android.basedatos.Ayudante;


public abstract class Gestion<T> {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public Gestion(Context c) {
        this(c, true);
    }

    public Gestion(Context c, boolean write) {
        abd = new Ayudante(c);
        if(write) {
            this.open();
        } else {
            this.openRead();
        }
    }

    public Ayudante getAyudante() {
        return abd;
    }

    public SQLiteDatabase getBasedatos() {
        return bd;
    }

    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void openRead() {
        bd = abd.getReadableDatabase();
    }

    public void close() {
        abd.close();
    }

    public abstract long insert(T objeto);

    public abstract long insert(ContentValues objeto);

    public long insert(String tabla, ContentValues valores) {
        return bd.insert(tabla, null, valores);
    }

    public abstract int deleteAll();

    public int deleteAll(String tabla){
        return this.delete(tabla, "1=1", null);
    }

    public abstract int delete(T objeto);

    public int delete(String tabla, String condicion, String[] argumentos) {
        return bd.delete(tabla, condicion, argumentos);
    }

    public abstract int update(T objeto);

    public abstract int update(ContentValues valores, String condicion, String[] argumentos);

    public int update(String tabla, ContentValues valores, String condicion, String[] argumentos) {
        return bd.update(tabla, valores, condicion, argumentos);
    }

    public Cursor getConsulta(String sql, String[] params){
        return bd.rawQuery(sql, params);
    }

    public abstract Cursor getCursor();

    public abstract Cursor getCursor(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy);

    public Cursor getCursor(String tabla, String[] proyeccion, String orden) {
        return getCursor(tabla, proyeccion, null, null, null, null, orden);
    }

    public Cursor getCursor(String tabla, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        Cursor cursor = bd.query(tabla, columns, selection, selectionArgs, groupBy, having, orderBy);
        return cursor;
    }
}