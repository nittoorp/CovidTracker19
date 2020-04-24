package com.praveennittoor.covidtracker19.StoresNearMeResponse;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.*;



public class StoresNearMeResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("html_attr")
	@Expose
	private List<String> html_attr;
	@SerializedName("result")
	@Expose
	private List<StoresNearMe> result;
	/**
	 * @param html_attr
	 * @param result
	 */
	public StoresNearMeResponse(List<String> html_attr, List<StoresNearMe> result) {
		super();
		this.html_attr = html_attr;
		this.result = result;
	}
	@Override
	public String toString() {
		return "StoresNearMeResponse [html_attr=" + html_attr + ", result=" + result + "]";
	}
	public List<String> getHtml_attr() {
		return html_attr;
	}
	public void setHtml_attr(List<String> html_attr) {
		this.html_attr = html_attr;
	}
	public List<StoresNearMe> getResult() {
		return result;
	}
	public void setResult(List<StoresNearMe> result) {
		this.result = result;
	}




}
