package com.example.mlagunas.hackaton_app.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.mlagunas.hackaton_app.Fragments.InformationFragment;
import com.example.mlagunas.hackaton_app.R;

/**
 * Created by mlagunas on 11/07/15.
 */
public class InfoActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_informacion);


        InformationFragment info =
                (InformationFragment)getSupportFragmentManager()
                        .findFragmentById(R.id.Frg_info);


        info.mostrarDetalle(getIntent().getStringExtra("foto"),getIntent().getStringExtra("descripcion"),getIntent().getStringExtra("nombre"),getIntent().getStringExtra("color"),getIntent().getStringExtra("raza"));
    }
}
