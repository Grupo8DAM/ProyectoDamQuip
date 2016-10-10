package com.izv.dam.newquip.adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.izv.dam.newquip.R;
import com.izv.dam.newquip.pojo.ElementoLista;
import com.izv.dam.newquip.pojo.Nota;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AdaptadorVistaLista extends RecyclerView.Adapter<AdaptadorVistaLista.ViewHolder> {

    private ArrayList<ElementoLista> items;

    public AdaptadorVistaLista( ArrayList<ElementoLista> items ) {

        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li   = LayoutInflater.from(parent.getContext());
        View view           = li.inflate( R.layout.item_lista, parent, false);

        //view.setOnFocusChangeListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bindItem(items.get(position));
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public void addNewView(){

        items.add( new ElementoLista());
        this.notifyItemInserted(items.size());
    }

    public void removeView( int position ){

        items.remove(position);
        this.notifyItemRemoved(position);
    }


    public ArrayList<ElementoLista> getItems(){

        return this.items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CheckBox cbDone, cbRemove;
        public EditText edTexto;

        public ViewHolder(View itemView) {

            super(itemView);

            cbDone      = (CheckBox) itemView.findViewById(R.id.cbDone);
            cbRemove    = (CheckBox) itemView.findViewById(R.id.cbRemove);
            edTexto     = (EditText) itemView.findViewById(R.id.edTarea);

            cbDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = ViewHolder.super.getAdapterPosition();
                    items.get(position).setCheck(cbDone.isChecked());
                    notifyItemChanged(position);
                }
            });

            //Controlamos que se actualize el texto como por la entrada por teclado o al cambiar el foco
            edTexto.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                //Se registran las opciones IME para controlar la introduccion del texto
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if ( (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT) ) {

                        int position = ViewHolder.super.getAdapterPosition();
                        items.get(position).setTexto(edTexto.getText().toString());
                        notifyItemChanged(position);

                    }

                    //Devolvemos siempre falso para ocultar el teclado
                    return false;
                }
            });

            edTexto.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if ( hasFocus ) {

                        cbRemove.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        cbRemove.setVisibility(View.GONE);
                    }
                }
            });

            edTexto.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    int position = ViewHolder.super.getAdapterPosition();
                    items.get(position).setTexto(edTexto.getText().toString());

                }
            });
            cbRemove.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    removeView(ViewHolder.super.getAdapterPosition());
                }
            });
        }

        public void bindItem( ElementoLista el ){

            cbDone.setChecked(el.isCheck());
            edTexto.setText(el.getTexto());
        }
    }
}