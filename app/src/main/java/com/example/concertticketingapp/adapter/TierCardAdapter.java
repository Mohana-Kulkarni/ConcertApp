package com.example.concertticketingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.R;
import com.example.concertticketingapp.holder.EventCardHolder;
import com.example.concertticketingapp.holder.TierCardHolder;
import com.example.concertticketingapp.model.Tier;

import java.util.List;

public class TierCardAdapter extends RecyclerView.Adapter<TierCardHolder> {
    Context context;
    List<Tier> tierList;

    public TierCardAdapter(Context context, List<Tier> tierList) {
        this.context = context;
        this.tierList = tierList;
    }
    @NonNull
    @Override
    public TierCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.tiers_card, parent, false);
        return new TierCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TierCardHolder holder, int position) {
        Tier tier = tierList.get(position);
        holder.setTierData(tier.getName(),tier.getPrice(), tier.getCapacity());
    }

    @Override
    public int getItemCount() {
        return tierList.size();
    }
}
