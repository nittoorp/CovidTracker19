package com.praveennittoor.covidtracker19.service.REST.Endpoints;


import com.praveennittoor.covidtracker19.newsAPIResponse.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsHeadlinesEndpoint {
	@GET("/news/")
	Call<NewsResponse> topHeadlines(@Query(value ="country") String country);

}
