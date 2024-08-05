package com.example.concertticketingapp.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.concertticketingapp.R;

public class EventCardHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView eventName, eventVenue, eventDate;
    public EventCardHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.event_image);
        eventName = itemView.findViewById(R.id.event_name);
        eventVenue = itemView.findViewById(R.id.event_venue);
        eventDate = itemView.findViewById(R.id.event_date);
    }

    public void setImage(String imageUrl) {
        Glide.with(itemView)
                .load(imageUrl)
                .apply(new RequestOptions().transform(new RoundedCorners(15)))
                .placeholder(R.drawable.placeholder) // Placeholder image
                .error(R.drawable.error) // Error image
                .into(image);

    }

    public void setEventName(String eventName) {
        this.eventName.setText(eventName);
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue.setText(eventVenue);
    }

    public void setEventDate(String eventDate) {
        this.eventDate.setText(eventDate);
    }
}
