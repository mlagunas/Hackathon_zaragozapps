package com.example.mlagunas.hackaton_app.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mlagunas.hackaton_app.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DisplayMessageActivityFragment extends Fragment {

    public DisplayMessageActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display_message, container, false);

    }
}
