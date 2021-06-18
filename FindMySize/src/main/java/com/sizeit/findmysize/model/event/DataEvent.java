package com.sizeit.findmysize.model.event;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataEvent{

	@SerializedName("abTest")
	private boolean abTest;

	@SerializedName("origin")
	private String origin;

	@SerializedName("eventType")
	private String eventType;

	@SerializedName("projectName")
	private String projectName;

	@SerializedName("region")
	private String region;

	@SerializedName("userId")
	private String userId;

	@SerializedName("orderValue")
	private String orderValue;

	@SerializedName("platform")
	private String platform;

	@SerializedName("products")
	private List<DataProducts> products;

	public void setAbTest(boolean abTest){
		this.abTest = abTest;
	}

	public boolean isAbTest(){
		return abTest;
	}

	public void setOrigin(String origin){
		this.origin = origin;
	}

	public String getOrigin(){
		return origin;
	}

	public void setEventType(String eventType){
		this.eventType = eventType;
	}

	public String getEventType(){
		return eventType;
	}

	public void setProjectName(String projectName){
		this.projectName = projectName;
	}

	public String getProjectName(){
		return projectName;
	}

	public void setRegion(String region){
		this.region = region;
	}

	public String getRegion(){
		return region;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setOrderValue(String orderValue){
		this.orderValue = orderValue;
	}

	public String getOrderValue(){
		return orderValue;
	}

	public void setPlatform(String platform){
		this.platform = platform;
	}

	public String getPlatform(){
		return platform;
	}

	public void setProducts(List<DataProducts> products){
		this.products = products;
	}

	public List<DataProducts> getProducts(){
		return products;
	}
}