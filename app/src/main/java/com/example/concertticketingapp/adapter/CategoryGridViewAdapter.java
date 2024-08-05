package com.example.concertticketingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.concertticketingapp.R;
import com.example.concertticketingapp.model.Place;

import java.util.List;


public class CategoryGridViewAdapter extends ArrayAdapter<String> {
    public CategoryGridViewAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listCategoryView = convertView;
        if (listCategoryView == null) {
            listCategoryView = LayoutInflater.from(getContext()).inflate(R.layout.event_category_card, parent,false);
        }
        TextView categoryName = listCategoryView.findViewById(R.id.event_category);
        String category = getItem(position);
        categoryName.setText(category);

        return  listCategoryView;
    }
}
