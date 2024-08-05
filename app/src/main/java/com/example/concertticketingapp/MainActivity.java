package com.example.concertticketingapp;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.concertticketingapp.adapter.CategoryGridViewAdapter;
import com.example.concertticketingapp.adapter.EventAdapter;
import com.example.concertticketingapp.adapter.PlaceGridViewAdapter;
import com.example.concertticketingapp.integration.RetrofitClient;
import com.example.concertticketingapp.model.Category;
import com.example.concertticketingapp.model.Event;
import com.example.concertticketingapp.model.Place;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    ProgressDialog mProgressDialog;
    String cityName;
    GridView cityGrid, categoryGrid;
    CardView cityCard;
    View cityPopup;
    EventAdapter eventAdapter;
    PopupWindow popupWindow;
    RecyclerView recyclerView;

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
                List<Event> events = response.body();
                System.out.println(events);

                //Horizontal Layout created
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);

                //Events added to recycler view
                eventAdapter = new EventAdapter(MainActivity.this, events);
                recyclerView.setAdapter(eventAdapter);

//                //Add recycler view to the scroll view
//                eventsScrollView.addView(recyclerView);

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("api", "onFailure: " + t.getLocalizedMessage());
            }
        });

    }




    private void addCategoryView() {
        HorizontalScrollView categoryScrollView = findViewById(R.id.category_scroll);
        LinearLayout categoryContainer = new LinearLayout(this);
        categoryContainer.setOrientation(LinearLayout.HORIZONTAL);
        categoryScrollView.addView(categoryContainer);

        String[] categories = getResources().getStringArray(R.array.category);
        for (String categoryName : categories) {
            String category_img = categoryName.toLowerCase();
            int resId = getResources().getIdentifier(category_img, "drawable", getPackageName());
            Category category = new Category(categoryName, category_img);

            LinearLayout categoryItem = (LinearLayout) getLayoutInflater().inflate(R.layout.category_add_layout, null);

            ImageView categoryImg = categoryItem.findViewById(R.id.category_img);
            TextView categoryNameView = categoryItem.findViewById(R.id.category_name);

            categoryNameView.setText(category.getCategoryName());

            categoryImg.setImageResource(resId);

            categoryContainer.addView(categoryItem);

        }
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