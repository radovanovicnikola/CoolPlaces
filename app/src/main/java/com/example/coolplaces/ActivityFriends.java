package com.example.coolplaces;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityFriends extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        Toolbar toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
        ToolBarController.init(this.getApplicationContext(),toolbar);

        ToolBarController.getInstance().setContext(this);

        SimpleUserData simpleUserData = new SimpleUserData(BitmapFactory.decodeResource(getResources(),R.drawable.img_profile_example),"Tamara","Jovanovic");
        ArrayList<SimpleUserData> listUsers = new ArrayList<SimpleUserData>();
        for(int i=0;i<5;i++) {
            listUsers.add(new SimpleUserData(simpleUserData));
        }
        AdapterFriends af = new AdapterFriends(this,listUsers);
        GridView viewUsers = findViewById(R.id.grid);
        viewUsers.setAdapter(af);
    }
}
