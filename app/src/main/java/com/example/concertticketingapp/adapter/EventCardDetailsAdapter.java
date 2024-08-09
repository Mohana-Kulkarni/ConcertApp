package com.example.concertticketingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.R;
import com.example.concertticketingapp.holder.EventDetailsHolder;
import com.example.concertticketingapp.model.Event;

public class EventCardDetailsAdapter extends RecyclerView.Adapter<EventDetailsHolder> {

    Context context;
    Event event;

    public EventCardDetailsAdapter(Context context, Event event) {
        this.context = context;
        this.event = event;
    }

    @NonNull
    @Override
    public EventDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_event_details, parent, false);
        return new EventDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventDetailsHolder holder, int position) {
        String[] dateTime = event.getDateAndTime().split(" ");
        holder.setEventDate(dateTime[0]);
        holder.setEventTime(dateTime[1]);
        holder.setEventVenue(event.getVenueId().getName() + "," + event.getVenueId().getAddress());
        holder.setEventCategories(event.getCategoryList());
        holder.setEventDuration(event.getEventDuration());
        holder.setEventDescription(event.getDescription());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
