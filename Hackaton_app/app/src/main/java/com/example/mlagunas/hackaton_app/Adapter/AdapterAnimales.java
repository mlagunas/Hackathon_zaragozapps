package com.example.mlagunas.hackaton_app.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Debug;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.mlagunas.hackaton_app.Objects.Animal;
import com.example.mlagunas.hackaton_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mlagunas on 11/07/15.
 */
public class AdapterAnimales extends ArrayAdapter<Animal> {

    private Activity context;
    private ArrayList<Animal> data,dataInit;

    public AdapterAnimales(Fragment context, ArrayList<Animal> d) {

        super(context.getActivity(), R.layout.list_animales, d);
        this.data = d;
        dataInit = d;
        this.context = context.getActivity();
    }

    @Override
    public int getCount(){
        return data.size()-1;
    }


    @Override
    public void add(Animal a){
        data.add(0,a);
        notifyDataSetChanged();
        dataInit = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(
                    R.layout.list_animales, null);
        }

            TextView lblDe = (TextView) convertView.findViewById(R.id.lblespecie);
            String especie = data.get(position).getEspecie();
            lblDe.setText(especie.charAt(0) + especie.substring(1, especie.length()).toLowerCase());

            TextView lblAsunto = (TextView) convertView.findViewById(R.id.lblraza);
            String raza = data.get(position).getRaza();
            lblAsunto.setText(raza.charAt(0) + raza.substring(1, raza.length()).toLowerCase());


        return convertView;

    }

    Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            ArrayList<Animal> tempList=new ArrayList<Animal>();

            //constraint is the result from text you want to filter against.
            //objects is your data set you will filter from
            if(constraint != null && dataInit!=null) {
                int length=dataInit.size();
                int i=0;
                while(i<length){

                    Animal item=dataInit.get(i);
                    //do whatever you wanna do here
                    //adding result set output array

                    if (item.getRaza().toLowerCase().startsWith((constraint.toString()).toLowerCase()))
                        tempList.add(item);
                    i++;
                }
                //following two lines is very important
                //as publish result can only take FilterResults objects
                filterResults.values = tempList;
                filterResults.count = tempList.size();
            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence contraint, FilterResults results) {
            data = (ArrayList<Animal>) results.values;
            Log.d("CUENTA",data.size()+" ");
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    };

    @Override
    public Filter getFilter() {
        return myFilter;
    }

}
