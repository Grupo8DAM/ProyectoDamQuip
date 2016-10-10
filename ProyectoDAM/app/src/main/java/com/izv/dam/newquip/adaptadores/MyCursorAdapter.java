package com.izv.dam.newquip.adaptadores;

/**
 * Created by Fernando on 04/10/2016.
 */

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.izv.dam.newquip.contrato.ContratoBaseDatos;

/**
 * Created by Fernando on 04/10/2016.
 */

public abstract class MyCursorAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>
                                    implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    private Cursor cursor;
    private boolean datosValidos;
    private int idColumn;
    private DataSetObserver dso;
    private ContentObserver co;

    public MyCursorAdapter(Context context, Cursor cursor) {

        this.context    = context;
        this.cursor     = cursor;
        datosValidos    = cursor != null;

        if ( datosValidos ) {

            idColumn = this.cursor.getColumnIndex("_id");
        }
        else
        {
            idColumn = -1;
        }

        //idColumn        = datosValidos ? this.cursor.getColumnIndex("_id") : -1;
        dso             = new NotifyingDataSetObserver();
        co              = new ChangeObserver();

        if (this.cursor != null) {

            this.cursor.registerDataSetObserver(dso);
            this.cursor.registerContentObserver(co);
        }

    }

    public Cursor getCursor() {

        return cursor;
    }

    @Override
    public int getItemCount() {

        if ( datosValidos && cursor != null ) {

            return cursor.getCount();
        }

        return 0;
    }

    @Override
    public long getItemId(int position) {

        if ( datosValidos && cursor != null && cursor.moveToPosition(position) ) {

            return cursor.getLong(idColumn);
        }

        return 0;
    }

    /*
    *    Indica si cada elemento en el conjunto de datos se puede
    *    representar con un identificador único de tipo Long.
    *
    * */
    @Override
    public void setHasStableIds(boolean hasStableIds) {

        super.setHasStableIds(true);
    }

    public abstract void onBindViewHolder(VH viewHolder, Cursor cursor);

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {

        /*if (!datosValidos) {

            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!cursor.moveToPosition(position)) {

            throw new IllegalStateException("couldn't move cursor to position " + position);
        }*/

        onBindViewHolder(viewHolder, cursor);
    }

    @Override
    public int getItemViewType(int position) {

        return this.getType(position);
    }

    public int getType( int position ){

        if (!datosValidos) {

            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!cursor.moveToPosition(position)) {

            throw new IllegalStateException("couldn't move cursor to position " + position);
        }

        return cursor.getInt(cursor.getColumnIndex(ContratoBaseDatos.Arbol.TIPO));

    }
    /**
     * Change the underlying cursor to a new cursor. If there is an existing cursor it will be
     * closed.
     */
    public void changeCursor(Cursor cursor) {

        Cursor old = swapCursor(cursor);

        if (old != null) {
            old.close();
        }
    }

    /**
     *
     *  Metodo utilizado para cambiar nuestro cursor por uno nuevo
     *  el antiguo se devuelve para que pueda ser cerrado
     */
    public Cursor swapCursor(Cursor newCursor) {

        if ( newCursor == cursor) {
            return null;
        }

        Cursor oldCursor = cursor;

        //Quitamos el escuchador del cambio a nuestro cursor
        if (oldCursor != null ) {

            if ( co != null ) oldCursor.unregisterContentObserver( co );
            if ( dso != null ) oldCursor.unregisterDataSetObserver(dso);
        }

        cursor = newCursor;

        if (cursor != null) {

            if ( dso != null ) cursor.registerDataSetObserver(dso);
            if ( co != null ) cursor.registerContentObserver(co);

            idColumn = newCursor.getColumnIndexOrThrow("_id");
            datosValidos = true;
            notifyDataSetChanged();

        }
        else
        {
            idColumn = -1;
            datosValidos = false;
            notifyDataSetChanged();
            //There is no notifyDataSetInvalidated() method in RecyclerView.Adapter
        }

        return oldCursor;
    }

    /*
            *   Nos permite agregar un escuchador a nuestro cursor para notificar
            *   cuando se haya realizado un cambio en los datos del mismo
            *   Eso lo utilizamos para que nuestro adaptador cambie la informacion
            *
            * */
    private class NotifyingDataSetObserver extends DataSetObserver {

        @Override
        public void onChanged() {

            super.onChanged();
            datosValidos = true;
            notifyDataSetChanged();
        }

        @Override
        public void onInvalidated() {

            super.onInvalidated();
            datosValidos = false;
            notifyDataSetChanged();
            //There is no notifyDataSetInvalidated() method in RecyclerView.Adapter
        }
    }

    private class ChangeObserver extends ContentObserver {

        public ChangeObserver() {
            super(new Handler());
        }

        @Override
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
        }
    }

}
