package com.example.mlagunas.hackaton_app.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mlagunas.hackaton_app.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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



    public void mostrarDetalle(String imagen,String descripcion, String nombre, String color, String raza) {


        ImageButton img = (ImageButton)getView().findViewById(R.id.imageAnimal);

        Picasso.with(img.getContext()).load("http:"+imagen).into(img);


        TextView txtTituloDescripcion = (TextView)getView().findViewById(R.id.titleDescription);
        txtTituloDescripcion.setText(nombre);

        TextView txtDescripcion = (TextView)getView().findViewById(R.id.textDescription);
        txtDescripcion.setText(descripcion);

        TextView txtColor = (TextView)getView().findViewById(R.id.textColor);
        txtColor.setText(color);

        TextView txtRaza = (TextView)getView().findViewById(R.id.textRaza);
        txtRaza.setText(raza);

        Button btnSend = (Button) getView().findViewById(R.id.btnSend);

    }
}
