package com.izv.dam.newquip.contrato;

import com.izv.dam.newquip.pojo.Lista;

/**
 * Created by Fernando on 09/10/2016.
 */

public interface ContratoLista {

    interface InterfaceModelo {

        void close();

        Lista getLista(long id);

        long saveLista(Lista lista);

    }

    interface InterfacePresentador {

        void onPause();

        void onResume();

        long onSaveLista(Lista n);

    }

    interface InterfaceVista {

        void mostrarLista(Lista n);

    }

}
