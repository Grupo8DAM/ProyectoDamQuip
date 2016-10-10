package com.izv.dam.newquip.contrato;

import android.database.Cursor;

import com.izv.dam.newquip.pojo.Lista;
import com.izv.dam.newquip.pojo.Nota;

public interface ContratoMain {

    interface InterfaceModelo {

        void close();

        long deleteNota(int position);

        long deleteNota(Nota n);

        Nota getNota(int position);

        long deleteLista(int position);

        long deleteLista(Lista l);

        Lista getLista(int position);

        void loadData(OnDataLoadListener listener);

        interface OnDataLoadListener {
            public void setCursor(Cursor c);
        }
    }

    interface InterfacePresentador {

        void onAddNota();

        void onDeleteNota(int position);

        void onDeleteNota(Nota n);

        void onEditNota(int position);

        void onEditNota(Nota n);

        void onPause();

        void onResume();

        void onAddLista();

        void onShowBorrarNota(int position);

        void onDeleteLista(int position);

        void onDeleteLista(Lista lista);

        void onEditLista(int position);

        void onEditLista(Lista lista);

        void onShowBorrarLista(int position);

    }

    interface InterfaceVista {

        void mostrarAgregarNota();

        void mostrarDatos(Cursor c);

        void mostrarEditarNota(Nota n);

        void mostrarConfirmarBorrarNota(Nota n);

        void mostrarAgregarLista();

        void mostrarEditarLista(Lista lista);

        void mostrarConfirmarBorrarLista(Lista lista);

    }

}