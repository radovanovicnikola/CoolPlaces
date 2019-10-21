package com.example.coolplaces;

import android.graphics.Bitmap;

import java.util.Date;

public class SimpleNotificationData {
    private String content;
    private Bitmap picture;
    private Date arrivedDate;

    public String getContent() {
        return content;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public Date getArrivedDate() {
        return arrivedDate;
    }
}
