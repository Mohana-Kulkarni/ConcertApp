package com.example.concertticketingapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.concertticketingapp.R;
import com.example.concertticketingapp.holder.TicketCardHolder;
import com.example.concertticketingapp.model.Ticket;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.List;

public class TicketCardAdapter extends RecyclerView.Adapter<TicketCardHolder> {

    Context context;
    List<Ticket> ticketList;

    public TicketCardAdapter(Context context, List<Ticket> ticketList) {
        this.context = context;
        this.ticketList = ticketList;
    }
    @NonNull
    @Override
    public TicketCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ticket_card, parent, false);
        return new TicketCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketCardHolder holder, int position) {
        Ticket ticket = ticketList.get(position);
        String place = ticket.getEventId().getVenueId().getName() + "," + ticket.getEventId().getVenueId().getAddress();
        List<Bitmap> qrCodes = generateQRCodes(ticket);
        holder.setTicketData(ticket.getEventId().getName(), ticket.getEventId().getDateAndTime(), place, ticket.getTier().getName(), ticket.getCount(), qrCodes);

    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    private List<Bitmap> generateQRCodes(Ticket ticket) {
        List<Bitmap> qrCodes = new ArrayList<>();
        for (String data : ticket.getNfts().keySet()) {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                String info = ticket.getVcId() + "," + ticket.getId() + "," + data;
                BitMatrix bitMatrix = multiFormatWriter.encode(info, BarcodeFormat.QR_CODE, 250, 250);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                qrCodes.add(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
        return qrCodes;
    }
}

