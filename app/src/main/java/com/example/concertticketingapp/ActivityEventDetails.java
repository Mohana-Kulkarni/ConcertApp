package com.example.concertticketingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.concertticketingapp.adapter.ArtistCardAdapter;
import com.example.concertticketingapp.adapter.EventCardDetailsAdapter;
import com.example.concertticketingapp.adapter.ImageCardAdapter;
import com.example.concertticketingapp.databinding.ActivityEventDetailsBinding;
import com.example.concertticketingapp.holder.EventCardHolder;
import com.example.concertticketingapp.holder.EventDetailsHolder;
import com.example.concertticketingapp.integration.RetrofitClient;
import com.example.concertticketingapp.model.Artist;
import com.example.concertticketingapp.model.Event;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityEventDetails extends AppCompatActivity {

    CardView backBtn;
    String eventId;
    ActivityEventDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEventDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        eventId = getIntent().getStringExtra("EVENT_ID");
        System.out.println("In details : " + eventId);
        loadEventDetails(eventId);
        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToEventsActivity();
            }
        });
    }

    public void goToEventsActivity() {
        System.out.println("Clicked Back Button");
        startActivity(new Intent(ActivityEventDetails.this, Activity_Events.class));
        finish();
    }

    private void loadEventDetails(String eventId) {
        RetrofitClient.getRetrofitInstance().getAPI().getEventById(eventId).enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                Event event = response.body();
                if (event != null) {
                    displayEventDetails(event);
                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                Log.e("api", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void displayEventDetails(Event event) {
        String[] dateTime = event.getDateAndTime().split(" ");
        binding.eventDate.setText(dateTime[0]);
        binding.eventTime.setText(dateTime[1]);
        binding.eventDuration.setText(event.getEventDuration());
        binding.eventName.setText(event.getName());
        binding.eventVenue.setText(event.getVenueId().getName() + ", " + event.getVenueId().getAddress());
        String categories = String.join(", ", event.getCategoryList());
        binding.eventCategory.setText(categories);
        binding.eventDescription.setText(event.getDescription());

        // Load the image using Glide
        System.out.println("In events : " + event.getImageUrls());
        binding.imagesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ImageCardAdapter adapter = new ImageCardAdapter(this, event.getImageUrls());
        binding.imagesRecyclerView.setAdapter(adapter);

        List<Artist> artistList = event.getArtistList();
        System.out.println(artistList);

        ArtistCardAdapter cardAdapter = new ArtistCardAdapter(this, artistList);
        binding.artistRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.artistRecycler.setAdapter(cardAdapter);
    }

}
