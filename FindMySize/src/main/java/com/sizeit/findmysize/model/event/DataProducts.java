package com.sizeit.findmysize.model.event;

public class DataProducts {

    private boolean productSkuAbTest;
    private String product_id;
    private String currency;
    private String price;

    public boolean isProductSkuAbTest() {
        return productSkuAbTest;
    }

    public void setProductSkuAbTest(boolean productSkuAbTest) {
        this.productSkuAbTest = productSkuAbTest;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}