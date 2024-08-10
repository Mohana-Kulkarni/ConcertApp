package com.example.concertticketingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.R;
import com.example.concertticketingapp.holder.ArtistCardHolder;
import com.example.concertticketingapp.holder.ImageCardHolder;
import com.example.concertticketingapp.model.Artist;

import java.util.List;

public class ArtistCardAdapter extends RecyclerView.Adapter<ArtistCardHolder> {
    Context context;
    List<Artist> artists;

    public ArtistCardAdapter(Context context, List<Artist> artists) {
        this.context = context;
        this.artists = artists;
    }
    @NonNull
    @Override
    public ArtistCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.artist_card, parent, false);
        return new ArtistCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistCardHolder holder, int position) {
        Artist artist = artists.get(position);
        System.out.println(getItemCount());
        holder.setArtistImg(artist.getProfileImg());
        holder.setArtistName(artist.getName());

    }

    @Override
    public int getItemCount() {
        return artists.size();
    }
}
