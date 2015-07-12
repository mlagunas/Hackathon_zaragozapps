package com.example.mlagunas.hackaton_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.mlagunas.hackaton_app.Fragments.InformationFragment;
import com.example.mlagunas.hackaton_app.R;

/**
 * Created by mlagunas on 11/07/15.
 */
public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_informacion);

        
        InformationFragment info =
                (InformationFragment)getSupportFragmentManager()
                        .findFragmentById(R.id.Frg_info);


        info.mostrarDetalle(getIntent().getStringExtra("foto"),getIntent().getStringExtra("descripcion"),
                getIntent().getStringExtra("nombre"),getIntent().getStringExtra("color"),
                getIntent().getStringExtra("raza"),getIntent().getStringExtra("tamanio"),getIntent().getStringExtra("edad"));
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
