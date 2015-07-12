package com.example.mlagunas.hackaton_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.example.mlagunas.hackaton_app.Fragments.InformationFragment;
import com.example.mlagunas.hackaton_app.Fragments.ListFragment;
import com.example.mlagunas.hackaton_app.Objects.Animal;
import com.example.mlagunas.hackaton_app.R;

public class MainActivity extends AppCompatActivity implements ListFragment.ListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ListFragment frgListado
                =(ListFragment)getSupportFragmentManager()
                .findFragmentById(R.id.Frg_list);

        frgListado.setAnimalListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAnimalSelected(Animal a) {

        boolean hayInfo = (getSupportFragmentManager().findFragmentById(R.id.Frg_info) != null);


        if(hayInfo) {
            ((InformationFragment)getSupportFragmentManager()
                    .findFragmentById(R.id.Frg_info)).mostrarDetalle(a.getFoto(),a.getObservaciones(),a.getNombre(),a.getColor(),a.getRaza(),a.getTamanio(),a.getEdad());
        }
        else {
            Intent i = new Intent(this, InfoActivity.class);
            i.putExtra("foto", a.getFoto());
            i.putExtra("descripcion", a.getObservaciones());
            if (a.getNombre()==null || a.getNombre().equals("")){
                i.putExtra("nombre","Sin nombre");
            }else{
                i.putExtra("nombre",a.getNombre());
            }

            i.putExtra("color", a.getColor());
            i.putExtra("raza", a.getRaza());
            i.putExtra("tamanio",a.getTamanio());
            i.putExtra("edad",a.getEdad());
            startActivity(i);

        }
    }
}
