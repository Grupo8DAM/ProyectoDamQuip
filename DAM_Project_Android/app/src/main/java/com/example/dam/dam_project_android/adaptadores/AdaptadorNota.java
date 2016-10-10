package com.example.dam.dam_project_android.adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.dam.dam_project_android.R;
import com.example.dam.dam_project_android.pojo.Nota;



public class AdaptadorNota extends CursorAdapter {

    public AdaptadorNota(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv = (TextView) view.findViewById(R.id.tvTituloNota);
        Nota n = Nota.getNota(cursor);
        tv.setText(n.getTitulo());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item, parent, false);
    }
}