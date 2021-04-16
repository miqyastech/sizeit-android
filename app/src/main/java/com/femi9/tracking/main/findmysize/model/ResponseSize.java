package com.femi9.tracking.main.findmysize.model;

import com.google.gson.annotations.SerializedName;

public class ResponseSize {

    @SerializedName("data")
    private ResponseData data;

    @SerializedName("success")
    private boolean success;

    public void setData(ResponseData data) {
        this.data = data;
    }

    public ResponseData getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}