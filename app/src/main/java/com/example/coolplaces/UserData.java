package com.example.coolplaces;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.ByteArrayOutputStream;

@IgnoreExtraProperties
public class UserData {


    public String email;
    public String userName;
    public String firstName;
    public String lastName;
    public String phoneNumber;

    public void setProfilePicture(String profilePicture) {
        try {
            byte [] encodeByte=Base64.decode(profilePicture,Base64.DEFAULT);
            this.profilePicture= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch(Exception e) {
            e.getMessage();
        }
    }

    private Bitmap profilePicture;
    public String city=null;
    public String sex=null;

    public String getProfilePicture(){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        profilePicture.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        return Base64.encodeToString(b,Base64.DEFAULT);
    }



    public UserData(){

    }
    public UserData(Bitmap profilePicture, String userName, String firstName, String lastName, String phone,String email){
        this.profilePicture = profilePicture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.phoneNumber = phone;
        this.email = email;
    }
    public UserData(UserData object){
        this.profilePicture = object.profilePicture;
        this.userName = userName;
        this.firstName = object.firstName;
        this.lastName = object.lastName;
        this.phoneNumber = object.phoneNumber;
        this.city = object.city;
        this.sex = object.sex;
    }
    @Exclude
    public String getFirstName() {
        return firstName;
    }

    @Exclude
    public String getLastName() {
        return lastName;
    }
    @Exclude
    public String getUserName() {
        return userName;
    }
    @Exclude
    public String getPhoneNumber() {
        return phoneNumber;
    }
    @Exclude
    public String getCity() {
        return city;
    }
    @Exclude
    public String getSex() {
        return sex;
    }
    @Exclude
    public Bitmap getProfilePictureBitmap()
    {
        return profilePicture;
    }
}
