package com.example.coolplaces;

import android.graphics.Bitmap;

import java.util.Date;

public class SimpleMessageData {
    private String msg;
    private Bitmap senderPicture;
    private Date arrivedDate;

    public SimpleMessageData(String msg,Bitmap senderPicture,Date arrivedDate){
        this.msg = msg;
        this.senderPicture = senderPicture;
        this.arrivedDate = arrivedDate;
    }

    public SimpleMessageData(SimpleMessageData simpleMessageData){
        this.msg = simpleMessageData.msg;
        this.senderPicture = simpleMessageData.senderPicture;
        this.arrivedDate = simpleMessageData.arrivedDate;
    }

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
