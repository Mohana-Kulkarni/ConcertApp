package com.example.concertticketingapp.holder;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.concertticketingapp.R;
import com.example.concertticketingapp.adapter.ImageCardAdapter;
import com.example.concertticketingapp.adapter.QRCardAdapter;
import com.example.concertticketingapp.model.Ticket;
import com.google.android.material.tabs.TabLayout;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.List;

public class TicketCardHolder extends RecyclerView.ViewHolder {

    ViewPager ticketQR;
    TabLayout qrDots;

    TextView eventName, eventDate, eventVenue, tierName, nftCount;
    public TicketCardHolder(@NonNull View itemView) {
        super(itemView);
        eventName = itemView.findViewById(R.id.event_name);
        eventDate = itemView.findViewById(R.id.event_date_time);
        eventVenue = itemView.findViewById(R.id.event_venue);
        tierName = itemView.findViewById(R.id.tier_name);
        nftCount = itemView.findViewById(R.id.nft_id);
        ticketQR = itemView.findViewById(R.id.ticket_qr);
        qrDots = itemView.findViewById(R.id.qr_dots);
    }

    public void setTicketData(String eventName, String eventDate, String eventVenue, String tierName, int nftCount, List<Bitmap> qrCodes) {
        this.eventName.setText(eventName);
        this.eventVenue.setText(eventVenue);
        this.eventDate.setText(eventDate);
        this.tierName.setText(tierName);
        this.nftCount.setText(String.valueOf(nftCount));


        QRCardAdapter adapter = new QRCardAdapter(itemView.getContext(), qrCodes);
        this.ticketQR.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        qrDots.setupWithViewPager(ticketQR, true);
    }


}
