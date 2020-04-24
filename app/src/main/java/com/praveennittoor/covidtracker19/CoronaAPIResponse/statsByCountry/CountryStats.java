package com.praveennittoor.covidtracker19.CoronaAPIResponse.statsByCountry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryStats {

	@SerializedName("country_name")
	@Expose
	private String country_name;
	
	@SerializedName("cases")
	@Expose
	private String cases;
	
	@SerializedName("deaths")
	@Expose
	private String deaths;

	@SerializedName("total_recovered")
	@Expose
	private String total_recovered;
	
	@SerializedName("active_cases")
	@Expose
	private String active_cases;

	@SerializedName("serious_critical")
	@Expose
	private String serious_critical;
	/**
	 * @param country_name
	 * @param cases
	 * @param deaths
	 * @param total_recovered
	 * @param active_cases
//	 * @param new_deaths
//	 * @param new_cases
	 * @param serious_critical
	 */
	public CountryStats(String country_name, String cases, String deaths, String total_recovered, String active_cases,
			String new_deaths, String new_cases, String serious_critical) {
		super();
		this.country_name = country_name;
		this.cases = cases;
		this.deaths = deaths;
		this.total_recovered = total_recovered;
		this.active_cases = active_cases;
//		this.new_deaths = new_deaths;
//		this.new_cases = new_cases;
		this.serious_critical = serious_critical;
	}

	@Override
	public String toString() {
		return "CountryStats [country_name=" + country_name + ", cases=" + cases + ", deaths=" + deaths
				+ ", total_recovered=" + total_recovered + ", active_cases=" + active_cases + ", serious_critical=" + serious_critical + "]";
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getCases() {
		return cases;
	}

	public void setCases(String cases) {
		this.cases = cases;
	}

	public String getDeaths() {
		return deaths;
	}

	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}

	public String getTotal_recovered() {
		return total_recovered;
	}

	public void setTotal_recovered(String total_recovered) {
		this.total_recovered = total_recovered;
	}

	public String getActive_cases() {
		return active_cases;
	}

	public void setActive_cases(String active_cases) {
		this.active_cases = active_cases;
	}

//	public String getNew_deaths() {
//		return new_deaths;
//	}
//
//	public void setNew_deaths(String new_deaths) {
//		this.new_deaths = new_deaths;
//	}
//
//	public String getNew_cases() {
//		return new_cases;
//	}
//
//	public void setNew_cases(String new_cases) {
//		this.new_cases = new_cases;
//	}

	public String getSerious_critical() {
		return serious_critical;
	}

	public void setSerious_critical(String serious_critical) {
		this.serious_critical = serious_critical;
	}

	

}
