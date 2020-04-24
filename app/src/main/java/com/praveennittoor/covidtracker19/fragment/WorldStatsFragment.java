package com.praveennittoor.covidtracker19.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.praveennittoor.covidtracker19.CoronaAPIResponse.TotalStatsResponse;
import com.praveennittoor.covidtracker19.R;
import com.praveennittoor.covidtracker19.service.REST.API;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.*;

public class WorldStatsFragment extends Fragment {

    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    ArrayList PieEntryLabels;
    FragmentActivity listener;
    TotalStatsResponse stats;

    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity.
    // This does not mean the Activity is fully initialized.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    // This event fires 2nd, before views are created for the fragment
    // The onCreate method is called when the Fragment instance is being created, or re-created.
    // Use onCreate for any standard setup that does not require the activity to be fully created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        stats=stats();
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
        pieChart = view.findViewById(R.id.pieChart);
        getEntries();
        pieDataSet = new PieDataSet(pieEntries, "");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setSliceSpace(5f);


        if(stats!=null) {
            TextView num1 = view.findViewById(R.id.textView3);
            num1.setText(num1.getText()+" "+"123131");
            TextView num2 = view.findViewById(R.id.textView4);
            num2.setText(num2.getText()+" "+stats.getTotal_deaths()+"\n"+"Recovered "+stats.getTotal_recovered());
            TextView num4 = view.findViewById(R.id.textView5);
            num4.setText(num4.getText()+" "+stats.getNew_cases()+"\n"+"New Deaths "+stats.getNew_deaths());

        }



    }
    private void getEntries() {
        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(8f, 0));
        pieEntries.add(new PieEntry(2f, 1));

    }

    // This method is called when the fragment is no longer connected to the Activity
    // Any references saved in onAttach should be nulled out here to prevent memory leaks.
    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    // This method is called after the parent Activity's onCreate() method has completed.
    // Accessing the view hierarchy of the parent activity must be done in the onActivityCreated.
    // At this point, it is safe to search for activity View objects by their ID, for example.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public TotalStatsResponse stats(){
        Call<TotalStatsResponse> call = API.worldStats().worldStats();

        call.enqueue(new Callback<TotalStatsResponse>() {
            @Override
            public void onResponse(Call<TotalStatsResponse> call, Response<TotalStatsResponse> response) {
                System.out.println(response);
                stats = response.body();

//                //stores = storesResponse.getResult();
//
                if(stats!=null) {

                    System.out.println(stats);
                }

            }

            @Override
            public void onFailure(Call<TotalStatsResponse> call, Throwable t) {
                Log.e("error", t.toString());
                System.out.print(t.getMessage());
                //Toast.makeText(this, "Something went wrong...Please try later!", LENGTH_SHORT).show();
            }
        });
        return stats;
    }
}
