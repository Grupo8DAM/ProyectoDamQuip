package com.izv.dam.newquip.vistas.main;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.izv.dam.newquip.R;
import com.izv.dam.newquip.adaptadores.CursorAdaptadorNota;
import com.izv.dam.newquip.contrato.ContratoMain;
import com.izv.dam.newquip.dialogo.DialogoBorrarLista;
import com.izv.dam.newquip.dialogo.OnBorrarDialogListener;
import com.izv.dam.newquip.pojo.Lista;
import com.izv.dam.newquip.pojo.Nota;
import com.izv.dam.newquip.dialogo.DialogoBorrarNota;
import com.izv.dam.newquip.vistas.notas.VistaNota;
import com.izv.dam.newquip.vistas.listas.VistaLista;

public class VistaQuip extends AppCompatActivity implements ContratoMain.InterfaceVista , OnBorrarDialogListener {

    private CursorAdaptadorNota adaptador;
    private PresentadorQuip presentador;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quip);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarUp);
        setSupportActionBar(toolbar);

        presentador = new PresentadorQuip(this);

        rv = (RecyclerView) findViewById(R.id.lvListaNotas);
        adaptador = new CursorAdaptadorNota(this, null);

        rv.setHasFixedSize(true);
        rv.setAdapter(adaptador);
        rv.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false));

        adaptador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int i       = rv.getChildAdapterPosition(v);
                int type    = adaptador.getType(i);

                if ( type == 0 ) {

                    presentador.onEditNota(i);
                }
                else
                {
                    presentador.onEditLista(i);
                }
            }
        });

        adaptador.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(VistaQuip.this, "delete", Toast.LENGTH_SHORT).show();

                int i       = rv.getChildAdapterPosition(v);
                int type    = adaptador.getType(i);

                if ( type == 0 ) {

                    presentador.onShowBorrarNota(i);
                }
                else
                {
                    presentador.onShowBorrarLista(i);
                }

                return true;
            }
        });

        TextView ed = (TextView) findViewById(R.id.tvCrearNota);
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presentador.onAddNota();
            }
        });

        ImageButton iv = (ImageButton) findViewById(R.id.ibCrearLista);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presentador.onAddLista();
            }
        });
    }

    @Override
    protected void onPause() {

        presentador.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {

        presentador.onResume();
        super.onResume();
    }



    @Override
    public void mostrarAgregarNota() {

        Toast.makeText(VistaQuip.this, "add", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, VistaNota.class);
        startActivity(i);
    }

    @Override
    public void mostrarDatos(Cursor c) {

        adaptador.changeCursor(c);
    }

    @Override
    public void mostrarEditarNota(Nota n) {

        Toast.makeText(VistaQuip.this, "edit", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, VistaNota.class);
        Bundle b = new Bundle();
        b.putParcelable("nota", n);
        i.putExtras(b);
        startActivity(i);
    }

    @Override
    public void mostrarConfirmarBorrarNota(Nota n) {

        DialogoBorrarNota fragmentBorrar = DialogoBorrarNota.newInstance(n);
        fragmentBorrar.show(getSupportFragmentManager(), "Dialogo borrar");

    }

    @Override
    public void onBorrarPossitiveButtonClick(Nota n) {

        presentador.onDeleteNota(n);
    }


    @Override
    public void mostrarAgregarLista() {

        Intent i = new Intent(this, VistaLista.class);
        startActivity(i);

    }

    @Override
    public void mostrarEditarLista(Lista lista) {

        Intent i = new Intent(this, VistaLista.class);
        Bundle b = new Bundle();
        b.putParcelable("lista", lista);
        i.putExtras(b);
        startActivity(i);

    }

    @Override
    public void mostrarConfirmarBorrarLista(Lista lista) {

        DialogoBorrarLista fragmentBorrar = DialogoBorrarLista.newInstance(lista);
        fragmentBorrar.show(getSupportFragmentManager(), "Dialogo borrar");
    }


    @Override
    public void onBorrarPossitiveButtonClick(Lista l) {

        presentador.onDeleteLista(l);
    }

    @Override
    public void onBorrarNegativeButtonClick() {

    }
}