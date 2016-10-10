package com.izv.dam.newquip.dialogo;

import com.izv.dam.newquip.pojo.Lista;
import com.izv.dam.newquip.pojo.Nota;

/**
 * Created by Pilar on 28/09/2016.
 */

public interface OnBorrarDialogListener {

    void onBorrarPossitiveButtonClick(Lista l);
    void onBorrarPossitiveButtonClick(Nota n);
    void onBorrarNegativeButtonClick();
}
