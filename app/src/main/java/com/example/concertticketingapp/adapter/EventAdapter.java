package com.example.concertticketingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.R;
import com.example.concertticketingapp.holder.EventCardHolder;
import com.example.concertticketingapp.model.Event;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventCardHolder> {
    Context context;
    List<Event> eventList;

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.suggest_event_layout, parent, false);
        return new EventCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventCardHolder holder, int position) {
        Event event = eventList.get(position);
        holder.setEventId(event.getId());
        holder.setImage(event.getImageUrls().get(0));
        holder.setEventName(event.getName());
        holder.setEventDate(event.getDateAndTime());
        holder.setEventVenue(event.getVenueId().getName());
        holder.setCategoryGird(event.getCategoryList());
        holder.setEventPrice("Rs." + event.getTierList().get(0).getPrice());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
