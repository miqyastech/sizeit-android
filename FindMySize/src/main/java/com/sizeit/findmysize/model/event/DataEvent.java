package com.sizeit.findmysize.model.event;

import java.util.List;

public class DataEvent {

    private boolean abTest;
    private String origin;
    private String eventType;
    private String projectName;
    private String region;
    private String userId;
    private String orderValue;
    private String platform;
    private List<DataProducts> products;

    public boolean isAbTest() {
        return abTest;
    }

    public void setAbTest(boolean abTest) {
        this.abTest = abTest;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public List<DataProducts> getProducts() {
        return products;
    }

    public void setProducts(List<DataProducts> products) {
        this.products = products;
    }
}