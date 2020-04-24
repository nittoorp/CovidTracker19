package com.praveennittoor.covidtracker19.service.REST.Endpoints;


import com.praveennittoor.covidtracker19.CoronaAPIResponse.statsByStates.IndianStatesStatsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface IndianStatesEndpoint {

	@GET("/in/states")
	Call<IndianStatesStatsResponse> indianStates();

}
