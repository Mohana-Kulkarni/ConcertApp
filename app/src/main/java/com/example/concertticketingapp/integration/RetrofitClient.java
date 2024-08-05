package com.example.concertticketingapp.integration;

import kotlin.jvm.Synchronized;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient retrofit = null;
    private iConcertAppAPI apiInterface;

    private static String BASE_URL = "https://ticketing-service-flhm.onrender.com/";

    private RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(iConcertAppAPI.class);
    }


    public static synchronized RetrofitClient getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new RetrofitClient();
        }
        return retrofit;
    }

    public iConcertAppAPI getAPI() {
        return apiInterface;
    }


}
