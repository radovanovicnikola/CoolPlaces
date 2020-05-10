package com.example.coolplaces;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentFriends extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_friends,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        UserData simpleUserData = new UserData(BitmapFactory.decodeResource(getResources(),R.drawable.img_profile_example),"tamaraJo","Tamara","Jovanovic","066321231","tamarojo@gmail.com");
        ArrayList<UserData> listUsers = new ArrayList<UserData>();
        for(int i=0;i<5;i++) {
            listUsers.add(new UserData(simpleUserData));
        }
        AdapterFriends af = new AdapterFriends(getActivity(),listUsers);
        GridView viewUsers = view.findViewById(R.id.grid);
        viewUsers.setAdapter(af);
    }
}
