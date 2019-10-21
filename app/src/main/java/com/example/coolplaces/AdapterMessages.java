package com.example.coolplaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterMessages extends BaseAdapter {
    private Context ctx;
    private List<SimpleMessageData> messageList;
    private LayoutInflater inflater;

    public AdapterMessages(Context ctx, List<SimpleMessageData> messageList){
        this.ctx=ctx;
        this.messageList = messageList;
        this.inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int i) {
        return messageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SimpleMessageData messageData = messageList.get(i);
        View viewItemMessage = inflater.inflate(R.layout.item_message_list,null);
        TextView txtMessage = viewItemMessage.findViewById(R.id.txtMessage);
        txtMessage.setText(messageData.getMsg());
        ImageView imgProfile = viewItemMessage.findViewById(R.id.imgProfile);
        imgProfile.setImageBitmap(messageData.getSenderPicture());
        TextView viewMsgDate = viewItemMessage.findViewById(R.id.txtTime);
        viewMsgDate.setText(UI_helper.timePass(messageData.getArrivedDate()));

        return viewItemMessage;
    }
}
