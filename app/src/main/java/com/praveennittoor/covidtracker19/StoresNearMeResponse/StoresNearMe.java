package com.praveennittoor.covidtracker19.StoresNearMeResponse;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoresNearMe {

	@SerializedName("geometry")
	@Expose
	private Geometry geometry;
	@SerializedName("iconURL")
	@Expose
	private String iconURL;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("rating")
	@Expose
	private float rating;
	/**
	 * @param geometry
	 * @param iconURL
	 * @param name
	 * @param rating
	 */
	public StoresNearMe(Geometry geometry, String iconURL, String name, float rating) {
		super();
		this.geometry = geometry;
		this.iconURL = iconURL;
		this.name = name;
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "StoresNearMe [geometry=" + geometry + ", iconURL=" + iconURL + ", name=" + name + ", rating=" + rating
				+ "]";
	}
	public Geometry getGeometry() {
		return geometry;
	}
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	public String getIconURL() {
		return iconURL;
	}
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}


}
