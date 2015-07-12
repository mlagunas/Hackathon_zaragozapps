package com.example.mlagunas.hackaton_app.Adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mlagunas.hackaton_app.R;

import java.util.ArrayList;

/**
 * Created by mlagunas on 12/07/15.
 */
public class AdapterEspecie extends ArrayAdapter<String> {
    ArrayList<String> data;
    private Activity context;

    public AdapterEspecie(Fragment context, ArrayList<String> d) {

        super(context.getActivity(), R.layout.list_animales, d);
        this.data = d;
        this.context = context.getActivity();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_dataset, null);

        TextView lblEspecie = (TextView)item.findViewById(R.id.lblespecie);
        lblEspecie.setText(data.get(position));


        return(item);
    }
}
