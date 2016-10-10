package com.izv.dam.newquip.vistas.main;

import android.content.Context;
import android.database.Cursor;

import com.izv.dam.newquip.contrato.ContratoMain;
import com.izv.dam.newquip.pojo.Lista;
import com.izv.dam.newquip.pojo.Nota;

public class PresentadorQuip implements ContratoMain.InterfacePresentador{

    private ContratoMain.InterfaceModelo modelo;
    private ContratoMain.InterfaceVista vista;
    private ContratoMain.InterfaceModelo.OnDataLoadListener oyente;

    public PresentadorQuip(ContratoMain.InterfaceVista vista) {

        this.vista = vista;
        this.modelo = new ModeloQuip((Context)vista);
        oyente = new ContratoMain.InterfaceModelo.OnDataLoadListener() {
            @Override
            public void setCursor(Cursor c) {
                PresentadorQuip.this.vista.mostrarDatos(c);
            }
        };
    }

    @Override
    public void onAddNota() {

        this.vista.mostrarAgregarNota();
    }

    @Override
    public void onDeleteNota(Nota n) {

        this.modelo.deleteNota(n);
        this.modelo.loadData(oyente);

    }

    @Override
    public void onEditNota(Nota n) {

        this.vista.mostrarEditarNota(n);
    }


    @Override
    public void onDeleteNota(int position) {

        this.modelo.deleteNota(position);
        this.modelo.loadData(oyente);
    }

    @Override
    public void onEditNota(int position) {
        Nota n = this.modelo.getNota(position);
        this.onEditNota(n);
    }


    @Override
    public void onShowBorrarNota(int position) {
        Nota n = this.modelo.getNota(position);
        this.vista.mostrarConfirmarBorrarNota(n);
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {

        this.modelo.loadData(oyente);
    }

    @Override
    public void onAddLista() {

        this.vista.mostrarAgregarLista();

    }

    @Override
    public void onEditLista(int position) {

        Lista lista = this.modelo.getLista(position);
        this.onEditLista(lista);
    }

    @Override
    public void onEditLista(Lista lista) {

        this.vista.mostrarEditarLista(lista);
    }

    @Override
    public void onDeleteLista(int position) {

        this.modelo.deleteLista(position);
        this.modelo.loadData(oyente);
    }

    @Override
    public void onDeleteLista(Lista lista) {

        this.modelo.deleteLista(lista);
        this.modelo.loadData(oyente);
    }


    @Override
    public void onShowBorrarLista(int position) {

        Lista lista = this.modelo.getLista(position);
        this.vista.mostrarConfirmarBorrarLista(lista);

    }


}
