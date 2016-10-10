package com.izv.dam.newquip.vistas.main;

import android.content.Context;
import android.database.Cursor;
import com.izv.dam.newquip.contrato.ContratoMain;
import com.izv.dam.newquip.gestion.GestionLista;
import com.izv.dam.newquip.gestion.GestionNota;
import com.izv.dam.newquip.pojo.Lista;
import com.izv.dam.newquip.pojo.Nota;

public class ModeloQuip implements ContratoMain.InterfaceModelo {

    private GestionNota gn  = null;
    private GestionLista gl = null;
    private Cursor cursor;

    public ModeloQuip(Context c) {

        gn = new GestionNota(c);
        gl = new GestionLista(c);
    }

    @Override
    public void close() {

        gn.close();
        gl.close();
    }

    @Override
    public long deleteNota(Nota n) {

        return gn.delete(n);
    }

    @Override
    public long deleteNota(int position) {

        cursor.moveToPosition(position);
        Nota n = Nota.getNota(cursor);
        return this.deleteNota(n);

    }

    @Override
    public Nota getNota(int position) {

        cursor.moveToPosition(position);
        Nota n = Nota.getNota(cursor);
        return n;
    }

    @Override
    public long deleteLista(int position) {

        Lista lista = this.getLista(position);
        return this.deleteLista(lista);
    }

    @Override
    public long deleteLista(Lista l) {

        return gl.delete(l);
    }

    @Override
    public Lista getLista(int position) {

        cursor.moveToPosition(position);
        Lista lista = Lista.getLista(cursor);
        return lista;
    }

    @Override
    public void loadData(OnDataLoadListener listener) {

        cursor = gn.getCursor();
        listener.setCursor(cursor);
    }
}