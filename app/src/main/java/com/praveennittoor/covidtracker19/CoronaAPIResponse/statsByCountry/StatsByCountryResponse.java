package com.praveennittoor.covidtracker19.CoronaAPIResponse.statsByCountry;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatsByCountryResponse {

	@SerializedName("countries_stat")
	@Expose
	private List<CountryStats> countries_stat;

	public List<CountryStats> getCountries_stat() {
		return countries_stat;
	}

	public void setCountries_stat(List<CountryStats> countries_stat) {
		this.countries_stat = countries_stat;
	}

	@Override
	public String toString() {
		return "StatsByCountryResponse [countries_stat=" + countries_stat + "]";
	}

	/**
	 * @param countries_stat
	 */
	public StatsByCountryResponse(List<CountryStats> countries_stat) {
		super();
		this.countries_stat = countries_stat;
	}
	
	
}
