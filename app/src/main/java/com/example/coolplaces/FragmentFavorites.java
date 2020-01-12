package com.example.coolplaces;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentFavorites extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_favorites,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        SimplePlaceData simplePlaceData = new SimplePlaceData("Cele kula","Cele kula u Nisu.", BitmapFactory.decodeResource(getResources(),R.drawable.img_place_example));
        ArrayList<SimplePlaceData> listPlaces = new ArrayList<SimplePlaceData>();
        for(int i=0;i<5;i++) {
            listPlaces.add(new SimplePlaceData(simplePlaceData));
        }

        AdapterPlaces am = new AdapterPlaces(getActivity(),listPlaces);
        ListView viewPlaces = view.findViewById(R.id.list);
        viewPlaces.setAdapter(am);
    }
}
