package com.example.coolplaces;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Date;

public class SimplePlaceData {
    private String name;
    private String description;
    private Bitmap picture;

    public SimplePlaceData(String name, String description, Bitmap picture){
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public SimplePlaceData(SimplePlaceData simplePlaceData){
        this.name = simplePlaceData.name;
        this.description = simplePlaceData.description;
        this.picture = simplePlaceData.picture;
    }


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
