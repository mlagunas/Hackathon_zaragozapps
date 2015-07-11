package com.example.mlagunas.hackaton_app;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by mlagunas on 11/07/15.
 */
public class ListFragment extends Fragment {

    private ListView lstListado;
    private Animal[] data;

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
        data[0] = new Animal("","","","","","","","",0,"perro","pimpampum","","","","");
        data[1] = new Animal("","","","","","","","",0,"perra","bocadillodeatun","","","","");
        lstListado.setAdapter(new AdaptadorAnimales(this));
    }


    class AdaptadorAnimales extends ArrayAdapter<Animal>{

        Activity context;

        AdaptadorAnimales(Fragment context) {
            super(context.getActivity(), R.layout.list_animales, data);
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

}
