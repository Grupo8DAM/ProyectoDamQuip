package com.example.dam.dam_project_android.contrato;


import com.example.dam.dam_project_android.pojo.Nota;

public interface ContratoNota {

    interface InterfaceModelo {

        void close();

        Nota getNota(long id);

        long saveNota(Nota n);

    }

    interface InterfacePresentador {

        void onPause();

        void onResume();

        long onSaveNota(Nota n);

    }

    interface InterfaceVista {

        void mostrarNota(Nota n);

    }

}