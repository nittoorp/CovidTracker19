package com.praveennittoor.covidtracker19.service.REST.Endpoints;

import com.praveennittoor.covidtracker19.StoresNearMeResponse.StoresNearMeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StoresNearMeEndpoint {
    @GET("/stores")
    Call<StoresNearMeResponse> searchStoresNearMe(
                                                  @Query("lat") Double lat,
                                                  @Query("lon") Double lon
                                                  );
}
