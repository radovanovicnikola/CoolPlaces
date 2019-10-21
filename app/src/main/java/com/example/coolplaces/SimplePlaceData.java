package com.example.coolplaces;

import android.graphics.Bitmap;

import java.util.Date;

public class SimplePlaceData {
    private String name;
    private String description;
    private Bitmap picture;

    public Bitmap getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
