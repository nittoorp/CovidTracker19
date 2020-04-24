package com.praveennittoor.covidtracker19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;
import com.praveennittoor.covidtracker19.CoronaAPIResponse.TotalStatsResponse;
import com.praveennittoor.covidtracker19.CoronaAPIResponse.statsByCountry.CountryStats;
import com.praveennittoor.covidtracker19.CoronaAPIResponse.statsByCountry.StatsByCountryResponse;
import com.praveennittoor.covidtracker19.fragment.WorldStatsFragment;
import com.praveennittoor.covidtracker19.fragment.StoresFragment;
import com.praveennittoor.covidtracker19.fragment.NewsFragment;
import com.praveennittoor.covidtracker19.adapters.TabAdapter;
import com.praveennittoor.covidtracker19.service.REST.API;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    LocationListener locationListener;
    Location userLocation;
    ProgressDialog progressDialog;
    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    ArrayList PieEntryLabels;
    TotalStatsResponse stats;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Button btn = findViewById(R.id.button);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        pieChart = findViewById(R.id.pie);
        getEntries();
        progressDialog.show();
        initializeUserLocation();


        /////////////////////////////
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        stats();

//        pieChart = findViewById(R.id.pie);
//
//        pieDataSet = new PieDataSet(pieEntries, "");
//        pieData = new PieData(pieDataSet);
//        pieChart.setData(pieData);
//        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
//        pieDataSet.setSliceSpace(2f);
//        pieDataSet.setValueTextColor(Color.WHITE);
//        pieDataSet.setValueTextSize(10f);
//        pieDataSet.setSliceSpace(5f);
    }
    private void getEntries() {
        new AsyncTask<Object, Void, StatsByCountryResponse>() {
            @Override
            protected StatsByCountryResponse doInBackground(Object... params) {
                try {
                    Call<StatsByCountryResponse> call = API.statsByCountry().statsByCountry();

                    call.enqueue(new Callback<StatsByCountryResponse>() {
                        @Override
                        public void onResponse(Call<StatsByCountryResponse> call, Response<StatsByCountryResponse> response) {
                            System.out.println(response);
                            StatsByCountryResponse res = response.body();
                            List<CountryStats> countryStatsList = res.getCountries_stat();
//                //stores = storesResponse.getResult();
                            Collections.sort(countryStatsList, new Comparator<CountryStats>() {
                                @Override
                                public int compare(CountryStats o1, CountryStats o2) {
                                    return Integer.parseInt(o2.getCases().replaceAll(",", "")) - Integer.parseInt(o1.getCases().replaceAll(",", ""));
                                }
                            });

                            if (countryStatsList != null) {
                                //System.out.println(countryStatsList+"\n");
                                int totalCases = 0;
                                for (CountryStats i : countryStatsList) {
                                    totalCases += Integer.parseInt(i.getCases().replaceAll(",", ""));
                                }
                                System.out.println(totalCases);
                                int temp = totalCases;
                                int i = 0;
                                Map<String, Integer> countries = new HashMap<>();
                                while (temp > (int) (0.25 * totalCases)) {
                                    int i1 = Integer.parseInt(countryStatsList.get(i).getCases().replaceAll(",", ""));
                                    temp = temp - i1;
                                    System.out.println(temp);
                                    countries.put(countryStatsList.get(i).getCountry_name(), i1);
                                    ++i;
                                }
                                countries.put("Others", temp);
                                ArrayList<Integer> colors = new ArrayList<Integer>();

                                for (int c : ColorTemplate.VORDIPLOM_COLORS)
                                    colors.add(c);

                                for (int c : ColorTemplate.JOYFUL_COLORS)
                                    colors.add(c);

                                for (int c : ColorTemplate.COLORFUL_COLORS)
                                    colors.add(c);

                                for (int c : ColorTemplate.LIBERTY_COLORS)
                                    colors.add(c);

                                for (int c : ColorTemplate.PASTEL_COLORS)
                                    colors.add(c);

                                colors.add(ColorTemplate.getHoloBlue());
                                pieDataSet.setColors(colors);



                                pieEntries = new ArrayList<>();

//                                for (String key : countries.keySet()) {
//                                    int k = (int)((countries.get(key) / totalCases)*10);
//                                    pieEntries.add(new PieEntry(k,key));
//                                    i++;
//                                }

                                pieDataSet.setSliceSpace(10f);
                                pieDataSet.setValueTextColor(Color.WHITE);
                                pieDataSet.setValueTextSize(10f);
                                pieDataSet.setSliceSpace(10f);

                                pieEntries.add(new PieEntry(2f, 0));
                                pieEntries.add(new PieEntry(2f, 1));
                                pieDataSet = new PieDataSet(pieEntries, "COVID CASES");
                                pieData = new PieData(pieDataSet);
                                pieChart.setData(pieData);

                            progressDialog.dismiss();
                        }}

                        @Override
                        public void onFailure(Call<StatsByCountryResponse> call, Throwable t) {
                            Log.e("error", t.toString());
                            System.out.print(t.getMessage());
                            //Toast.makeText(this, "Something went wrong...Please try later!", LENGTH_SHORT).show();
                        }
                    });


                }catch(Exception e){
                    System.out.println(e);
                }
                return null;
            }
            protected void onPostExecute(StatsByCountryResponse response) {
                //System.out.print(response.toString());
                progressDialog.dismiss();

            }

        }.execute();
    }




    public void getStores(View view){
        Intent intent = new Intent(getApplicationContext(), StoresActivity.class);
        intent.putExtra("userloc",userLocation);
        startActivity(intent);
    }
    public void getNews(View view){
        Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
        intent.putExtra("userloc",userLocation);
        startActivity(intent);
    }

    public void initializeUserLocation(){
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                userLocation = location;
                progressDialog.dismiss();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) { }

            @Override
            public void onProviderEnabled(String s) { }

            @Override
            public void onProviderDisabled(String s) { }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    public void stats(){

        Call<TotalStatsResponse> call = API.worldStats().worldStats();

        call.enqueue(new Callback<TotalStatsResponse>() {
            @Override
            public void onResponse(Call<TotalStatsResponse> call, Response<TotalStatsResponse> response) {
                System.out.println(response);
                stats = response.body();

//                //stores = storesResponse.getResult();
//
                if(stats!=null) {

                    TextView num1 = findViewById(R.id.textView3);
                    num1.setText(num1.getText()+": "+stats.getTotal_cases());
                    TextView num2 = findViewById(R.id.textView4);
                    num2.setText(num2.getText()+": "+stats.getTotal_deaths());
                    TextView num4 = findViewById(R.id.textView5);
                    num4.setText(num4.getText()+": "+stats.getTotal_recovered());
                    TextView num5 = findViewById(R.id.textView6);
                    num5.setText(num5.getText()+": "+stats.getNew_deaths());
                    TextView num6 = findViewById(R.id.textView7);
                    num6.setText(num6.getText()+": "+stats.getNew_cases());
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<TotalStatsResponse> call, Throwable t) {
                Log.e("error", t.toString());
                System.out.print(t.getMessage());
                //Toast.makeText(this, "Something went wrong...Please try later!", LENGTH_SHORT).show();
            }
        });

    }

}
