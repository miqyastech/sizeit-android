package com.sizeit.findmysize.model.event;

import com.google.gson.annotations.SerializedName;

public class DataProducts {

	@SerializedName("productSkuAbTest")
	private boolean productSkuAbTest;

	@SerializedName("sku")
	private String sku;

	public void setProductSkuAbTest(boolean productSkuAbTest){
		this.productSkuAbTest = productSkuAbTest;
	}

	public boolean isProductSkuAbTest(){
		return productSkuAbTest;
	}

	public void setSku(String sku){
		this.sku = sku;
	}

	public String getSku(){
		return sku;
	}
}