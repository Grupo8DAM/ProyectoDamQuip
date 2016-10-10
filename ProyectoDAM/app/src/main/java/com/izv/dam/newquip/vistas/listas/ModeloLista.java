package com.izv.dam.newquip.vistas.listas;

import android.content.Context;

import com.izv.dam.newquip.contrato.ContratoLista;
import com.izv.dam.newquip.gestion.GestionLista;
import com.izv.dam.newquip.gestion.GestionNota;
import com.izv.dam.newquip.pojo.Lista;
import com.izv.dam.newquip.pojo.Nota;

public class ModeloLista implements ContratoLista.InterfaceModelo {

    private GestionLista gl = null;

    public ModeloLista(Context c) {

        gl = new GestionLista(c);
    }

    @Override
    public void close() {

        gl.close();
    }

    @Override
    public Lista getLista(long id) {

        return gl.get(id);
    }

    @Override
    public long saveLista(Lista lista) {
        long r;

        if( lista.getId()==0 ) {

            r = this.insertLista(lista);

        } else {

            r = this.updateLista(lista);
        }

        return r;
    }

    private long deleteLista(Lista lista) {

        return gl.delete(lista);
    }

    private long insertLista(Lista lista) {

        if( lista.getTitulo().trim().compareTo("")==0 && !lista.haveData() ) {
            return 0;
        }
        return gl.insert(lista);
    }

    private long updateLista(Lista lista) {

        if( lista.getTitulo().trim().compareTo("")== 0 && !lista.haveData() ) {

            this.deleteLista(lista);
            gl.delete(lista);
            return 0;

        }

        return gl.update(lista);
    }
}