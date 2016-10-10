package com.example.dam.dam_project_android.contrato;

import android.database.Cursor;

import com.example.dam.dam_project_android.pojo.Nota;


public interface ContratoMain {

    interface InterfaceModelo {

        void close();

        long deleteNota(int position);

        long deleteNota(Nota n);

        Nota getNota(int position);

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

        void onShowBorrarNota(int position);

    }

    interface InterfaceVista {

        void mostrarAgregarNota();

        void mostrarDatos(Cursor c);

        void mostrarEditarNota(Nota n);

        void mostrarConfirmarBorrarNota(Nota n);

    }

}