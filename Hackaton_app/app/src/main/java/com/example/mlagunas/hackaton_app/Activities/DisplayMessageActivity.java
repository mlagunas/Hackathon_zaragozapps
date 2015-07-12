package com.example.mlagunas.hackaton_app.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mlagunas.hackaton_app.R;

public class DisplayMessageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        EditText nombre = (EditText) findViewById(R.id.Name);
        //nombre.getText();

        EditText email = (EditText)findViewById(R.id.Email);
        //email.getText();

        EditText telefono = (EditText)findViewById(R.id.Phone);
        //telefono.getText();

        EditText mensaje = (EditText)findViewById(R.id.bodymessage);
        //mensaje.getText();

        Button btnSend = (Button)findViewById(R.id.btnSend);


    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Toast.makeText(getApplicationContext(), " Tu mensaje ha sido enviado :)", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_message, menu);
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
}
