package com.example.mlagunas.hackaton_app;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.mlagunas.hackaton_app.MyService.LocalBinder;

public class MainActivity extends AppCompatActivity implements ListFragment.ListListener {

    MyService mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListFragment frgListado
                =(ListFragment)getSupportFragmentManager()
                .findFragmentById(R.id.Frg_list);

        mService.download();
        frgListado.setAnimalListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalBinder binder = (LocalBinder) service;
            mService = binder.getService();
            mBound = true;
            mService.download();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            ;
            Log.d("RESULTADO",intent.getDataString());

        }

    };

    @Override
    public void onResume(){
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("webpage"));
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
