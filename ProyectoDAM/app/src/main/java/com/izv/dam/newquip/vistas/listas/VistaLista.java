package com.izv.dam.newquip.vistas.listas;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.izv.dam.newquip.R;
import com.izv.dam.newquip.adaptadores.AdaptadorVistaLista;
import com.izv.dam.newquip.contrato.ContratoLista;
import com.izv.dam.newquip.pojo.ElementoLista;
import com.izv.dam.newquip.pojo.Lista;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VistaLista extends AppCompatActivity implements ContratoLista.InterfaceVista {

    private Lista lista = new Lista();
    private PresentadorLista presentador;

    //Componentes Necesarios Para el Trabajo con Las Listas

    //Layouts
    private LinearLayout lyAddItem;

    //Componentes Varios
    private TextInputEditText edTitLista;

    //RecyclerView
    private RecyclerView rv;
    private AdaptadorVistaLista adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        presentador = new PresentadorLista(this);

        if (savedInstanceState != null) {

            lista = savedInstanceState.getParcelable("lista");
        }
        else
        {
            Bundle b = getIntent().getExtras();

            if(b != null ) {
                lista = b.getParcelable("lista");
            }
        }

        init();
        mostrarLista(lista);
    }

    @Override
    protected void onPause() {

        saveLista();
        presentador.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        presentador.onResume();
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("lista", lista);
    }

    @Override
    public void mostrarLista(Lista lista) {

        edTitLista.setText(lista.getTitulo());

        if ( lista.getItems().isEmpty() ) {

            adapter.addNewView();
        }
    }

    private void saveLista() {

        lista.setTitulo(edTitLista.getText().toString());
        lista.setItems(adapter.getItems());

        long r = presentador.onSaveLista(lista);

        if ( r > 0 & lista.getId() == 0 ) {

            lista.setId(r);
        }
    }

    private void init() {

        rv                  = (RecyclerView) findViewById(R.id.rvActivityLista);
        adapter             = new AdaptadorVistaLista(lista.getItems());
        lyAddItem           = (LinearLayout) findViewById(R.id.lyAddItem);
        edTitLista          = (TextInputEditText) findViewById(R.id.edTitLista);

        rv.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);

        lyAddItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                VistaLista.this.adapter.addNewView();
            }
        });

    }


}