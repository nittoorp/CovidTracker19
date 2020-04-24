package com.praveennittoor.covidtracker19;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


import com.praveennittoor.covidtracker19.StoresNearMeResponse.StoresNearMe;
import com.praveennittoor.covidtracker19.StoresNearMeResponse.StoresNearMeResponse;
import com.praveennittoor.covidtracker19.adapters.CustomPlacesNearMeAdapter;
import com.praveennittoor.covidtracker19.adapters.ItemClickSupport;
import com.praveennittoor.covidtracker19.service.REST.API;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoresActivity extends AppCompatActivity {


    StoresNearMeResponse stores;
    Location userLocation;

    private CustomPlacesNearMeAdapter adapter;
    private static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        Intent intent =getIntent();
        userLocation=intent.getParcelableExtra("userloc");
        stores=callStores();
        System.out.println(stores);


    }



    public StoresNearMeResponse callStores(){

        Call<StoresNearMeResponse> call = API.storesNearMe().searchStoresNearMe(userLocation.getLatitude(),userLocation.getLongitude());

        call.enqueue(new Callback<StoresNearMeResponse>() {
            @Override
            public void onResponse(Call<StoresNearMeResponse> call, Response<StoresNearMeResponse> response) {
                System.out.println(response);
                stores = response.body();

//                //stores = storesResponse.getResult();
//
                if(stores!=null) {
                    generateDataList(stores.getResult());
                    System.out.println(stores);
                }

            }

            @Override
            public void onFailure(Call<StoresNearMeResponse> call, Throwable t) {
                Log.e("error", t.toString());
                System.out.print(t.getMessage());
                // Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
        return stores;








    }
        public void generateDataList(List<StoresNearMe> storeList){
            if(storeList.size()== 0){
                storeList = new ArrayList<>();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(StoresActivity.this, "No content, please try again!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

            System.out.println(storeList);
            Collections.shuffle(storeList);
            recyclerView = findViewById(R.id.storeList);

            adapter = new CustomPlacesNearMeAdapter(this,storeList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(StoresActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

            ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                private View lastSelectedView = null;

                @SuppressLint("ResourceAsColor")
                public void clearSelection() {
                    if(lastSelectedView != null) lastSelectedView.setBackgroundColor(android.R.color.white);
                }
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View view) {
                    clearSelection();
                    lastSelectedView = view;
                    view.setBackgroundDrawable(view.getContext().getResources().getDrawable(R.color.colorPrimaryDark));

                    StoresNearMe selectedStore = adapter.getItem(position);
                    System.out.println(selectedStore.getName());

                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    intent.putExtra("userLat",userLocation.getLatitude());
                    intent.putExtra("userLon",userLocation.getLongitude());
                    intent.putExtra("storeLat",selectedStore.getGeometry().getLocation().getLatitude());
                    intent.putExtra("storeLon",selectedStore.getGeometry().getLocation().getLongitude());
                    intent.putExtra("title",selectedStore.getName());
                    startActivity(intent);
                }
            });
        }


}
