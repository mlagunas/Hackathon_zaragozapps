package com.example.mlagunas.hackaton_app;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by mlagunas on 11/07/15.
 */
public class ListFragment extends Fragment {

    private ListView lstListado;
    private Animal[] data;
    private ListListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        lstListado = (ListView)getView().findViewById(R.id.list_animales);
        data = new Animal[2];
        data[0] = new Animal("","","","","","","ASDASFDFADAFDSFDSASFDFDS","",0,"perro","pimpampum","","","","");
        data[1] = new Animal("","","","","","","QWEQWEWQQWEQWEQEWEQWQEWQEWEQW","",0,"perra","bocadillodeatun","","","","");
        lstListado.setAdapter(new AdapterAnimales(this,data));


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
