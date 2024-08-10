package com.example.concertticketingapp.holder;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.example.concertticketingapp.R;

public class ArtistCardHolder extends RecyclerView.ViewHolder {

    TextView artistName;
    ImageView artistImg;
    public ArtistCardHolder(@NonNull View itemView) {
        super(itemView);
        artistImg = itemView.findViewById(R.id.artist_img);
        artistName = itemView.findViewById(R.id.artist_name);
    }
    public void setArtistName(String artistName) {
        this.artistName.setText(artistName);
    }

    public void setArtistImg(String img) {
        Glide.with(itemView)
                .load(img)
                .apply(new RequestOptions().transform(new RoundedCorners(15)))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, com.bumptech.glide.request.target.Target<Drawable> target, boolean isFirstResource) {
                        Log.e("Glide", "Load failed", e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, com.bumptech.glide.request.target.Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .placeholder(R.drawable.placeholder) // Placeholder image
                .error(R.drawable.error) // Error image
                .into(artistImg);
    }

}
