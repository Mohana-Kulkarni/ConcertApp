package com.example.concertticketingapp.holder;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.concertticketingapp.R;
import com.example.concertticketingapp.adapter.CategoryGridViewAdapter;

import java.util.List;

public class EventCardHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView eventName, eventVenue, eventDate, eventPrice, eventId;
    GridView categoryGrid;
    public EventCardHolder(@NonNull View itemView) {
        super(itemView);
        eventId = itemView.findViewById(R.id.event_id);
        image = itemView.findViewById(R.id.event_image);
        eventName = itemView.findViewById(R.id.event_name);
        eventVenue = itemView.findViewById(R.id.event_venue);
        eventDate = itemView.findViewById(R.id.event_date);
        categoryGrid = itemView.findViewById(R.id.category_grid);
        eventPrice = itemView.findViewById(R.id.event_price);
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

    public void setCategoryGird(List<String> categories) {
        CategoryGridViewAdapter adapter = new CategoryGridViewAdapter(this.categoryGrid.getContext(), categories);
        this.categoryGrid.setAdapter(adapter);
    }

    public void setEventPrice(String eventPrice) {
        this.eventPrice.setText(eventPrice);
    }

    public void setEventId(String eventId) {
        this.eventId.setText(eventId);
    }

    public void setEventData(String imageUrl, String eventName, String eventVenue, String eventDate, List<String> categories, int eventPrice, String eventId) {
        setImage(imageUrl);
        setEventId(eventId);
        setEventName(eventName);
        setEventVenue(eventVenue);
        setCategoryGird(categories);
        setEventDate(eventDate);
        setEventPrice("Rs. " + eventPrice);
    }


}
