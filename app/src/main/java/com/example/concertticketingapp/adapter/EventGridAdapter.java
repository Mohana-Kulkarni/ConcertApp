package com.example.concertticketingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.ActivityEventDetails;
import com.example.concertticketingapp.R;
import com.example.concertticketingapp.holder.EventCardHolder;
import com.example.concertticketingapp.model.Event;

import java.util.List;

public class EventGridAdapter extends RecyclerView.Adapter<EventCardHolder> {
    private final Context context;
    private final List<Event> events;
    private final String city;


    public EventGridAdapter(Context context, List<Event> events, String city) {
        this.context = context;
        this.events = events;
        this.city = city;
    }

    @NonNull
    @Override
    public EventCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.price_event_layout, parent, false);
        return new EventCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventCardHolder holder, int position) {
        Event event = events.get(position);
        holder.setEventData(event.getImageUrls().get(0),
                event.getName(), event.getVenueId().getName(),
                event.getDateAndTime(),
                event.getCategoryList(),
                event.getTiers().get(0).getPrice(),
                event.getId());

        holder.itemView.setOnClickListener(v -> {
            System.out.println(event.getId() + ":" + city);
            Intent intent = new Intent(context, ActivityEventDetails.class);
            intent.putExtra("EVENT_ID", event.getId());
            intent.putExtra("cityName", city);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

}
