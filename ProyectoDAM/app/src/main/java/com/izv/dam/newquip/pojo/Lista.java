package com.izv.dam.newquip.pojo;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.izv.dam.newquip.contrato.ContratoBaseDatos;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Fernando on 07/10/2016.
 */

public class Lista implements Parcelable {

    private long id;
    private long idP;
    private String titulo;
    private ArrayList<ElementoLista> items;

    //Elementos utilizados para convertir y extraer arraylist a string
    private static final Gson GSON = new GsonBuilder().create();
    private static final Type TYPE = new TypeToken<ArrayList<ElementoLista>>(){}.getType();

    public Lista() {

        this( 0, 0, "", new ArrayList<ElementoLista>());
    }

    public Lista ( long id, long idP, String titulo, ArrayList<ElementoLista> items ) {

        this.id         = id;
        this.idP        = idP;
        this.titulo     = titulo;
        this.items      = items;

    }

    public Lista ( long id, long idP, String titulo, String json ) {

        this.id         = id;
        this.idP        = idP;
        this.titulo     = titulo;
        this.items      = GSON.fromJson( json, TYPE );
    }


    protected Lista(Parcel in) {
        id = in.readLong();
        idP = in.readLong();
        titulo = in.readString();
        items = in.createTypedArrayList(ElementoLista.CREATOR);
    }

    public static final Creator<Lista> CREATOR = new Creator<Lista>() {
        @Override
        public Lista createFromParcel(Parcel in) {
            return new Lista(in);
        }

        @Override
        public Lista[] newArray(int size) {
            return new Lista[size];
        }
    };

    //Metodos para eliminar actualizar y agregar elementos a nuestra lista
    public void addItem ( ElementoLista el ) {

        items.add( el );
    }

    public void removeItem ( int position ) {

        items.remove(position);
    }

    public void updateItem ( int position, ElementoLista el ) {

        if ( items.size() == position ) {

            this.addItem(el);
        }
        else {

            this.removeItem( position );
            items.add( position, el );
        }
    }
    //Setter & Getter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdP(){ return idP; }

    public void setIdP(long idP){ this.idP = idP; }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<ElementoLista> getItems() {
        return items;
    }

    public void setItems(ArrayList<ElementoLista> items) {
        this.items = items;
    }


    //ToString

    @Override
    public String toString() {

        return "Lista{" +
                "id=" + id +
                ", idp='" + idP + '\'' +
                ", titulo='" + titulo + '\'' +
                ", items=" + Arrays.toString(items.toArray( new ElementoLista[items.size()])) +
                '}';
    }

    public static Lista getLista( Cursor c ) {

        long id         = c.getLong(c.getColumnIndex(ContratoBaseDatos.Elementos._ID));
        String titulo   = c.getString(c.getColumnIndex(ContratoBaseDatos.Arbol.TITULO));
        long idP        = c.getLong(c.getColumnIndex(ContratoBaseDatos.Elementos.IDPADRE));
        String json     = c.getString(c.getColumnIndex(ContratoBaseDatos.Elementos.CONTENIDO));

        return new Lista( id, idP, titulo, json );
    }

    public ContentValues getContentValues() {

        return this.getContentValues(false);
    }

    private ContentValues getContentValues(boolean withId){

        ContentValues valores = new ContentValues();

        if(withId){

            valores.put(ContratoBaseDatos.Arbol._ID, this.getId());
        }

        valores.put(ContratoBaseDatos.Arbol.TITULO, this.getTitulo());
        valores.put(ContratoBaseDatos.Elementos.CONTENIDO, GSON.toJson(items));
        valores.put(ContratoBaseDatos.Elementos.IDPADRE, idP);
        valores.put(ContratoBaseDatos.Elementos.IMAGEN, "");

        return valores;
    }

    //Metodo utilizado para comprobar si la lista esta llena o no
    public boolean haveData(){

        if ( items.isEmpty() ) return false;

        for ( ElementoLista el : items ) {

            if ( el.haveText() ) {

                return true;
            }
        }

        return false;
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
        dest.writeTypedList(items);
    }
}
