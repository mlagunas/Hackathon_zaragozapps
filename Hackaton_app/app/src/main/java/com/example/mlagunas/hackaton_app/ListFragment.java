package com.example.mlagunas.hackaton_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * Created by mlagunas on 11/07/15.
 */
public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        ListView lstListado = (ListView) getView().findViewById(R.id.list_animales);


        return inflater.inflate(R.layout.fragment_list, container, false);
    }
    

}
