package com.example.concertticketingapp.integration;

import com.example.concertticketingapp.model.Event;
import com.example.concertticketingapp.model.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface iConcertAppAPI {
    @GET("/events/id")
    Call<Event> getEventById(@Query("id") String id);

    @GET("/events/city")
    Call<List<Event>> getEventByCity(@Query("city") String city);

    @GET("/events/venue")
    Call<Event> getEventByVenue(@Query("venue") String venue);

    @GET("/places/all")
    Call<List<Place>> getPlaces();



}
