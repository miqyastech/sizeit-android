package com.femi9.findmysize.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseData {

	@SerializedName("sizes")
	private List<DataSizes> sizes;

	public void setSizes(List<DataSizes> sizes){
		this.sizes = sizes;
	}

	public List<DataSizes> getSizes(){
		return sizes;
	}
}