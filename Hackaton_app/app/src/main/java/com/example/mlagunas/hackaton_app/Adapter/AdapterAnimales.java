package com.example.mlagunas.hackaton_app.Adapter;

import android.app.Activity;
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

    public View getView(int position, View convertView, ViewGroup parent) {

        if(data!=null && position<data.size()) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_animales, null);

            TextView lblDe = (TextView) item.findViewById(R.id.lblnombre);
            lblDe.setText(data.get(position).getNombre());

            TextView lblAsunto = (TextView) item.findViewById(R.id.lblraza);
            lblAsunto.setText(data.get(position).getRaza());

            return (item);
        }

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

    public ArrayList<Animal> getFilteredResults(CharSequence constraint){
        ArrayList<Animal> result = new ArrayList<Animal>();
        if (constraint == null){
            return dataInit;
        }
        else{
            data = new ArrayList<Animal>();
            for(Animal a: dataInit) {
                    if (a.getEspecie().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        data.add(a);
                    }
            }
            return data;
        }
    }

    public void setData(ArrayList<Animal> d){
        this.data = d;
        super.
        notifyDataSetChanged();
    }
}
