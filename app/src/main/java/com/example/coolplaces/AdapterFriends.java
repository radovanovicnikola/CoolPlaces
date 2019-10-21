package com.example.coolplaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterFriends extends BaseAdapter {
    private Context ctx;
    private List<SimpleUserData> userList;
    private LayoutInflater inflater;

    public AdapterFriends(Context ctx,List<SimpleUserData> userList){
        this.ctx=ctx;
        this.userList=userList;
        this.inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SimpleUserData userData = userList.get(i);
        View viewItemFriend = inflater.inflate(R.layout.item_friend_list,null);
        TextView txtName = viewItemFriend.findViewById(R.id.txtName);
        txtName.setText(userData.getFirstName()+" "+userData.getLastName());
        ImageView imgProfile = viewItemFriend.findViewById(R.id.imgProfile);
        imgProfile.setImageBitmap(userData.getProfilePicture());

        return viewItemFriend;
    }
}
