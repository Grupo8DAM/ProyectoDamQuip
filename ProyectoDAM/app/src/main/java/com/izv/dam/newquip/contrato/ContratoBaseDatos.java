package com.izv.dam.newquip.contrato;

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

    public static abstract class Arbol implements BaseColumns {

        public static final String TABLA    = "Arbol";
        public static final String TITULO   = "Titulo";
        public static final String TIPO     = "Tipo";


        public static final String[] PROJECTION_ALL = {_ID, TITULO, TIPO};
        public static final String SORT_ORDER_DEFAULT = _ID + " desc";
    }

    public static abstract class Elementos implements BaseColumns {

        public static final String TABLA        = "Elementos";
        public static final String CONTENIDO    = "Contenido";
        public static final String IDPADRE      = "ParentId";
        public static final String IMAGEN       = "Imagen";

        public static final String[] PROJECTION_ALL = {_ID, CONTENIDO, IDPADRE, IMAGEN};
        public static final String SORT_ORDER_DEFAULT = IDPADRE + " desc";

    }


}