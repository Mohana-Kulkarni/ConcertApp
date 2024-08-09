package com.example.concertticketingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.R;
import com.example.concertticketingapp.holder.EventDetailsHolder;
import com.example.concertticketingapp.holder.ImageCardHolder;

import java.util.List;

public class ImageCardAdapter extends RecyclerView.Adapter<ImageCardHolder> {
    Context context;
    List<String> imageUrls;

    public ImageCardAdapter(Context context, List<String> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }
    @NonNull
    @Override
    public ImageCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.image_card, parent, false);
        return new ImageCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageCardHolder holder, int position) {
        String url = imageUrls.get(position);
        holder.setImage(url);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }
}
