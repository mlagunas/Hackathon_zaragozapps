package com.example.mlagunas.hackaton_app.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ListView;


import com.example.mlagunas.hackaton_app.Adapter.AdapterAnimales;
import com.example.mlagunas.hackaton_app.Objects.Animal;
import com.example.mlagunas.hackaton_app.Objects.AnimalRequest;
import com.example.mlagunas.hackaton_app.Interfaces.PetService;
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
 * Created by mlagunas on 11/07/15.
 */
public class ListFragment extends Fragment {

    private ListView lstListado;
    private ArrayList<Animal> data;
    private ListListener listener;
    private Gson gson;
    AdapterAnimales a;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        gson = new Gson();
        data = new ArrayList<>();
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    private void restService(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://iescities.com:443/IESCities/api")
                .build();
        PetService service = restAdapter.create(PetService.class);

        String query = "select * from result";
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
                }catch (IOException e) {
                    e.printStackTrace();

                }
                String resultado = sb.toString();
                resultado = resultado.replace("{}", "null");
                AnimalRequest animalRequest = gson.fromJson(resultado, AnimalRequest.class); //  <--- Esto es el resultado final
                Log.d("RESULT",animalRequest.getRows().toString());
                data = animalRequest.getRows();
                lstListado.refreshDrawableState();
                a.notifyDataSetChanged();
            }


            @Override
            public void failure(RetrofitError error) {
                Log.v("TAG", "Error: " + error.getMessage());

            }
        });
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        restService();
        lstListado = (ListView)getView().findViewById(R.id.list_animales);
        a = new AdapterAnimales(this, data);
        lstListado.setAdapter(a);

        lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener != null) {
                    listener.onAnimalSelected(
                            (Animal) lstListado.getAdapter().getItem(pos));
                }
            }
        });
    }

    public interface ListListener {
        void onAnimalSelected(Animal a);
    }

    public void setAnimalListener(ListListener listener) {
        this.listener=listener;
    }

}
