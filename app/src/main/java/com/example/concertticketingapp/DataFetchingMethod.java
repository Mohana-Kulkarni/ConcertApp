package com.example.concertticketingapp;

import static com.example.concertticketingapp.UtilityClass.addProgressBar;
import static com.example.concertticketingapp.UtilityClass.popupWindow;
import static com.example.concertticketingapp.UtilityClass.removeProgressBar;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.adapter.EventAdapter;
import com.example.concertticketingapp.adapter.EventGridAdapter;
import com.example.concertticketingapp.adapter.PlaceGridViewAdapter;
import com.example.concertticketingapp.integration.RetrofitClient;
import com.example.concertticketingapp.model.Event;
import com.example.concertticketingapp.model.Place;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataFetchingMethod {
    static List<Event> events = new ArrayList<>();
    static List<Place> cities = new ArrayList<>();
    public static void fetchEventsByCity(Context context,String cityName, FetchDataCallback callback) {
        addProgressBar(context);

        RetrofitClient.getRetrofitConcertInstance().getAPI().getEventByCity(cityName).enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()) {
                    events = response.body();
                    callback.onEventsFetched(events);
                } else {
                    callback.onError(new Throwable("Failed to fetch events"));
                }
                removeProgressBar();
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("api", "onFailure: " + t.getLocalizedMessage());
                callback.onError(t);
                removeProgressBar();
            }
        });
    }

    public static void fetchCityList(Context context, FetchDataCallback callback) {
        addProgressBar(context);
        RetrofitClient.getRetrofitConcertInstance().getAPI().getPlaces().enqueue(new Callback<List<Place>>() {
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("api","onFailure: " + t.getLocalizedMessage());
                callback.onError(t);
                removeProgressBar();
            }

            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()) {
                    cities = (List<Place>) response.body();
                    callback.onCitiesFetched(cities);
                } else {
                    callback.onError(new Throwable("Failed to fetch cities"));
                }
                removeProgressBar();
            }

        });
    }
}
