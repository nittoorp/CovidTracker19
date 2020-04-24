package com.praveennittoor.covidtracker19.service.REST.Endpoints;



import com.praveennittoor.covidtracker19.CoronaAPIResponse.statsByCountry.StatsByCountryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface StatsByCountryEndpoint {

	@GET("/stats/world")
	Call<StatsByCountryResponse> statsByCountry();
}
