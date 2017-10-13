package com.joseaguilar.navegationdrawer.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.joseaguilar.navegationdrawer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class P1_FragmentDos extends Fragment {


    public static P1_FragmentDos newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt("argsInstance", instance);
        P1_FragmentDos firstFragment = new P1_FragmentDos();
        firstFragment.setArguments(args);
        return firstFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // En caso queramos hacer uso de los elemntos del layout .xml del fragmento se debera de realizar de esta forma
        //Paso 1 : crear View
        View view =inflater.inflate(R.layout.fragment_p1__fragment_dos, container, false);
        //Paso 2: anexar elemento con view.findViewById

        return view;
    }

}
