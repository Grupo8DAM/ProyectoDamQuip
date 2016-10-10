package com.izv.dam.newquip.pojo;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.izv.dam.newquip.contrato.ContratoBaseDatos;

public class Nota implements Parcelable {

    private long id,idP;
    private String titulo, nota;

    public Nota() {

        this(0, 0, null, null);
    }

    public Nota(long id, long idP, String titulo, String nota) {
        this.id     = id;
        this.idP    = idP;
        this.titulo = titulo;
        this.nota   = nota;
    }

    protected Nota(Parcel in) {

        id      = in.readLong();
        idP     = in.readLong();
        titulo  = in.readString();
        nota    = in.readString();
    }

    public static final Creator<Nota> CREATOR = new Creator<Nota>() {
        @Override
        public Nota createFromParcel(Parcel in) {

            return new Nota(in);
        }

        @Override
        public Nota[] newArray(int size) {

            return new Nota[size];
        }
    };

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getIdP() {

        return idP;
    }

    public void setIdP(long idP){

        this.idP = idP;
    }

    public void setId(String id) {

        try
        {
            this.id = Long.parseLong(id);

        } catch(NumberFormatException e){

            this.id = 0;
        }
    }

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public String getNota() {

        return nota;
    }

    public void setNota(String nota) {

        this.nota = nota;
    }

    public ContentValues getContentValues(){

        return this.getContentValues(false);
    }

    public ContentValues getContentValues(boolean withId){

        ContentValues valores = new ContentValues();

        if(withId){

            valores.put(ContratoBaseDatos.Elementos._ID, this.getId());
        }

        valores.put(ContratoBaseDatos.Arbol.TITULO, this.getTitulo());
        valores.put(ContratoBaseDatos.Elementos.CONTENIDO, this.getNota());
        valores.put(ContratoBaseDatos.Elementos.IDPADRE, this.idP);
        valores.put(ContratoBaseDatos.Elementos.IMAGEN, "");

        return valores;
    }

    public static Nota getNota(Cursor c){

        Nota objeto = new Nota();

        objeto.setId(c.getLong(c.getColumnIndex(ContratoBaseDatos.Elementos._ID)));
        objeto.setIdP(c.getLong(c.getColumnIndex(ContratoBaseDatos.Elementos.IDPADRE)));
        objeto.setTitulo(c.getString(c.getColumnIndex(ContratoBaseDatos.Arbol.TITULO)));
        objeto.setNota(c.getString(c.getColumnIndex(ContratoBaseDatos.Elementos.CONTENIDO)));

        return objeto;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", nota='" + nota + '\'' +
                '}';
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(id);
        dest.writeLong(idP);
        dest.writeString(titulo);
        dest.writeString(nota);
    }
}