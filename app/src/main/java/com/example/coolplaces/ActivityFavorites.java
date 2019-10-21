package com.example.coolplaces;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityFavorites extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        setSupportActionBar(ToolBarController.getInstance().getToolbar());

        ToolBarController.getInstance().setContext(this);
    }
}