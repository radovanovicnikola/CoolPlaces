package com.example.coolplaces;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

//singleton global class; must use init before use
public class ToolBarController {
    private Toolbar toolbar;
    private ViewGroup actionBar;
    private Context ctx;
    private View selectedMenuItemV;
    private static ToolBarController instance=null;
    private FragmentManager fragmentManager;
    private AllMenuFragments menuFragments;

    private ToolBarController(AppCompatActivity activity, Toolbar toolbar){
        this.ctx = activity.getApplicationContext();
        this.toolbar = toolbar;
        actionBar = toolbar.findViewById(R.id.menu_action_bar);
        fragmentManager = activity.getSupportFragmentManager();
        menuFragments = new AllMenuFragments();
        setItemsEvenents();
        initSelect();
    }

    public static ToolBarController init(AppCompatActivity activity, Toolbar toolbar){
        instance = new ToolBarController(activity,toolbar);
        return instance;
    }

    public static ToolBarController getInstance(){
        return instance;
    }

    public AllMenuFragments getMenuFragments() {
        return menuFragments;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setContext(Context ctx){
        this.ctx = ctx;
    }

    private void setItemsEvenents(){
        actionBar.findViewById(R.id.menu_item_friends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(view);
                Toast.makeText(ToolBarController.this.ctx,"Friends",Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.activity_content,menuFragments.getFriends());
                ft.commit();
            }
        });
        actionBar.findViewById(R.id.menu_item_messages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(view);
                Toast.makeText(ToolBarController.this.ctx,"Messages",Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.activity_content,menuFragments.getMessages());
                ft.commit();
            }
        });
        actionBar.findViewById(R.id.menu_item_map_pin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(view);
                Toast.makeText(ToolBarController.this.ctx,"Map",Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.activity_content,menuFragments.getMap());
                ft.commit();
            }
        });
        actionBar.findViewById(R.id.menu_item_favorites).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(view);
                Toast.makeText(ToolBarController.this.ctx,"Favorites",Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.activity_content,menuFragments.getFavorites());
                ft.commit();
            }
        });
        actionBar.findViewById(R.id.menu_item_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(view);
                Toast.makeText(ToolBarController.this.ctx,"Notification",Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.activity_content,menuFragments.getNotification());
                ft.commit();
            }
        });
        actionBar.findViewById(R.id.menu_item_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(view);
                Toast.makeText(ToolBarController.this.ctx,"Profile",Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.activity_content,menuFragments.getProfile());
                ft.commit();
            }
        });
    }
    private void initSelect(){
        //select first item as init state
        selectedMenuItemV = actionBar.findViewById(R.id.menu_item_friends);
        selectedMenuItemV.setBackgroundResource(R.color.menuItemSelected);
        //selected item is not clicable
        selectedMenuItemV.setClickable(false);

        //set default fragment
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.activity_content,menuFragments.getFriends());
        ft.commit();
    }
    private void unselect(){
        selectedMenuItemV.setBackgroundResource(R.color.menuItemNotSelected);
        //make it clickable
        selectedMenuItemV.setClickable(true);
    }
    public void selectItem(View item){
        unselect();
        selectedMenuItemV = item;
        item.setClickable(false);
        item.setBackgroundResource(R.color.menuItemSelected);
    }


}
