package com.example.concertticketingapp;

import com.example.concertticketingapp.model.Event;
import com.example.concertticketingapp.model.Place;

import java.util.List;

public interface FetchDataCallback {
    void onEventsFetched(List<Event> events);
    void onCitiesFetched(List<Place> cities);
    void onError(Throwable t);
}
