package com.praveennittoor.covidtracker19.CoronaAPIResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalStatsResponse {

	@SerializedName("total_cases")
	@Expose
	private String total_cases;
	
	@SerializedName("total_deaths")
	@Expose
	private String total_deaths;
	
	@SerializedName("total_recovered")
	@Expose
	private String total_recovered;

	@SerializedName("new_cases")
	@Expose
	private String new_cases;

	@SerializedName("new_deaths")
	@Expose
	private String new_deaths;

	public String getTotal_cases() {
		return total_cases;
	}

	public void setTotal_cases(String total_cases) {
		this.total_cases = total_cases;
	}

	public String getTotal_deaths() {
		return total_deaths;
	}

	public void setTotal_deaths(String total_deaths) {
		this.total_deaths = total_deaths;
	}

	public String getTotal_recovered() {
		return total_recovered;
	}

	public void setTotal_recovered(String total_recovered) {
		this.total_recovered = total_recovered;
	}

	public String getNew_cases() {
		return new_cases;
	}

	public void setNew_cases(String new_cases) {
		this.new_cases = new_cases;
	}

	public String getNew_deaths() {
		return new_deaths;
	}

	public void setNew_deaths(String new_deaths) {
		this.new_deaths = new_deaths;
	}


	@Override
	public String toString() {
		return "TotalStatsResponse{" +
				"total_cases='" + total_cases + '\'' +
				", total_deaths='" + total_deaths + '\'' +
				", total_recovered='" + total_recovered + '\'' +
				", new_cases='" + new_cases + '\'' +
				", new_deaths='" + new_deaths + '\'' +
				'}';
	}

	public TotalStatsResponse(String total_cases, String total_deaths, String total_recovered, String new_cases, String new_deaths) {
		super();
		this.total_cases = total_cases;
		this.total_deaths = total_deaths;
		this.total_recovered = total_recovered;
		this.new_cases = new_cases;
		this.new_deaths = new_deaths;
	}


	

}
