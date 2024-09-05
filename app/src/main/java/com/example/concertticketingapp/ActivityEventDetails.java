package com.example.concertticketingapp;

import static com.example.concertticketingapp.UtilityClass.goToEventsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.concertticketingapp.adapter.ArtistCardAdapter;
import com.example.concertticketingapp.adapter.EventCardDetailsAdapter;
import com.example.concertticketingapp.adapter.ImageCardAdapter;
import com.example.concertticketingapp.adapter.TierCardAdapter;
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

    CardView backBtn, addBtn, removeBtn;
    TextView ticketCount;
    String eventId;
    ActivityEventDetailsBinding binding;
    private int count = 0;
    private String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEventDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ActivityTracker.getInstance().setLastActivityName("ActivityEventDetails");

        getDataFromEvents();

        loadEventDetails(eventId);

        backBtn = binding.backBtn;
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToEventsActivity(ActivityEventDetails.this, null, city);
            }
        });


    }

    public void getDataFromEvents() {
        eventId = getIntent().getStringExtra("EVENT_ID");
        city = getIntent().getStringExtra("selectedCity");
    }


    private void loadEventDetails(String eventId) {
        RetrofitClient.getRetrofitConcertInstance().getAPI().getEventById(eventId).enqueue(new Callback<Event>() {
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

        //Artist Recycler connected
        List<Artist> artistList = event.getArtistList();
        System.out.println(artistList);

        ArtistCardAdapter cardAdapter = new ArtistCardAdapter(this, artistList);
        binding.artistRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.artistRecycler.setAdapter(cardAdapter);

        //Tier Recycler connected
        TierCardAdapter tierCardAdapter = new TierCardAdapter(this, event.getTiers());
        binding.tiersRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.tiersRecycler.setAdapter(tierCardAdapter);
    }

}
