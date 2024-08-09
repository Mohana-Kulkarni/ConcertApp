package com.example.concertticketingapp.holder;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.concertticketingapp.R;

public class ImageCardHolder extends RecyclerView.ViewHolder {
    ImageView image;
    public ImageCardHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
    }

    public void setImage(String imageUrl) {
        Glide.with(itemView)
                .load(imageUrl)
                .apply(new RequestOptions().transform(new RoundedCorners(15)))
                .placeholder(R.drawable.placeholder) // Placeholder image
                .error(R.drawable.error) // Error image
                .into(image);
    }
}
