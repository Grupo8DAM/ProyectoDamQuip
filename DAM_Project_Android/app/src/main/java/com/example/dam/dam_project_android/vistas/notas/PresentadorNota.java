package com.example.dam.dam_project_android.vistas.notas;

import android.content.Context;

import com.example.dam.dam_project_android.contrato.ContratoNota;
import com.example.dam.dam_project_android.pojo.Nota;


public class PresentadorNota implements ContratoNota.InterfacePresentador {

    private ContratoNota.InterfaceModelo modelo;
    private ContratoNota.InterfaceVista vista;

    public PresentadorNota(ContratoNota.InterfaceVista vista) {
        this.vista = vista;
        this.modelo = new ModeloNota((Context)vista);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
    }

    @Override
    public long onSaveNota(Nota n) {
        return this.modelo.saveNota(n);
    }

}