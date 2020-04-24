package com.praveennittoor.covidtracker19.service.REST;
import com.praveennittoor.covidtracker19.Config.Config;
import com.praveennittoor.covidtracker19.service.REST.Endpoints.IndianStatesEndpoint;
import com.praveennittoor.covidtracker19.service.REST.Endpoints.NewsHeadlinesEndpoint;
import com.praveennittoor.covidtracker19.service.REST.Endpoints.StatsByCountryEndpoint;
import com.praveennittoor.covidtracker19.service.REST.Endpoints.StatsByStatesINUSAEndpoint;
import com.praveennittoor.covidtracker19.service.REST.Endpoints.StoresNearMeEndpoint;
import com.praveennittoor.covidtracker19.service.REST.Endpoints.WorldStatsEndpoint;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

            private static <T> T builder(Class<T> endpoint, String URL) {
                return new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(endpoint);
            }

            public static StoresNearMeEndpoint storesNearMe() {
                return builder(StoresNearMeEndpoint.class, Config.BASE_URL);
            }

            public static IndianStatesEndpoint statsOfStatesIN() {
                return builder(IndianStatesEndpoint.class, Config.BASE_URL);
            }

            public static NewsHeadlinesEndpoint newsNearMe() {
                return builder(NewsHeadlinesEndpoint.class, Config.BASE_URL);
            }

            public static StatsByCountryEndpoint statsByCountry() {
                return builder(StatsByCountryEndpoint.class, Config.BASE_URL);
            }

            public static StatsByStatesINUSAEndpoint statsOfStatesUS() {
                return builder(StatsByStatesINUSAEndpoint.class, Config.BASE_URL);
            }

            public static WorldStatsEndpoint worldStats() {
                return builder(WorldStatsEndpoint.class, Config.BASE_URL);
            }


}
