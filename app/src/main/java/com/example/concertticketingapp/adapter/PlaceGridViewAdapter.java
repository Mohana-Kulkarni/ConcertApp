package com.example.concertticketingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.concertticketingapp.R;
import com.example.concertticketingapp.model.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceGridViewAdapter extends ArrayAdapter<Place> {
    private OnItemClickListner onItemClickListner;
    public PlaceGridViewAdapter(@NonNull Context context, @NonNull List<Place> objects, OnItemClickListner onItemClickListner) {
        super(context, 0, objects);
        this.onItemClickListner = onItemClickListner;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listPlaceView = convertView;
        if (listPlaceView == null) {
            listPlaceView = LayoutInflater.from(getContext()).inflate(R.layout.city_name, parent,false);
        }
        Place place = getItem(position);
        TextView cityName = listPlaceView.findViewById(R.id.cityName);
        cityName.setText(place.getCity());

        listPlaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListner != null) {
                    onItemClickListner.onItemClick(place.getCity());
                }
            }
        });
        return  listPlaceView;
    }

    public interface OnItemClickListner {
        void onItemClick(String cityName);
    }
}
