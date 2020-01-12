package com.example.coolplaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterPlaces extends BaseAdapter {
    private Context ctx;
    private List<SimplePlaceData> simplePlaceData;
    private LayoutInflater inflater;

    public AdapterPlaces(Context ctx, List<SimplePlaceData> simplePlaceData){
        this.ctx=ctx;
        this.simplePlaceData = simplePlaceData;
        this.inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return simplePlaceData.size();
    }

    @Override
    public Object getItem(int i) {
        return simplePlaceData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SimplePlaceData placeData = simplePlaceData.get(i);
        View viewPlaceItem = inflater.inflate(R.layout.item_places_list,null);
        TextView txtContent = viewPlaceItem.findViewById(R.id.txt);
        txtContent.setText(placeData.getDescription());
        ImageView img = viewPlaceItem.findViewById(R.id.imgProfile);
        img.setImageBitmap(placeData.getPicture());
        TextView viewTxtPlace = viewPlaceItem.findViewById(R.id.txtPlace);
        viewTxtPlace.setText(placeData.getName());

        return viewPlaceItem;
    }
}
