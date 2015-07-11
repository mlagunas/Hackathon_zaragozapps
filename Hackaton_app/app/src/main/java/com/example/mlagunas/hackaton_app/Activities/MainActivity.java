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


    boolean mBound = false;

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
        boolean hayInfo =
                (getSupportFragmentManager().findFragmentById(R.id.Frg_info) != null);


        if(hayInfo) {
            ((InformationFragment)getSupportFragmentManager()
                    .findFragmentById(R.id.Frg_info)).mostrarDetalle(a.getNombre());
        }
        else {
            Intent i = new Intent(this, InfoActivity.class);
            i.putExtra("descripcion", a.getObservaciones());
            startActivity(i);

        }
    }
}
