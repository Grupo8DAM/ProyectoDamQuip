package com.izv.dam.newquip.dialogo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.izv.dam.newquip.R;
import com.izv.dam.newquip.pojo.Lista;

/**
 * Created by Fernando on 09/10/2016.
 */

public class DialogoBorrarLista extends DialogFragment {

    private Lista lista;

    // Interfaz de comunicación
    OnBorrarDialogListener listener;

    public DialogoBorrarLista() {
    }

    public static DialogoBorrarLista newInstance(Lista lista) {

        DialogoBorrarLista fragment = new DialogoBorrarLista();
        Bundle args = new Bundle();
        args.putParcelable("lista", lista);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ( getArguments() != null ) {
            lista = getArguments().getParcelable("lista");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return createDialogBorrar();
    }

    public AlertDialog createDialogBorrar() {

        String titulo_dialogo= String.format("%s %s", getString(R.string.etiqueta_dialogo_borrar), lista.getTitulo());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(titulo_dialogo);
        builder.setMessage(R.string.mensaje_confirm_borrar_lista);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onBorrarPossitiveButtonClick(lista);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onBorrarNegativeButtonClick();
            }
        });
        AlertDialog alertBorrar = builder.create();
        return alertBorrar;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (OnBorrarDialogListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(
                    context.toString() + " no implementó OnBorrarDialogListener");

        }
    }

}
