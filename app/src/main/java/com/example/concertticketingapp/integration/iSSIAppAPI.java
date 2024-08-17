package com.example.concertticketingapp.integration;

import com.example.concertticketingapp.model.VerifiableCredentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface iSSIAppAPI {

    @GET("userDetails/VCS")
    Call<List<VerifiableCredentials>> getVCsByUserDid(@Query("userDid") String userDid);
}
