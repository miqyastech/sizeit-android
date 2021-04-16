package com.femi9.tracking.main.findmysize.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataAPI {
    @SerializedName("belly")
    @Expose
    private Integer belly;
    @SerializedName("apiKey")
    @Expose
    private String apiKey;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("brandSize")
    @Expose
    private Integer brandSize;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("hip")
    @Expose
    private Integer hip;
    @SerializedName("height")
    @Expose
    private Integer height;

    public Integer getBelly() {
        return belly;
    }

    public void setBelly(Integer belly) {
        this.belly = belly;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getBrandSize() {
        return brandSize;
    }

    public void setBrandSize(Integer brandSize) {
        this.brandSize = brandSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHip() {
        return hip;
    }

    public void setHip(Integer hip) {
        this.hip = hip;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
