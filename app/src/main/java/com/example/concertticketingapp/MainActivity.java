package com.example.concertticketingapp;

import static com.example.concertticketingapp.DataFetchingMethod.fetchCityList;
import static com.example.concertticketingapp.DataFetchingMethod.fetchEventsByCity;
import static com.example.concertticketingapp.UtilityClass.addProgressBar;
import static com.example.concertticketingapp.UtilityClass.popupWindow;
import static com.example.concertticketingapp.UtilityClass.removeProgressBar;
import static com.example.concertticketingapp.UtilityClass.showPopup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.concertticketingapp.adapter.CategoryCardAdapter;
import com.example.concertticketingapp.adapter.CategoryGridViewAdapter;
import com.example.concertticketingapp.adapter.EventAdapter;
import com.example.concertticketingapp.adapter.EventGridAdapter;
import com.example.concertticketingapp.adapter.PlaceGridViewAdapter;
import com.example.concertticketingapp.model.Event;
import com.example.concertticketingapp.model.Place;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.util.Log;
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
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    GridView cityGrid, eventGrid;
    CardView cityCard;
    String selectedCity, eventId;
    View cityPopup;
    EventAdapter eventAdapter;

    RecyclerView recyclerView;
    TextView seeAll;
    ArrayList<Event> events = new ArrayList<>();
    List<Place> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                displayCityPopup();
            }
        });

        binding.seeAllEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Events.class);
                intent.putExtra("cityName", selectedCity);
                intent.putParcelableArrayListExtra("eventList", (ArrayList<? extends Parcelable>) events);
                startActivity(intent);
                finish();
            }
        });

//        new Handler().postDelayed(this::displayCityPopup, 10);
//        new Handler().postDelayed(this::setEventData, 10);

        if(!selectedCity.isEmpty() || selectedCity != null) {
            setEventData();
        }

        setNavTab();

    }

    public void displayCityPopup() {

        if (cityPopup.getParent() != null) {
            ((ViewGroup) cityPopup.getParent()).removeView(cityPopup);
        }

        fetchCityList(this, new FetchDataCallback() {
            @Override
            public void onEventsFetched(List<Event> events) {
                return;
            }

            @Override
            public void onCitiesFetched(List<Place> cityList) {
                cities.addAll(cityList);
                PlaceGridViewAdapter adapter = new PlaceGridViewAdapter(MainActivity.this, cities, cityName -> {
                    popupWindow.dismiss();
                    selectedCity = cityName;
                    setEventData();
                });

                cityGrid.setAdapter(adapter);
                showPopup(cityPopup);

            }

            @Override
            public void onError(Throwable t) {

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
                    UtilityClass.goToEventsActivity(this, events, selectedCity);
                    break;

                }

                case R.id.navigation_tickets: {

                    binding.bottomNavigationView.requestFocus();
                   UtilityClass.goToPurchasedTicketsActivity(this);

                    break;
                }
                case R.id.navigation_issued_vcs: {

                    binding.bottomNavigationView.requestFocus();
                    UtilityClass.goToIssuedVCsActivity(this);

                    break;
                }
                default:
                    break;
            }
            return true;
        });
    }

    private void setEventData() {

        //Horizontal Layout created
        fetchEventsByCity(this, selectedCity, new FetchDataCallback() {
            @Override
            public void onEventsFetched(List<Event> eventList) {
                events.addAll(eventList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);

                //Events added to recycler view
                eventAdapter = new EventAdapter(MainActivity.this, events);
                recyclerView.setAdapter(eventAdapter);

                //Add recycler view to the scroll view
                RecyclerView recyclerView = findViewById(R.id.event_recycler);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2)); // 2 columns
                recyclerView.setAdapter(new EventGridAdapter(MainActivity.this, events));
            }

            @Override
            public void onCitiesFetched(List<Place> cities) {
                return;
            }

            @Override
            public void onError(Throwable t) {
                Log.e("e", "Failed to fetch events" + t.getLocalizedMessage());
            }
        });

    }

    private void fetchEventsByCategory(String category) {
        List<Event> categoryWiseEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getCategoryList().contains(category)) {
                categoryWiseEvents.add(event);
            }
        }
        UtilityClass.goToEventsActivity(this, categoryWiseEvents, selectedCity);

    }



    private void addCategoryView() {
        GridView categoryGridView = findViewById(R.id.categoryGrid);

        List<String> categories = Arrays.asList(getResources().getStringArray(R.array.category));
        CategoryCardAdapter cardAdapter = new CategoryCardAdapter(MainActivity.this, categories, cateogry -> {
            fetchEventsByCategory(cateogry);
        });
        categoryGridView.setAdapter(cardAdapter);
        UtilityClass.setGridViewHeightBasedOnChildren(categoryGridView, 3);

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