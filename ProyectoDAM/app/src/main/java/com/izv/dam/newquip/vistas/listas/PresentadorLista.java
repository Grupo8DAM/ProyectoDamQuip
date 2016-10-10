package com.izv.dam.newquip.vistas.listas;

import android.content.Context;

import com.izv.dam.newquip.contrato.ContratoLista;
import com.izv.dam.newquip.contrato.ContratoNota;
import com.izv.dam.newquip.pojo.Lista;
import com.izv.dam.newquip.pojo.Nota;

public class PresentadorLista implements ContratoLista.InterfacePresentador {

    private ContratoLista.InterfaceModelo modelo;
    private ContratoLista.InterfaceVista vista;

    public PresentadorLista(ContratoLista.InterfaceVista vista) {

        this.vista = vista;
        this.modelo = new ModeloLista((Context)vista);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
    }

    @Override
    public long onSaveLista(Lista lista) {

        return this.modelo.saveLista(lista);
    }

}