package com.sizeit.findmysize.model.event;

import com.google.gson.annotations.SerializedName;

public class ResponseEvent {

    @SerializedName("success")
    private boolean success;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}