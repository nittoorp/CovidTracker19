package com.praveennittoor.covidtracker19;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.praveennittoor.covidtracker19.adapters.CustomNewsAdapter;
import com.praveennittoor.covidtracker19.adapters.ItemClickSupport;
import com.praveennittoor.covidtracker19.helper.CountryToCode;
import com.praveennittoor.covidtracker19.newsAPIResponse.NewsContent;
import com.praveennittoor.covidtracker19.newsAPIResponse.NewsResponse;
import com.praveennittoor.covidtracker19.service.REST.API;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    private CustomNewsAdapter adapter;
    private static RecyclerView recyclerView;
    Location userLocation;
    NewsResponse news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Intent intent =getIntent();
        userLocation=intent.getParcelableExtra("userloc");
        news();


    }
    public void generateDataList(List<NewsContent> newsContent){
        if(newsContent.size()== 0){
            newsContent = new ArrayList<>();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            Toast.makeText(NewsActivity.this, "No content, please try again!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

        System.out.println(newsContent);
        Collections.shuffle(newsContent);
        recyclerView = findViewById(R.id.newsList);

        adapter = new CustomNewsAdapter(this,newsContent);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NewsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            private View lastSelectedView = null;

            @SuppressLint("ResourceAsColor")
            public void clearSelection() {
                if(lastSelectedView != null) lastSelectedView.setBackgroundColor(R.color.front);
            }
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View view) {
                clearSelection();
                lastSelectedView = view;
                view.setBackgroundDrawable(view.getContext().getResources().getDrawable(R.color.back));

                NewsContent selectedStore = adapter.getItem(position);
                System.out.println(selectedStore.getContent());

            }
        });
    }
    public NewsResponse news(){
        Geocoder geoCoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> addresses= new ArrayList<>();
        try {
            addresses = geoCoder.getFromLocation(userLocation.getLatitude(), userLocation.getLongitude(), 1);

            String add = "";


            if (addresses.size() > 0) {
                //System.out.println(addresses.get(0).getLocality());
                System.out.println(addresses.get(0).getCountryName());

            }

            //System.out.println(add);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        String cc= addresses.get(0).getCountryName().replaceAll(" ","");
        System.out.println(cc);

        Call<NewsResponse> call = API.newsNearMe().topHeadlines(CountryToCode.map.get(cc));

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                System.out.println(response);
                news = response.body();

//                //stores = storesResponse.getResult();
//
                if(news!=null) {
                    generateDataList(news.getNewsResponse());
                    System.out.println(news);
                }

            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("error", t.toString());
                System.out.print(t.getMessage());
                //Toast.makeText(this, "Something went wrong...Please try later!", LENGTH_SHORT).show();
            }
        });
        return news;
    }

}
