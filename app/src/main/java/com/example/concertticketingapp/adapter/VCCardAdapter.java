package com.example.concertticketingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.R;
import com.example.concertticketingapp.holder.TierCardHolder;
import com.example.concertticketingapp.holder.VCCardHolder;
import com.example.concertticketingapp.model.VerifiableCredentials;

import java.util.List;

public class VCCardAdapter extends RecyclerView.Adapter<VCCardHolder> {

    Context context;
    List<VerifiableCredentials> verifiableCredentialsList;

    public VCCardAdapter(Context context, List<VerifiableCredentials> verifiableCredentialsList) {
        this.context = context;
        this.verifiableCredentialsList = verifiableCredentialsList;
    }
    @NonNull
    @Override
    public VCCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.vc_card, parent, false);
        return new VCCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VCCardHolder holder, int position) {
        VerifiableCredentials vc = verifiableCredentialsList.get(position);
        String userName = vc.getDetails().getFirstName() + " " + vc.getDetails().getLastName();
        holder.setData(userName, vc.getId(), vc.getType());
    }

    @Override
    public int getItemCount() {
        return verifiableCredentialsList.size();
    }
}
