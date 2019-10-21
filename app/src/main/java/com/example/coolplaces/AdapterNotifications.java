package com.example.coolplaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterNotifications extends BaseAdapter {
    private Context ctx;
    private List<SimpleNotificationData> notificationList;
    private LayoutInflater inflater;

    public AdapterNotifications(Context ctx, List<SimpleNotificationData> notificationList){
        this.ctx=ctx;
        this.notificationList = notificationList;
        this.inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Object getItem(int i) {
        return notificationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SimpleNotificationData notificationData = notificationList.get(i);
        View viewItemNotification = inflater.inflate(R.layout.item_notification_list,null);
        TextView txtContent =viewItemNotification.findViewById(R.id.txt);
        txtContent.setText(notificationData.getContent());
        ImageView img =viewItemNotification.findViewById(R.id.imgProfile);
        img.setImageBitmap(notificationData.getPicture());
        TextView viewNotiDate =viewItemNotification.findViewById(R.id.txtTime);
        viewNotiDate.setText(UI_helper.timePass(notificationData.getArrivedDate()));

        return viewItemNotification;
    }
}
