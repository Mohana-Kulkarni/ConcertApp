package com.example.concertticketingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.concertticketingapp.adapter.VCCardAdapter;
import com.example.concertticketingapp.databinding.ActivityIssuedVcsBinding;
import com.example.concertticketingapp.databinding.ActivityPurchasedTicketsBinding;
import com.example.concertticketingapp.integration.RetrofitClient;
import com.example.concertticketingapp.model.VerifiableCredentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityIssuedVCs extends AppCompatActivity {

    private ActivityIssuedVcsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityIssuedVcsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });

        fetchVCsByUserDid();

    }

    private void fetchVCsByUserDid() {
        String userDid = "did:ethr:3ac5d8a71babd99db3ee41940b8f984dd032006c";
        RetrofitClient.getRetrofitSSIInstance().getSSIAPI().getVCsByUserDid(userDid).enqueue(new Callback<List<VerifiableCredentials>>() {
            @Override
            public void onResponse(Call<List<VerifiableCredentials>> call, Response<List<VerifiableCredentials>> response) {
                List<VerifiableCredentials> verifiableCredentialsList = response.body();
                System.out.println(verifiableCredentialsList);

                binding.vcRecycler.setLayoutManager(new LinearLayoutManager(ActivityIssuedVCs.this, LinearLayoutManager.VERTICAL, false));
                VCCardAdapter cardAdapter = new VCCardAdapter(ActivityIssuedVCs.this, verifiableCredentialsList);
                binding.vcRecycler.setAdapter(cardAdapter);
            }

            @Override
            public void onFailure(Call<List<VerifiableCredentials>> call, Throwable t) {

            }
        });
    }

    public void goToMainActivity() {
        System.out.println("Clicked Back Button");
        startActivity(new Intent(ActivityIssuedVCs.this, MainActivity.class));
        finish();
    }
}