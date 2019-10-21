package com.example.coolplaces;

import android.graphics.Bitmap;

import java.util.Date;

public class SimpleMessageData {
    private String msg;
    private Bitmap senderPicture;
    private Date arrivedDate;

    public Bitmap getSenderPicture() {
        return senderPicture;
    }

    public String getMsg() {
        return msg;
    }

    public Date getArrivedDate() {
        return arrivedDate;
    }
}
