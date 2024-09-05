package com.example.concertticketingapp;

import static com.example.concertticketingapp.UtilityClass.goToMainActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.adapter.EventGridAdapter;
import com.example.concertticketingapp.databinding.ActivityEventsBinding;
import com.example.concertticketingapp.model.Event;
import com.example.concertticketingapp.model.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Activity_Events extends AppCompatActivity{
    private ActivityEventsBinding binding;
    ProgressDialog mProgressDialog;
    TextView cityName, listSize;

    String eventId, city;
    ArrayList<Event> eventList = new ArrayList<>();
    CardView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEventsBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_events);

        String activityName = ActivityTracker.getInstance().getLastActivityName();
        System.out.println(activityName);
        if(Objects.equals(activityName, "MainActivity")) {
            getDataFromMainActivity();
        } else if (Objects.equals(activityName, "ActivityEventDetails")) {
            getDataFromDetailsActivity();
        }


        RecyclerView recyclerView = findViewById(R.id.events_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns
        recyclerView.setAdapter(new EventGridAdapter(this, eventList, city));

        cityName = findViewById(R.id.city);
        listSize = findViewById(R.id.list_size);
        cityName.setText(city);
        String size = String.valueOf(eventList.size());
        listSize.setText(size + " Events");

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity(Activity_Events.this, city);
            }
        });

        ActivityTracker.getInstance().setLastActivityName("Activity_Events");
    }

    public void getDataFromMainActivity() {
        city = getIntent().getStringExtra("cityName");
        eventList.addAll(getIntent().getParcelableArrayListExtra("eventList"));
        System.out.println("In events : " + eventList);
    }

    public void getDataFromDetailsActivity() {
        city = getIntent().getStringExtra("cityName");
        System.out.println(city);
        DataFetchingMethod.fetchEventsByCity(this, city, new FetchDataCallback() {
            @Override
            public void onEventsFetched(List<Event> events) {
                eventList.addAll(events);
            }

            @Override
            public void onCitiesFetched(List<Place> cities) {
                return;
            }

            @Override
            public void onError(Throwable t) {
                Log.e("e", "api" + t.getLocalizedMessage());
            }
        });
    }


//    @Override
//    public void onItemClick(String string) {
//        Intent intent = new Intent(this, ActivityEventDetails.class);
//        System.out.println("In events : " + eventId);
//        intent.putExtra("EVENT_ID", eventId);
//        startActivity(intent);
//    }
}
