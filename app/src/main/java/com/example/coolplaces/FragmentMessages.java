package com.example.coolplaces;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentMessages extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_messages,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        SimpleMessageData simpleMessageData = new SimpleMessageData("Test poruka.",BitmapFactory.decodeResource(getResources(),R.drawable.img_profile_example),new Date());
        ArrayList<SimpleMessageData> listMessages = new ArrayList<SimpleMessageData>();
        for(int i=0;i<5;i++) {
            listMessages.add(new SimpleMessageData(simpleMessageData));
        }

        AdapterMessages am = new AdapterMessages(getActivity(),listMessages);
        ListView viewMessages = view.findViewById(R.id.list);
        viewMessages.setAdapter(am);
    }
}
