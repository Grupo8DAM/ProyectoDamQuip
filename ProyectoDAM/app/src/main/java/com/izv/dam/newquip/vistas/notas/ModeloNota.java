package com.izv.dam.newquip.vistas.notas;

import android.content.Context;

import com.izv.dam.newquip.contrato.ContratoNota;
import com.izv.dam.newquip.gestion.GestionNota;
import com.izv.dam.newquip.pojo.Nota;

public class ModeloNota implements ContratoNota.InterfaceModelo {

    private GestionNota gn = null;

    public ModeloNota(Context c) {

        gn = new GestionNota(c);
    }

    @Override
    public void close() {

        gn.close();
    }

    @Override
    public Nota getNota(long id) {

        return gn.get(id);
    }

    @Override
    public long saveNota(Nota n) {
        long r;

        if( n.getId()==0 ) {

             r = this.insertNota(n);

        } else {

            r = this.updateNota(n);
        }

        return r;
    }

    private long deleteNota(Nota n) {

        return gn.delete(n);
    }

    private long insertNota(Nota n) {

        if(n.getNota().trim().compareTo("")==0 && n.getTitulo().trim().compareTo("")==0) {

            return 0;
        }

        return gn.insert(n);
    }

    private long updateNota(Nota n) {

        if(n.getNota().trim().compareTo("")==0 && n.getTitulo().trim().compareTo("")==0) {
            this.deleteNota(n);
            gn.delete(n);
            return 0;
        }
        return gn.update(n);
    }
}