package com.example.concertticketingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.concertticketingapp.R;

import java.util.List;

public class CategoryCardAdapter extends ArrayAdapter<String> {
    public CategoryCardAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listCategoryView = convertView;
        if (listCategoryView == null) {
            listCategoryView = LayoutInflater.from(getContext()).inflate(R.layout.event_category_card, parent,false);
        }
        ImageView categoryImg = listCategoryView.findViewById(R.id.category);
        Context context = getContext();
        String categoryName = getItem(position);
        String finalCategoryName = categoryName;
        if(categoryName.equals("Electronic/Dance")) {
            finalCategoryName ="Electronic";
        } else if (categoryName.equals("R&B/Soul")) {
            finalCategoryName="Soul";
        }
        int resId = context.getResources().getIdentifier(finalCategoryName.toLowerCase() + "_img", "drawable", context.getPackageName());
        categoryImg.setImageResource(resId);


        listCategoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListner != null) {
                    onItemClickListner.onItemClick(categoryName);
                }
            }
        });

        return  listCategoryView;
    }
}
