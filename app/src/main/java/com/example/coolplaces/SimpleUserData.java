package com.example.coolplaces;

import android.graphics.Bitmap;

public class SimpleUserData {
    private Bitmap profilePicture;
    private String firstName;
    private String lastName;

    public SimpleUserData(Bitmap profilePicture,String firstName,String lastName){
        this.profilePicture = profilePicture;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public SimpleUserData(SimpleUserData object){
        this.profilePicture = object.profilePicture;
        this.firstName = object.firstName;
        this.lastName = object.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Bitmap getProfilePicture() {
        return profilePicture;
    }
}
