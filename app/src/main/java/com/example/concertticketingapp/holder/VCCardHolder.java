package com.example.concertticketingapp.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.R;

public class VCCardHolder extends RecyclerView.ViewHolder {

    TextView userName, vcType, vcId;
    public VCCardHolder(@NonNull View itemView) {
        super(itemView);
        userName = itemView.findViewById(R.id.user_name);
        vcId = itemView.findViewById(R.id.vc_id);
        vcType = itemView.findViewById(R.id.vc_type);
    }

    public void setData(String userName, String vcId, String vcType) {
        this.userName.setText(userName);
        this.vcType.setText(vcType);
        this.vcId.setText(vcId);
    }
}
