package com.example.coolplaces;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

//singleton global class; must use init before use
public class ToolBarController {
    private Toolbar toolbar;
    private ViewGroup actionBar;
    private Context ctx;
    private View selectedMenuItemV;
    private static ToolBarController instance=null;
    //private

    private ToolBarController(Context ctx, Toolbar toolbar){
        this.ctx = ctx;
        this.toolbar = toolbar;
        actionBar = toolbar.findViewById(R.id.menu_action_bar);

        setItemsEvenents();
        initSelect();
    }

    public static ToolBarController init(Context ctx, Toolbar toolbar){
        instance = new ToolBarController(ctx,toolbar);
        return instance;
    }

    public static ToolBarController getInstance(){
        return instance;
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
                Intent intent = new Intent(ctx,ActivityFriends.class);
                ctx.startActivity(intent);
            }
        });
        actionBar.findViewById(R.id.menu_item_messages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(view);
                Toast.makeText(ToolBarController.this.ctx,"Messages",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ctx,ActivityMessages.class);
                ctx.startActivity(intent);
            }
        });
        actionBar.findViewById(R.id.menu_item_map_pin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(view);
                Toast.makeText(ToolBarController.this.ctx,"Map",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ctx,ActivityMap.class);
                ctx.startActivity(intent);
            }
        });
        actionBar.findViewById(R.id.menu_item_favorites).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(view);
                Toast.makeText(ToolBarController.this.ctx,"Favorites",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ctx,ActivityFavorites.class);
                ctx.startActivity(intent);
            }
        });
        actionBar.findViewById(R.id.menu_item_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(view);
                Toast.makeText(ToolBarController.this.ctx,"Notification",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ctx,ActivityNotification.class);
                ctx.startActivity(intent);
            }
        });
        actionBar.findViewById(R.id.menu_item_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(view);
                Toast.makeText(ToolBarController.this.ctx,"Profile",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ctx,ActivityProfile.class);
                ctx.startActivity(intent);
            }
        });
    }
    private void initSelect(){
        //select first item as init state
        selectedMenuItemV = actionBar.findViewById(R.id.menu_item_friends);
        selectedMenuItemV.setBackgroundResource(R.color.menuItemSelected);
        //selected item is not clicable
        selectedMenuItemV.setClickable(false);
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
