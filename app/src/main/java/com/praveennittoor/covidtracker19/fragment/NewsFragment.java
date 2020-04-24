package com.praveennittoor.covidtracker19.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.praveennittoor.covidtracker19.CoronaAPIResponse.TotalStatsResponse;
import com.praveennittoor.covidtracker19.R;
import com.praveennittoor.covidtracker19.newsAPIResponse.NewsResponse;
import com.praveennittoor.covidtracker19.service.REST.API;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {
    Location userLoc;
    NewsResponse news;
    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity.
    // This does not mean the Activity is fully initialized.

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    // This event fires 2nd, before views are created for the fragment
    // The onCreate method is called when the Fragment instance is being created, or re-created.
    // Use onCreate for any standard setup that does not require the activity to be fully created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getArguments() != null) {
            userLoc = getArguments().getParcelable("userloc");
        }
        super.onCreate(savedInstanceState);


    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_one, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // onViewCreated() is only called if the view returned from onCreateView() is non-null.
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }


    // This method is called when the fragment is no longer connected to the Activity
    // Any references saved in onAttach should be nulled out here to prevent memory leaks.
    @Override
    public void onDetach() {
        super.onDetach();

    }

    // This method is called after the parent Activity's onCreate() method has completed.
    // Accessing the view hierarchy of the parent activity must be done in the onActivityCreated.
    // At this point, it is safe to search for activity View objects by their ID, for example.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public NewsResponse news(){
        Geocoder geoCoder = new Geocoder(getContext(), Locale.getDefault());
        List<Address> addresses= new ArrayList<>();
        try {
            addresses = geoCoder.getFromLocation(userLoc.getLatitude(), userLoc.getLongitude(), 1);

            String add = "";


            if (addresses.size() > 0) {
                System.out.println(addresses.get(0).getLocality());
                System.out.println(addresses.get(0).getCountryName());

            }

            //System.out.println(add);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        Call<NewsResponse> call = API.newsNearMe().topHeadlines(addresses.get(0).getCountryName());

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                System.out.println(response);
                news = response.body();

//                //stores = storesResponse.getResult();
//
                if(news!=null) {

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
