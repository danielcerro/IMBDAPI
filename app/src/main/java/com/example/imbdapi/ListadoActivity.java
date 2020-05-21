package com.example.imbdapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    ArrayList<Pelicula> mve = new ArrayList<Pelicula>();
    ListView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        Intent i = getIntent();
        mve = i.getParcelableArrayListExtra("cine");
        listado = findViewById(R.id.lstpeliculas);
        if (mve !=null && mve.size()>0){
            PeliculaAdapter adapter = new PeliculaAdapter(this, mve);
            listado.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this,"No hay datos" , Toast.LENGTH_LONG).show();
        }
    }
}
