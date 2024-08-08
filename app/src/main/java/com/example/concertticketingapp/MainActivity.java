package com.example.concertticketingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.concertticketingapp.adapter.CategoryCardAdapter;
import com.example.concertticketingapp.adapter.CategoryGridViewAdapter;
import com.example.concertticketingapp.adapter.EventAdapter;
import com.example.concertticketingapp.adapter.EventGridAdapter;
import com.example.concertticketingapp.adapter.PlaceGridViewAdapter;
import com.example.concertticketingapp.integration.RetrofitClient;
import com.example.concertticketingapp.model.Category;
import com.example.concertticketingapp.model.Event;
import com.example.concertticketingapp.model.Place;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.concertticketingapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    ProgressDialog mProgressDialog;
    GridView cityGrid, eventGrid;
    CardView cityCard;
    String selectedCity;
    View cityPopup;
    EventAdapter eventAdapter;
    PopupWindow popupWindow;
    RecyclerView recyclerView;
    TextView seeAll;
    ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        mProgressDialog = new ProgressDialog(this);
//        mProgressDialog.setIndeterminate(true);
//        mProgressDialog.setMessage("Loading...");
//        mProgressDialog.show();



//        setSupportActionBar(binding.toolbar);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        LayoutInflater inflater = getLayoutInflater();
        cityPopup = inflater.inflate(R.layout.city_popup, null);
        cityGrid = cityPopup.findViewById(R.id.city_grid);
        recyclerView = findViewById(R.id.eventsRecycler);
        addCategoryView();


        cityCard = findViewById(R.id.cities);
        cityCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayCityList();
                showPopup(view);
            }
        });

        binding.seeAllEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Events.class);
                intent.putExtra("cityName", selectedCity);
                intent.putParcelableArrayListExtra("eventList", events);
                startActivity(intent);
                finish();
            }
        });

        setNavTab();

    }

    public void displayCityList() {

        System.out.println("In display");
        RetrofitClient.getRetrofitInstance().getAPI().getPlaces().enqueue(new Callback<List<Place>>() {
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("api","onFailure: " + t.getLocalizedMessage());
//                if (mProgressDialog.isShowing())
//                    mProgressDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) {
//                if (mProgressDialog.isShowing()){
//                    mProgressDialog.dismiss();
//                }

                List<Place> cities = (List<Place>) response.body();
                System.out.println(cities);

                PlaceGridViewAdapter adapter = new PlaceGridViewAdapter(MainActivity.this, cities, cityName -> {
                    popupWindow.dismiss();
                    selectedCity = cityName;
                    fetchEventsByCity(cityName);
                });
                cityGrid.setAdapter(adapter);
            }

        });
    }


    private void showPopup(View view) {

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focus = true;

        popupWindow = new PopupWindow(cityPopup, width, height, focus);
        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

    }

    private void fetchEventsByCity(String cityName) {
        System.out.println(cityName);
        HorizontalScrollView eventsScrollView = findViewById(R.id.events_scroll);

            RetrofitClient.getRetrofitInstance().getAPI().getEventByCity(cityName).enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                events = (ArrayList<Event>) response.body();
                System.out.println(events);

                //Horizontal Layout created
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);

                //Events added to recycler view
                eventAdapter = new EventAdapter(MainActivity.this, events);
                recyclerView.setAdapter(eventAdapter);

//                //Add recycler view to the scroll view
                RecyclerView recyclerView = findViewById(R.id.event_recycler);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2)); // 2 columns
                recyclerView.setAdapter(new EventGridAdapter(MainActivity.this, events));

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("api", "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

    public void setNavTab() {
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item->{
            int id = item.getItemId();
            switch(id) {
                case R.id.navigation_home:
                    break;

                case R.id.navigation_events : {
                    binding.bottomNavigationView.requestFocus();
                    Intent intent = new Intent(MainActivity.this, Activity_Events.class);
                    intent.putExtra("cityName", selectedCity);
                    System.out.println("In main : " + events);
                    intent.putParcelableArrayListExtra("eventList", events);
                    startActivity(intent);
                    finish();

                    break;

                }

                case R.id.navigation_tickets:
                    break;

                case R.id.navigation_issued_vcs:
                    break;

                default:
                    break;
            }
            return true;
        });
    }




    private void addCategoryView() {
        GridView categoryGridView = findViewById(R.id.categoryGrid);

        List<String> categories = Arrays.asList(getResources().getStringArray(R.array.category));
        CategoryCardAdapter cardAdapter = new CategoryCardAdapter(MainActivity.this, categories);
        categoryGridView.setAdapter(cardAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}