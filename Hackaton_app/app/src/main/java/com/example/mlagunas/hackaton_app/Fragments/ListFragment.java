package com.example.mlagunas.hackaton_app.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Handler;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.EditText;
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
public class ListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView lstListado;
    private ArrayList<Animal> data;
    private ListListener listener;
    private Gson gson;
    AdapterAnimales adapter;
    ProgressDialog p;
    private EditText inputSearch;
    private SwipeRefreshLayout swipeLayout;
    int count;
    String especie;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        gson = new Gson();
        data = new ArrayList<>();
        adapter = new AdapterAnimales(this,data);
        count = 10;
        especie = getArguments().getString("especie");



        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    private void restService(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://iescities.com:443/IESCities/api")
                .build();
        PetService service = restAdapter.create(PetService.class);

        String query = "select * from result where especie = '"+especie+"' limit "+count;

        count += 10;
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
                AnimalRequest animalRequest = gson.fromJson(resultado, AnimalRequest.class);
                Log.d("RESULT", animalRequest.getRows().toString());
                data = animalRequest.getRows();
                Log.d("LONGITUD", data.size()+" ");
                adapter.clear();
                for(Animal a: data){
                    adapter.add(a);
                }

                adapter.notifyDataSetChanged();
                p.dismiss();
                swipeLayout.setRefreshing(false);
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


        swipeLayout = (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        lstListado = (ListView)getView().findViewById(R.id.list_animales);
        adapter = new AdapterAnimales(this, data);
        lstListado.setAdapter(adapter);

        p = new ProgressDialog(this.getActivity());
        p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        p.setTitle("Loading");
        p.setMessage("Wait while loading...");
        p.show();

        lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener != null) {
                    listener.onAnimalSelected(
                            (Animal) lstListado.getAdapter().getItem(pos));
                }
            }
        });

        inputSearch = (EditText) this.getActivity().findViewById(R.id.editText);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

                adapter.getFilter().filter(arg0);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                restService();

            }
        }, 5000);
    }

    public interface ListListener {
        void onAnimalSelected(Animal a);
    }

    public void setAnimalListener(ListListener listener) {
        this.listener=listener;
    }

}
