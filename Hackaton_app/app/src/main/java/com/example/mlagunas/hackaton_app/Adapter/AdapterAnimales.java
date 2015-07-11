package com.example.mlagunas.hackaton_app.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mlagunas.hackaton_app.Objects.Animal;
import com.example.mlagunas.hackaton_app.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mlagunas on 11/07/15.
 */
public class AdapterAnimales extends ArrayAdapter<Animal> {

    private Activity context;
    private ArrayList<Animal> data;

    public AdapterAnimales(Fragment context, ArrayList<Animal> data) {

        super(context.getActivity(), R.layout.list_animales, data);
        this.data = data;
        this.context = context.getActivity();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.list_animales, null);

        TextView lblDe = (TextView) item.findViewById(R.id.lblnombre);
        lblDe.setText(data.get(position).getNombre());

        TextView lblAsunto = (TextView) item.findViewById(R.id.lblraza);
        lblAsunto.setText(data.get(position).getRaza());

        return (item);
    }

    public void setData(ArrayList<Animal> d){
        this.data = d;
        super.
        notifyDataSetChanged();
        Log.d("LONGITUDadapter", data.size() + " ");
        Log.d("COLORs", data.get(10).getColor());




    }
}
