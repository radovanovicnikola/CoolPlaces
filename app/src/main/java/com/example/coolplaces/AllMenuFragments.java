package com.example.coolplaces;

import androidx.fragment.app.Fragment;

public class AllMenuFragments {
    private Fragment favorites;
    private Fragment friends;
    private Fragment map;
    private Fragment messages;
    private Fragment notification;
    private Fragment profile;

    AllMenuFragments(){
        favorites =  new FragmentFavorites();
        friends = new FragmentFriends();
        map = new FragmentMap();
        messages = new FragmentMessages();
        notification = new FragmentNotification();
        profile = new FragmentProfile();
    }

    public Fragment getFavorites() {
        return favorites;
    }

    public Fragment getFriends() {
        return friends;
    }

    public Fragment getMap() {
        return map;
    }

    public Fragment getMessages() {
        return messages;
    }

    public Fragment getNotification() {
        return notification;
    }

    public Fragment getProfile() {
        return profile;
    }
}
