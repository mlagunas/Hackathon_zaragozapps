package com.example.mlagunas.hackaton_app.Fragments;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mlagunas.hackaton_app.Adapter.AdapterAnimales;
import com.example.mlagunas.hackaton_app.Interfaces.PetService;
import com.example.mlagunas.hackaton_app.Objects.Animal;
import com.example.mlagunas.hackaton_app.Objects.AnimalRequest;
import com.example.mlagunas.hackaton_app.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;

/**
 * A placeholder fragment containing a simple view.
 */
public class CreateActivityFragment extends Fragment {


    ProgressDialog p;

    private SwipeRefreshLayout swipeLayout;



    public CreateActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    private void restUpdateService(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://iescities.com:443/IESCities/api")
                .build();
        PetService service = restAdapter.create(PetService.class);

        String query = "insert into result(tamagno) values ()";


        TypedInput string = new TypedByteArray("text/plain",query.getBytes());
        service.listAnimals(string, new Callback<Response>() {
            public void success(Response result, Response response) {
                //Try to get response body
                BufferedReader reader = null;
                StringBuilder sb = new StringBuilder();
                try {

                    reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                    String line;

                    try {
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }

                p.dismiss();
                swipeLayout.setRefreshing(false);
            }
            @Override
            public void failure(RetrofitError error) {
                Log.v("TAG", "Error: " + error.getMessage());

            }
        });
    }
}
