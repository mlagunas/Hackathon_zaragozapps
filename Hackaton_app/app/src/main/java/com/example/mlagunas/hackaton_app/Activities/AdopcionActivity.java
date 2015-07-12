package com.example.mlagunas.hackaton_app.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mlagunas.hackaton_app.Fragments.AdopcionFragment;
import com.example.mlagunas.hackaton_app.Fragments.InformationFragment;
import com.example.mlagunas.hackaton_app.Objects.Animal;
import com.example.mlagunas.hackaton_app.R;

public class AdopcionActivity extends AppCompatActivity implements AdopcionFragment.ListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopcion);

        AdopcionFragment adopcion =
                (AdopcionFragment)getSupportFragmentManager()
                        .findFragmentById(R.id.fragment);

        adopcion.setEspecieListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adopcion, menu);
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
    public void onEspecieSelected(String s) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("especie", s);
        startActivity(i);

    }

}
