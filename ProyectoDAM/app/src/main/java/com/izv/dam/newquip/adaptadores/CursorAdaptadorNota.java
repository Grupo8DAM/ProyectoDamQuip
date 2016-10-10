package com.izv.dam.newquip.adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.izv.dam.newquip.R;
import com.izv.dam.newquip.contrato.ContratoBaseDatos;
import com.izv.dam.newquip.pojo.ElementoLista;
import com.izv.dam.newquip.pojo.Lista;
import com.izv.dam.newquip.pojo.Nota;

import java.util.ArrayList;


/**
 * Created by Fernando on 04/10/2016.
 */

public class CursorAdaptadorNota extends MyCursorAdapter<RecyclerView.ViewHolder> {

    //Escuchadores
    private View.OnClickListener clickListener;
    private View.OnLongClickListener longClickListener;

    public CursorAdaptadorNota(Context context, Cursor cursor){

        super(context,cursor);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li = LayoutInflater.from(parent.getContext());

        View  v = null;
        RecyclerView.ViewHolder vh = null;

        switch( viewType ) {

            case 0 : {

                v   = li.inflate( R.layout.item_quip_nota, parent, false);
                vh  = new ViewHolderNota(v);

                break;
            }

            case 1 : {

                v   = li.inflate( R.layout.item_quip_lista, parent, false);
                vh  = new ViewHolderLista(v);

                break;
            }
        }

        //Indicamos a la vista quien gestiona su evento
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);

        return vh;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {

        int tipo = cursor.getInt(cursor.getColumnIndex(ContratoBaseDatos.Arbol.TIPO));

        if ( tipo == 0 ) {

            Nota n = Nota.getNota(cursor);
            ((ViewHolderNota)viewHolder).viewNota(n);
        }
        else
        {
            Lista l = Lista.getLista(cursor);
            ((ViewHolderLista)viewHolder).viewLista(l);
        }
    }

    //Metodos para asignar desde la vista los eventos
    public void setOnClickListener( View.OnClickListener listener ) {

        this.clickListener = listener;
    }

    public void setOnLongClickListener ( View.OnLongClickListener longClickListener ) {

        this.longClickListener = longClickListener;
    }

    @Override
    public void onClick(View v) {

        if ( this.clickListener != null ) clickListener.onClick(v);
    }

    @Override
    public boolean onLongClick(View v) {

        if ( this.longClickListener != null ) {

            longClickListener.onLongClick(v);

            return true;
        }

        return false;
    }

    public static class ViewHolderNota extends RecyclerView.ViewHolder {

        public TextView tvTitulo;

        public ViewHolderNota(View view) {

            super(view);

            tvTitulo    = (TextView) view.findViewById(R.id.tvTituloNota);
        }

        public void viewNota( Nota n ) {

            tvTitulo.setText(n.getTitulo());
        }

    }

    public static class ViewHolderLista extends RecyclerView.ViewHolder {

        public CheckBox cb1, cb2, cb3;
        public TextView tvTitulo, tv1, tv2, tv3, tv4;

        public ViewHolderLista( View view ) {

            super(view);

            cb1         = (CheckBox) view.findViewById(R.id.cbItem1);
            cb2         = (CheckBox) view.findViewById(R.id.cbItem2);
            cb3         = (CheckBox) view.findViewById(R.id.cbItem3);
            tvTitulo    = (TextView) view.findViewById(R.id.tvTitulo);
            tv1         = (TextView) view.findViewById(R.id.tvItem1);
            tv2         = (TextView) view.findViewById(R.id.tvItem2);
            tv3         = (TextView) view.findViewById(R.id.tvItem3);
            tv4         = (TextView) view.findViewById(R.id.tvItem4);
        }

        public void viewLista( Lista lista ) {

            //Titulo lista
            if ( !lista.getTitulo().isEmpty() ) {

                tvTitulo.setText(lista.getTitulo());
                tvTitulo.setVisibility(View.VISIBLE);

            }

            //Elementos finitos de la lista
            ArrayList<ElementoLista> items = lista.getItems();

            for ( int i = 0; i < items.size(); i++ ) {

                ElementoLista el = items.get(i);

                switch ( i ) {

                    case 0 : {

                        cb1.setChecked(el.isCheck());
                        tv1.setText(el.getTexto());

                        cb1.setVisibility(View.VISIBLE);
                        tv1.setVisibility(View.VISIBLE);

                        break;
                    }

                    case 1 : {

                        cb2.setChecked(el.isCheck());
                        tv2.setText(el.getTexto());

                        cb2.setVisibility(View.VISIBLE);
                        tv2.setVisibility(View.VISIBLE);

                        break;
                    }

                    case 2 : {

                        cb3.setChecked(el.isCheck());
                        tv3.setText(el.getTexto());

                        cb1.setVisibility(View.VISIBLE);
                        tv1.setVisibility(View.VISIBLE);

                        break;
                    }

                }

                if ( i == 2 ) {

                    if ( i < items.size() ) {

                        tv4.setVisibility(View.VISIBLE);
                    }

                    break;
                }
            }

        }
    }


}
