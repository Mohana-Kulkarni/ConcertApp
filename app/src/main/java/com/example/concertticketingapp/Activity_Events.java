package com.example.concertticketingapp;

import static com.example.concertticketingapp.UtilityClass.goToMainActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.adapter.EventGridAdapter;
import com.example.concertticketingapp.databinding.ActivityEventsBinding;
import com.example.concertticketingapp.model.Event;
import com.example.concertticketingapp.model.OnItemClickListner;

import java.util.ArrayList;

public class Activity_Events extends AppCompatActivity{
    private ActivityEventsBinding binding;
    ProgressDialog mProgressDialog;
    TextView cityName, listSize;

    String eventId, city;

    CardView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEventsBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_events);

        Intent intent = getIntent();
        ArrayList<Event> eventList =  getIntent().getParcelableArrayListExtra("eventList");
        city = intent.getStringExtra("cityName");

        RecyclerView recyclerView = findViewById(R.id.events_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns
        recyclerView.setAdapter(new EventGridAdapter(this, eventList));

        cityName = findViewById(R.id.city);
        listSize = findViewById(R.id.list_size);
        cityName.setText(city);
        String size = String.valueOf(eventList.size());
        listSize.setText(size + " Events");

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity(Activity_Events.this);
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
