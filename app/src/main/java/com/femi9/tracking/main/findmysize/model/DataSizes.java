package com.femi9.tracking.main.findmysize.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class DataSizes {

	@SerializedName("size")
	private String size;

	@SerializedName("name")
	private String name;

	public void setSize(String size){
		this.size = size;
	}

	public String getSize(){
		return size;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DataSizes dataSizes = (DataSizes) o;
		return Objects.equals(name, dataSizes.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}