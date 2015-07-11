package com.example.mlagunas.hackaton_app;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by mlagunas on 11/07/15.
 */
public class AdapterAnimales extends ArrayAdapter<Animal> {

    private Activity context;
    private Animal[] data;

    AdapterAnimales(Fragment context, Animal[] data) {

        super(context.getActivity(), R.layout.list_animales, data);
        this.data = data;
        this.context = context.getActivity();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.list_animales, null);

        TextView lblDe = (TextView) item.findViewById(R.id.lblnombre);
        lblDe.setText(data[position].getNombre());

        TextView lblAsunto = (TextView) item.findViewById(R.id.lblraza);
        lblAsunto.setText(data[position].getRaza());

        return (item);
    }
}
