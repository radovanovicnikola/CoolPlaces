package com.example.coolplaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterBluethoothPhone extends BaseAdapter {
    private Context ctx;
    private List<SimplePhoneInfo> phoneList;
    private LayoutInflater inflater;

    public AdapterBluethoothPhone(Context ctx, List<SimplePhoneInfo> phoneList){
        this.ctx=ctx;
        this.phoneList=phoneList;
        this.inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return phoneList.size();
    }

    @Override
    public Object getItem(int i) {
        return phoneList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SimplePhoneInfo phoneInfo = phoneList.get(i);
        View viewItemBluetoothPhone = inflater.inflate(R.layout.item_bluetooth_phone_list,null);
        TextView txtName = viewItemBluetoothPhone.findViewById(R.id.txtPhone);
        txtName.setText(phoneInfo.getPhoneName());
        
        return viewItemBluetoothPhone;
    }
}
