package com.femi9.tracking.main.findmysize.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

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