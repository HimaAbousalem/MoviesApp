package com.example.user.moviesapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesFragment extends Fragment {
    private ArrayAdapter<String> setImagesPostersAdapter;
    public MoviesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        int dummyImages[] ={R.drawable.first,R.drawable.second,R.drawable.third,R.drawable.fourth
        ,R.drawable.fifth,R.drawable.sixth,R.drawable.seventh,R.drawable.eighth,R.drawable.tenth,R.drawable.Avengers
        ,R.drawable.Hitman,R.drawable.TheWolverine};
        List<Integer> postersDummyItems = new ArrayList<>();
        for(int i=0;i<postersDummyItems.size();i++){
            postersDummyItems.add(dummyImages[i]);
        }
        setImagesPostersAdapter =new ArrayAdapter<String>(getActivity(),
                R.layout.grid_item_posters,
                R.id.grid_item_posters_imageview,
                postersDummyItems);
        final GridView gridView = (GridView)rootView.findViewById(R.id.grid_view_Posters);
        gridView.setAdapter(setImagesPostersAdapter);
        return rootView;
    }
}
