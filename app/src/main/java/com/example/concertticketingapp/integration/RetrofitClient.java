package com.example.concertticketingapp.integration;

import kotlin.jvm.Synchronized;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient retrofitConcert = null;
    private static RetrofitClient retrofitSSI = null;
    private static iConcertAppAPI apiInterface;

    private static iSSIAppAPI ssiInterface;

    private static String BASE_URL1 = "https://ticketing-service-flhm.onrender.com/";

    private static String BASE_URL2 = "https://ssi-system.onrender.com/";

    public static synchronized RetrofitClient getRetrofitConcertInstance(){
        if(retrofitConcert == null){
            retrofitConcert = new RetrofitClient();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL1)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterface = retrofit.create(iConcertAppAPI.class);
        }
        return retrofitConcert;
    }

    public static synchronized RetrofitClient getRetrofitSSIInstance(){
        if(retrofitSSI == null){
            retrofitSSI = new RetrofitClient();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ssiInterface = retrofit.create(iSSIAppAPI.class);
        }
        return retrofitSSI;
    }

    public iConcertAppAPI getAPI() {
        return apiInterface;
    }

    public iSSIAppAPI getSSIAPI() {
        return ssiInterface;
    }
}
