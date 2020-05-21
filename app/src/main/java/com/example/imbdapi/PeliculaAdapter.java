package com.example.imbdapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PeliculaAdapter extends ArrayAdapter<Pelicula> {

    public PeliculaAdapter(@NonNull Context context, @NonNull ArrayList<Pelicula> movies) {
        super(context, 0, movies);
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Pelicula pelicula = getItem(position);
        Context context = parent.getContext();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pelicula, parent, false);
        }



        ImageView posterlis=convertView.findViewById(R.id.idPoster);
        TextView titulolist=convertView.findViewById(R.id.idTitulo);
        TextView descriplist=convertView.findViewById(R.id.idDescripcion);

        Picasso.with(context).load(pelicula.getImagen()).into(posterlis);
        titulolist.setText(pelicula.getTitulo());
        descriplist.setText(String.valueOf(pelicula.getDescripcion()));


        return convertView;
    }

}
