package com.example.mlagunas.hackaton_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mlagunas on 11/07/15.
 */
public class InformationFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_information, container, false);
    }

    public void mostrarDetalle(String texto) {
        TextView txtTituloDescripcion =
                (TextView)getView().findViewById(R.id.titleDescription);

        txtTituloDescripcion.setText("Descripción");
        TextView txtDescripcion =
                (TextView)getView().findViewById(R.id.textDescription);

        txtDescripcion.setText(texto);
    }
}
