package com.praveennittoor.covidtracker19.CoronaAPIResponse.statsByStates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IndianStates {
	
	@SerializedName("state")
	@Expose
	private String state;
	
	@SerializedName("active")
	@Expose
	private String active;
	
	@SerializedName("confirmed")
	@Expose
	private String confirmed;
	
	/**
	 * @param state
	 * @param active
	 * @param confirmed
	 * @param recovered
	 * @param deaths
	 */
	public IndianStates(String state, String active, String confirmed, String recovered, String deaths) {
		super();
		this.state = state;
		this.active = active;
		this.confirmed = confirmed;
		this.recovered = recovered;
		this.deaths = deaths;
	}

	@Override
	public String toString() {
		return "IndianStates [state=" + state + ", active=" + active + ", confirmed=" + confirmed + ", recovered="
				+ recovered + ", deaths=" + deaths + "]";
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

	public String getRecovered() {
		return recovered;
	}

	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}

	public String getDeaths() {
		return deaths;
	}

	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}

	@SerializedName("recovered")
	@Expose
	private String recovered;
	
	@SerializedName("deaths")
	@Expose
	private String deaths;

}
