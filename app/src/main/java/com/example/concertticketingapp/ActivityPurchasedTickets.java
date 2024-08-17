package com.example.concertticketingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.adapter.ImageCardAdapter;
import com.example.concertticketingapp.adapter.TicketCardAdapter;
import com.example.concertticketingapp.databinding.ActivityPurchasedTicketsBinding;
import com.example.concertticketingapp.integration.RetrofitClient;
import com.example.concertticketingapp.model.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPurchasedTickets extends AppCompatActivity {

    ActivityPurchasedTicketsBinding binding;
    RecyclerView ticketQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPurchasedTicketsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });

//        fetchTicketsByUserId();
        String userId = "392045287127908420";
        RetrofitClient.getRetrofitConcertInstance().getAPI().getTicketsByUserId(userId).enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                System.out.println("In Tickets : ");
                List<Ticket> tickets = response.body();
                System.out.println(tickets);

                TicketCardAdapter ticketCardAdapter = new TicketCardAdapter(ActivityPurchasedTickets.this, tickets);
                binding.ticketsRecycler.setLayoutManager(new LinearLayoutManager(ActivityPurchasedTickets.this, LinearLayoutManager.VERTICAL, false));
                binding.ticketsRecycler.setAdapter(ticketCardAdapter);


            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {
                Log.e("api", "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

    public void goToMainActivity() {
        System.out.println("Clicked Back Button");
        startActivity(new Intent(ActivityPurchasedTickets.this, MainActivity.class));
        finish();
    }

    public void fetchTicketsByUserId() {
        RetrofitClient.getRetrofitConcertInstance().getAPI().getTicketsByUserId("392045287127908420").enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                List<Ticket> tickets = response.body();
                System.out.println(tickets);

                TicketCardAdapter ticketCardAdapter = new TicketCardAdapter(ActivityPurchasedTickets.this, tickets);
                binding.ticketsRecycler.setLayoutManager(new LinearLayoutManager(ActivityPurchasedTickets.this, LinearLayoutManager.VERTICAL, false));
                binding.ticketsRecycler.setAdapter(ticketCardAdapter);




            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {
                Log.e("api", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
