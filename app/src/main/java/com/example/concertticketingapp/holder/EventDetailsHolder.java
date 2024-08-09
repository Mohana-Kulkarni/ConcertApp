package com.example.concertticketingapp.holder;

import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.concertticketingapp.R;
import com.example.concertticketingapp.adapter.ImageCardAdapter;

import java.util.List;

public class EventDetailsHolder extends RecyclerView.ViewHolder {

    RecyclerView imagesRecyclerView;

    CardView interestedBtn;

    TextView interestedCount, eventDate, eventTime, eventDuration, eventCategories, eventVenue, eventDescription;

    RecyclerView artistRecycler, tiersRecycler;

    int count = 0;

    public EventDetailsHolder(@NonNull View itemView) {
        super(itemView);
        imagesRecyclerView = itemView.findViewById(R.id.event_images);
        interestedBtn = itemView.findViewById(R.id.interested);
        interestedCount = itemView.findViewById(R.id.interested_count);
        eventDate = itemView.findViewById(R.id.event_date);
        eventTime = itemView.findViewById(R.id.event_time);
        eventDuration = itemView.findViewById(R.id.event_duration);
        eventCategories = itemView.findViewById(R.id.event_category);
        eventVenue = itemView.findViewById(R.id.event_venue);
        eventDescription = itemView.findViewById(R.id.event_description);
    }

    public void setImages(List<String> images) {
        ImageCardAdapter cardAdapter = new ImageCardAdapter(itemView.getContext(), images);
        imagesRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        imagesRecyclerView.setAdapter(cardAdapter);
    }

    public void setInterestedBtn(CardView interestedBtn) {
        this.interestedBtn = interestedBtn;
    }

    public void setInterestedCount(TextView interestedCount) {
        this.interestedCount = interestedCount;
    }

    public void setEventDate(String eventDate) {
        this.eventDate.setText(eventDate);
    }

    public void setEventTime(String eventTime) {
        this.eventTime.setText(eventTime);
    }

    public void setEventDuration(String eventDuration) {
        this.eventDuration.setText(eventDuration);
    }

    public void setEventCategories(List<String> eventCategories) {
        String categories = "";
        for (String category: eventCategories) {
            categories += (category + ",");
        }
        categories = categories.substring(0, categories.length() - 1);
        this.eventCategories.setText(categories);
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue.setText(eventVenue);
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription.setText(eventDescription);
    }

    public void setArtistRecycler(RecyclerView artistRecycler) {
        this.artistRecycler = artistRecycler;
    }

    public void setTiersRecycler(RecyclerView tiersRecycler) {
        this.tiersRecycler = tiersRecycler;
    }
}
