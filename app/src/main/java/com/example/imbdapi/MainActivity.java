package com.example.imbdapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    EditText nombre;

    Button listar;
    String link = "https://imdb-api.com/API/Search/k_12O7OywY/", url;
    ArrayList<Pelicula> memetflix = new ArrayList<Pelicula>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        nombre=findViewById(R.id.idTitulo);

        listar=findViewById(R.id.idlista);

        listar.setEnabled(false);
        nombre.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    url=link.concat(nombre.getText().toString());
                    requestDatos();
                    listar.setEnabled(true);
                    return true;
                }
                return false;
            }
});
        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ListadoActivity.class);
                i.putParcelableArrayListExtra("cine", memetflix);
                startActivity(i);
                nombre.setText("");
            }
        });
    }

    public void requestDatos(){
        RequestQueue cola = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //dato.setText(response.toString());
                        memetflix.clear();
                        parserJson(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error en la conexion", Toast.LENGTH_LONG).show();
            }
        });
       /* {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
               // headers.put("Content-Type", "application/json");
                headers.put("X-Auth-Token", "df875ad8e5ac477cb91ca3687c170e6c");
                return headers;
            }
        };*/
        cola.add(jsonObjectRequest);
    }

    public void parserJson(JSONObject response){
        try {
            String cadena = "";
            JSONArray peliculas = response.getJSONArray("results");
            for (int i = 0 ; i<peliculas.length(); i++) {
                JSONObject com = peliculas.getJSONObject(i);
                String id = com.getString("id");
                String titulo = com.getString("title");
                String poster = com.getString("image");
                String descript = com.getString("description");
                cadena = cadena + id + "," + titulo + "," + descript + "\n";
                Pelicula co = new Pelicula(id,titulo,poster,descript);
                if(memetflix.contains(co)==false){
                memetflix.add(co);}
            }
            //Toast.makeText(getApplicationContext(),"Id = "+ cs.get(1).getId(), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
