package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nicky.everprotecttesting.R;


/**
 * Created by Rp on 8/30/2016.
 */
public class Fragment_Banner extends Fragment {

    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);



        return view;

    }
    }
