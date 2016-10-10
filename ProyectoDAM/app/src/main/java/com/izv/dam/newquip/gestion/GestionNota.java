package com.izv.dam.newquip.gestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.izv.dam.newquip.contrato.ContratoBaseDatos;
import com.izv.dam.newquip.pojo.Nota;

public class GestionNota extends Gestion<Nota>{

    public GestionNota(Context c) {
        super(c);
    }

    public GestionNota(Context c, boolean write) {
        super(c, write);
    }

    @Override
    public int deleteAll() {

        this.deleteAll(ContratoBaseDatos.Arbol.TABLA);
        this.deleteAll(ContratoBaseDatos.Elementos.TABLA);

        return 1;
    }

    public int delete(String condicion, String[] argumentos) {

        return this.delete(ContratoBaseDatos.Elementos.TABLA, condicion, argumentos);
    }

    @Override
    public int delete(Nota objeto) {

        //Eliminamos en primer lugar en la tabla arbol y en segundo lugar en la de elementos
        String condicionA       = ContratoBaseDatos.Arbol._ID + " = ?";
        String[] argumentosA    = new String[]{String.valueOf(objeto.getIdP())};

        String condicionB       = ContratoBaseDatos.Elementos._ID + " = ?";
        String[] argumentosB    = new String[]{String.valueOf(objeto.getId())};

        this.delete( ContratoBaseDatos.Arbol.TABLA, condicionA, argumentosA);

        return this.delete(ContratoBaseDatos.Elementos.TABLA, condicionB, argumentosB);
    }

    public Nota get(long id) {

        //Construimos nuestra consulta sql
        String sql =    " select " + ContratoBaseDatos.Elementos.TABLA + "." + ContratoBaseDatos.Elementos._ID +
                ", " + ContratoBaseDatos.Arbol.TABLA + "." + ContratoBaseDatos.Arbol.TITULO +
                ", " + ContratoBaseDatos.Elementos.TABLA + "." + ContratoBaseDatos.Elementos.CONTENIDO +
                ", " + ContratoBaseDatos.Elementos.TABLA + "." + ContratoBaseDatos.Elementos.IDPADRE +
                ", " + ContratoBaseDatos.Elementos.TABLA + "." + ContratoBaseDatos.Elementos.IMAGEN +
                ", " + ContratoBaseDatos.Arbol.TABLA + "." + ContratoBaseDatos.Arbol.TIPO +
                " from " + ContratoBaseDatos.Arbol.TABLA + " inner join " + ContratoBaseDatos.Elementos.TABLA +
                " on " + ContratoBaseDatos.Arbol.TABLA + "." + ContratoBaseDatos.Arbol._ID +
                " = " + ContratoBaseDatos.Elementos.TABLA + "." + ContratoBaseDatos.Elementos._ID +
                " where " + ContratoBaseDatos.Elementos.TABLA + "." + ContratoBaseDatos.Elementos._ID +
                " = ?";

        String[] parametros = new String[]{ String.valueOf(id) };

        Cursor c = this.getConsulta( sql, parametros);

        if(c.getCount() > 0) {

            c.moveToFirst();
            Nota nota = Nota.getNota(c);
            return nota;
        }

        return null;
    }

    @Override
    public Cursor getCursor(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return this.getCursor(ContratoBaseDatos.TablaNota.TABLA, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    @Override
    public long insert(ContentValues objeto) {

        ContentValues cvA = new ContentValues();
        cvA.put(ContratoBaseDatos.Arbol.TITULO, objeto.getAsString(ContratoBaseDatos.Arbol.TITULO));
        cvA.put(ContratoBaseDatos.Arbol.TIPO, 0);

        //Id asignado al elemento insertado en la tabla Arbol
        long idP    = this.insert(ContratoBaseDatos.Arbol.TABLA, cvA);

        ContentValues cvE = new ContentValues();
        cvE.put(ContratoBaseDatos.Elementos.CONTENIDO, objeto.getAsString(ContratoBaseDatos.Elementos.CONTENIDO));
        cvE.put(ContratoBaseDatos.Elementos.IDPADRE, idP);
        cvE.put(ContratoBaseDatos.Elementos.IMAGEN, objeto.getAsString(ContratoBaseDatos.Elementos.IMAGEN));

        return this.insert(ContratoBaseDatos.Elementos.TABLA, cvE);
    }

    @Override
    public long insert(Nota objeto) {

        return this.insert(objeto.getContentValues());
    }

    @Override
    public int update(ContentValues valores, String condicion, String[] argumentos){

        return this.update(ContratoBaseDatos.Elementos.TABLA, valores, condicion, argumentos);
    }

    @Override
    public int update(Nota objeto) {

        ContentValues valores = objeto.getContentValues();

        ContentValues cvA   = new ContentValues();
        cvA.put(ContratoBaseDatos.Arbol.TITULO, valores.getAsString(ContratoBaseDatos.Arbol.TITULO));
        cvA.put(ContratoBaseDatos.Arbol.TIPO, 0);

        //Actualizamos el contenido en las dos tablas
        String condicionA       = ContratoBaseDatos.Arbol._ID + " = ?";
        String[] argumentosA    = new String[]{String.valueOf(objeto.getIdP())};

        this.update(ContratoBaseDatos.Arbol.TABLA, cvA, condicionA, argumentosA);

        String condicionB       = ContratoBaseDatos.Elementos._ID + " = ?";
        String[] argumentosB    = new String[]{String.valueOf(objeto.getId())};

        ContentValues cvE       = new ContentValues();

        cvE.put(ContratoBaseDatos.Elementos.CONTENIDO, valores.getAsString(ContratoBaseDatos.Elementos.CONTENIDO));
        cvE.put(ContratoBaseDatos.Elementos.IDPADRE, valores.getAsLong(ContratoBaseDatos.Elementos.IDPADRE));
        cvE.put(ContratoBaseDatos.Elementos.IMAGEN, valores.getAsString(ContratoBaseDatos.Elementos.IMAGEN));

        return this.update(ContratoBaseDatos.Elementos.TABLA, cvE, condicionB, argumentosB );
    }

}