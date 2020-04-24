package com.praveennittoor.covidtracker19.newsAPIResponse;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsResponse {
	
	@SerializedName("newsResponse")
	@Expose
	private List<NewsContent> newsResponse;

	public List<NewsContent> getNewsResponse() {
		return newsResponse;
	}

	public void setNewsResponse(List<NewsContent> newsResponse) {
		this.newsResponse = newsResponse;
	}

	@Override
	public String toString() {
		return "NewsResponse [newsResponse=" + newsResponse + "]";
	}

	/**
	 * @param newsResponse
	 */
	public NewsResponse(List<NewsContent> newsResponse) {
		super();
		this.newsResponse = newsResponse;
	}

}
