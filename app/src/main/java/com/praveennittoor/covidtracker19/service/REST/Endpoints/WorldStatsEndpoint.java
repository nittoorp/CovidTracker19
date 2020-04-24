package com.praveennittoor.covidtracker19.service.REST.Endpoints;




import com.praveennittoor.covidtracker19.CoronaAPIResponse.TotalStatsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;


public interface WorldStatsEndpoint {

	@GET("/")
	Call<TotalStatsResponse> worldStats();
}
