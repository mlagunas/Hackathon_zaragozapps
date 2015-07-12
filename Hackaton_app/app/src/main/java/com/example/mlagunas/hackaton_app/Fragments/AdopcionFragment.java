package com.example.mlagunas.hackaton_app.Fragments;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;


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
public class AdopcionFragment extends Fragment {

    private Gson gson;
    private ArrayList<String> especies;
    private ArrayList<Animal> a;
    ArrayAdapter<String> adapter;
    ListView listView;
    private ListListener listener;
    ProgressBar pb;

    public AdopcionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        gson = new Gson();
        return inflater.inflate(R.layout.fragment_adopcion, container, false);
    }


    private void restService(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://iescities.com:443/IESCities/api")
                .build();
        PetService service = restAdapter.create(PetService.class);

        String query = "select distinct especie from result";
       ;
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

                String resultado = sb.toString().replace("{}", "null");
                AnimalRequest animalRequest = gson.fromJson(resultado, AnimalRequest.class); //  <--- Esto es el resultado final
                a = animalRequest.getRows();
                Log.d("SUCCESS","");
                pb.setVisibility(View.GONE);
                createAdapter();

            }
            @Override
            public void failure(RetrofitError error) {
                Log.v("TAG", "Error: " + error.getMessage());

            }
        });
    }

    public void createAdapter(){
        for (Animal animal:a){
            adapter.add(animal.getEspecie());
        }
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        especies = new ArrayList<String>();
        restService();
        adapter = new ArrayAdapter<String>(this.getActivity(),R.layout.list_dataset,R.id.lblEspecie,especies);

        pb = (ProgressBar) getView().findViewById(R.id.marker_progress);

        listView = (ListView) getView().findViewById(R.id.list_dataset);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener != null) {
                    Log.d("CLICK", (String) listView.getAdapter().getItem(pos));
                    listener.onEspecieSelected((
                            (String) listView.getAdapter().getItem(pos)));
                }
            }
        });
    }

    public interface ListListener {
        void onEspecieSelected(String s);
    }

    public void setEspecieListener(ListListener listener) {
        this.listener=listener;
    }

}
