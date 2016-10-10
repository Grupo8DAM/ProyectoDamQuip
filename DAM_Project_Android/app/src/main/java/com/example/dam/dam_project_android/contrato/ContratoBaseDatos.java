package com.example.dam.dam_project_android.contrato;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class ContratoBaseDatos {

    public final static String BASEDATOS = "quiip.sqlite";

    private ContratoBaseDatos(){
    }

    public static abstract class TablaNota implements BaseColumns {
        //BaseColumns incluye de forma predeterminada el campo _id
        public static final String TABLA = "nota";
        public static final String TITULO = "titulo";
        public static final String NOTA = "nota";
        public static final String[] PROJECTION_ALL = {_ID, TITULO, NOTA};
        public static final String SORT_ORDER_DEFAULT = _ID + " desc";
    }
}