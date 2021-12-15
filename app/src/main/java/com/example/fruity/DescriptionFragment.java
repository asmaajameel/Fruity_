package com.example.fruity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DescriptionFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
   // private static final String ARG_PARAM2 = "param2";

    interface AddDesFruitListener{
        void addDes(String fruitDes);
    }

    AddDesFruitListener listener;
    private String mParam1;
    private String mParam2;

    public DescriptionFragment() {
        // Required empty public constructor
    }

    public static DescriptionFragment newInstance(String param1) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putString("description from FruitInfoActivity", param1);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_description, container, false);
        TextView dsTxt = (TextView) v.findViewById(R.id.description);
        dsTxt.setText(getArguments().getString("description from FruitInfoActivity"));
        //dsTxt.setText(this.getArguments().getString("fruitInfo"));


        return v;
    }
}