package com.izv.dam.newquip.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fernando on 08/10/2016.
 */

public class ElementoLista implements Parcelable {

    private String texto;
    private boolean check;

    public ElementoLista() {

        this("",false);
    }

    public ElementoLista( String texto, boolean check ) {
;
        this.texto  = texto;
        this.check  = check;
    }

    protected ElementoLista(Parcel in) {

        texto = in.readString();
        check = in.readByte() != 0;
    }

    public static final Creator<ElementoLista> CREATOR = new Creator<ElementoLista>() {
        @Override
        public ElementoLista createFromParcel(Parcel in) {
            return new ElementoLista(in);
        }

        @Override
        public ElementoLista[] newArray(int size) {
            return new ElementoLista[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(texto);
        dest.writeByte((byte) (check ? 1 : 0));
    }

    //Setter & Getter


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    //Metodo utilizado para comprobar si el elemento esta relleno o no
    public boolean haveText(){

        return !texto.isEmpty();
    }

    //ToString

    @Override
    public String toString() {
        return "ElementoLista{" +
                " texto='" + texto + '\'' +
                ", check=" + check +
                '}';
    }
}
