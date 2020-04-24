package com.praveennittoor.covidtracker19.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.praveennittoor.covidtracker19.CoronaAPIResponse.TotalStatsResponse;
import com.praveennittoor.covidtracker19.MainActivity;
import com.praveennittoor.covidtracker19.MapsActivity;
import com.praveennittoor.covidtracker19.R;
import com.praveennittoor.covidtracker19.StoresNearMeResponse.StoresNearMe;
import com.praveennittoor.covidtracker19.StoresNearMeResponse.StoresNearMeResponse;
import com.praveennittoor.covidtracker19.adapters.CustomPlacesNearMeAdapter;
import com.praveennittoor.covidtracker19.adapters.ItemClickSupport;
import com.praveennittoor.covidtracker19.service.REST.API;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoresFragment extends Fragment {
    StoresNearMeResponse stores;
    Location userLocation;
    TotalStatsResponse world;
    List<StoresNearMe> storeList;
    RecyclerView recyclerView;
    CustomPlacesNearMeAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getArguments() != null) {
            userLocation = getArguments().getParcelable("userloc");
        }
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        stores=callStores();
        storeList=stores.getResult();

        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    public StoresNearMeResponse callStores(){

        Call<StoresNearMeResponse> call = API.storesNearMe().searchStoresNearMe(33.4090163,-111.9251615);

        call.enqueue(new Callback<StoresNearMeResponse>() {
            @Override
            public void onResponse(Call<StoresNearMeResponse> call, Response<StoresNearMeResponse> response) {
                System.out.println(response);
                stores = response.body();

//                //stores = storesResponse.getResult();
//
                if(stores!=null) {
                    //generateDataList(stores);
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
}
