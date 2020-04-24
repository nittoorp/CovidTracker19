package com.praveennittoor.covidtracker19.CoronaAPIResponse.statsByStates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatesResponse {

	@SerializedName("state")
	@Expose
	private String state;
	@SerializedName("no_confirmed")
	@Expose
	private int no_confirmed;
	@SerializedName("no_deaths")
	@Expose
//	private int no_deaths;
//	@SerializedName("no_active")
//	@Expose
	private int no_active;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getNo_confirmed() {
		return no_confirmed;
	}
	public void setNo_confirmed(int no_confirmed) {
		this.no_confirmed = no_confirmed;
	}
//	public int getNo_deaths() {
//		return no_deaths;
//	}
//	public void setNo_deaths(int no_deaths) {
//		this.no_deaths = no_deaths;
//	}
	public int getNo_active() {
		return no_active;
	}
	public void setNo_active(int no_active) {
		this.no_active = no_active;
	}
	@Override
	public String toString() {
		return "StatesResponse [state=" + state + ", no_confirmed=" + no_confirmed
				+ ", no_active=" + no_active + "]";
	}
	/**
	 * @param state
	 * @param no_confirmed
	 * @param no_deaths
	 * @param no_active
	 */
	public StatesResponse(String state, int no_confirmed, int no_deaths, int no_active) {
		super();
		this.state = state;
		this.no_confirmed = no_confirmed;
//		this.no_deaths = no_deaths;
		this.no_active = no_active;
	}
	
}

