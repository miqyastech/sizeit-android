package com.sizeit.findmysize.model.event;

public class DataProducts {

    private boolean productSkuAbTest;
    private String sku;

    public boolean isProductSkuAbTest() {
        return productSkuAbTest;
    }

    public void setProductSkuAbTest(boolean productSkuAbTest) {
        this.productSkuAbTest = productSkuAbTest;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}