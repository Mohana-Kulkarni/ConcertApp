package com.example.concertticketingapp.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.R;

public class TierCardHolder extends RecyclerView.ViewHolder {

    TextView tierName, tierPrice, tierSeats, ticketCount;
    public CardView addBtn;
    public CardView removeBtn;
    public int count = 0;

    public TierCardHolder(@NonNull View itemView) {
        super(itemView);
        tierName = itemView.findViewById(R.id.tier_name);
        tierSeats = itemView.findViewById(R.id.available_seats);
        tierPrice = itemView.findViewById(R.id.tier_price);
        ticketCount = itemView.findViewById(R.id.ticket_count);
        removeBtn = itemView.findViewById(R.id.remove);
        addBtn = itemView.findViewById(R.id.add);
    }

    public void setTierData(String tierName, int tierPrice, int tierSeats) {
        this.tierName.setText(tierName);
        this.tierSeats.setText(tierSeats + " available");
        this.tierPrice.setText("Rs. " + tierPrice);
    }

    public void updateTicketCount() {
        ticketCount.setText(String.valueOf(count));
    }
}
