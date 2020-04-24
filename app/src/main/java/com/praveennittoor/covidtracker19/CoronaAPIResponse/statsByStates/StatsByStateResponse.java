package com.praveennittoor.covidtracker19.CoronaAPIResponse.statsByStates;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatsByStateResponse {

	@SerializedName("recovered")
	@Expose
	private int recovered;
	@SerializedName("statesResponse")
	@Expose
	private List<StatesResponse> statesResponse;
	@Override
	public String toString() {
		return "StatsByStateResponse [recovered=" + recovered + ", statesResponse=" + statesResponse + "]";
	}
	public int getRecovered() {
		return recovered;
	}
	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}
	public List<StatesResponse> getStatesResponse() {
		return statesResponse;
	}
	public void setStatesResponse(List<StatesResponse> statesResponse) {
		this.statesResponse = statesResponse;
	}
	/**
	 * @param recovered
	 * @param statesResponse
	 */
	public StatsByStateResponse(int recovered, List<StatesResponse> statesResponse) {
		super();
		this.recovered = recovered;
		this.statesResponse = statesResponse;
	}
	
}
