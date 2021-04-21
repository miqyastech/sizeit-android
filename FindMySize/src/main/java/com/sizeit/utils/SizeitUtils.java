package com.sizeit.utils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.appevents.AppEventsLogger;

public class SizeitUtils {

    private static final String INIT_USER = "INIT_USER";
    private static final String PRODUCT_VISIT = "PRODUCT_VISIT";
    private static final String PRODUCT_ADD_TO_CART = "PRODUCT_ADD_TO_CART";
    private static final String BUY_PRODUCT = "BUY_PRODUCT";
    private static final String RETURN_PRODUCT = "RETURN_PRODUCT";
    private static final String CUSTOM = "CUSTOM";

    public static void makeToast(Activity activity, String text) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * ADD USER INTO FB EVENT
     *
     * @param activity
     * @param userID   - CURRENT LOGGED IN USER ID.
     * @param hashSize - PASS TRUE IF USER HAS SIZE like, M, L, X, XL, XXL.
     * @param data     - YOU CAN ADD OTHER STRING/JSON INFORMATION FOR FUTURE USE.
     */
    public static void initUsers(Activity activity, String userID, boolean hashSize, String data) {
        AppEventsLogger logger = AppEventsLogger.newLogger(activity);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.user_id, userID);
        bundle.putInt(Constants.hash_size, hashSize ? 1 : 0);
        bundle.putString(Constants.data, data);
        logger.logEvent(INIT_USER, bundle);
    }

    public static void initUsers(Activity activity, String userID, boolean hashSize) {
        initUsers(activity, userID, hashSize, null);
    }


    /**
     * ADD VISIT PRODUCT EVENT
     *
     * @param activity
     * @param userID      - CURRENT LOGGED IN USER ID.
     * @param product_sku - PASS TRUE IF USER HAS SIZE like, M, L, X, XL, XXL.
     * @param hashSize    - PASS TRUE IF USER HAS SIZE like, M, L, X, XL, XXL.
     * @param data        - YOU CAN ADD OTHER STRING/JSON INFORMATION FOR FUTURE USE.
     */
    public static void visitProduct(Activity activity, String userID, String product_sku,
                                    boolean hashSize, String data) {
        AppEventsLogger logger = AppEventsLogger.newLogger(activity);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.user_id, userID);
        bundle.putInt(Constants.hash_size, hashSize ? 1 : 0);
        bundle.putString(Constants.product_sku, product_sku);
        bundle.putString(Constants.data, data);
        logger.logEvent(PRODUCT_VISIT, bundle);
    }

    public static void visitProduct(Activity activity, String userID, String product, boolean hashSize) {
        visitProduct(activity, userID, product, hashSize, null);
    }


    /**
     * ADD PRODUCT TO CART EVENT
     *
     * @param activity
     * @param userID      - CURRENT LOGGED IN USER ID.
     * @param product_sku - PRODUCT NAME OF PRODUCT ID
     * @param hashSize    - PASS TRUE IF USER HAS SIZE like, M, L, X, XL, XXL.
     * @param data        - YOU CAN ADD OTHER STRING/JSON INFORMATION LIKE, SKU ID, RECEIPT NUMBER, ORDER DETAILS, ETC.. FOR FUTURE USE.
     */
    public static void addProductToCart(Activity activity, String userID, String product_sku,
                                        boolean hashSize, String data) {
        AppEventsLogger logger = AppEventsLogger.newLogger(activity);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.user_id, userID);
        bundle.putInt(Constants.hash_size, hashSize ? 1 : 0);
        bundle.putString(Constants.product_sku, product_sku);
        bundle.putString(Constants.data, data);
        logger.logEvent(PRODUCT_ADD_TO_CART, bundle);
    }

    public void addProductToCart(Activity activity, String userID, String product_sku, boolean hashSize) {
        addProductToCart(activity, userID, product_sku, hashSize, null);
    }


    /**
     * BUY PRODUCT EVENT
     *
     * @param activity
     * @param userID      - CURRENT LOGGED IN USER ID.
     * @param hashSize    - PASS TRUE IF USER HAS SIZE like, M, L, X, XL, XXL.
     * @param product_sku - PRODUCT NAME OF PRODUCT ID
     * @param data        - YOU CAN ADD OTHER STRING/JSON INFORMATION LIKE, SKU ID, RECEIPT NUMBER, ORDER DETAILS, ETC.. FOR FUTURE USE.
     */
    public static void buyProduct(Activity activity, String userID, String product_sku,
                                  boolean hashSize, String data) {
        AppEventsLogger logger = AppEventsLogger.newLogger(activity);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.user_id, userID);
        bundle.putInt(Constants.hash_size, hashSize ? 1 : 0);
        bundle.putString(Constants.product_sku, product_sku);
        bundle.putString(Constants.data, data);
        logger.logEvent(BUY_PRODUCT, bundle);
    }

    public static void buyProduct(Activity activity, String userID, String product_sku, boolean hashSize) {
        buyProduct(activity, userID, product_sku, hashSize);
    }

    /**
     * RETURN PRODUCT EVENT
     *
     * @param activity
     * @param userID      - CURRENT LOGGED IN USER ID.
     * @param hashSize    - PASS TRUE IF USER HAS SIZE like, M, L, X, XL, XXL.
     * @param product_sku - PRODUCT NAME OF PRODUCT ID
     * @param data        - YOU CAN ADD OTHER STRING/JSON INFORMATION LIKE, SKU ID, RECEIPT NUMBER, ORDER DETAILS, ETC.. FOR FUTURE USE.
     */
    public static void returnProduct(Activity activity, String userID, String product_sku,
                                     boolean hashSize, String data) {
        AppEventsLogger logger = AppEventsLogger.newLogger(activity);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.user_id, userID);
        bundle.putInt(Constants.hash_size, hashSize ? 1 : 0);
        bundle.putString(Constants.product_sku, product_sku);
        bundle.putString(Constants.data, data);
        logger.logEvent(RETURN_PRODUCT, bundle);
    }

    public static void returnProduct(Activity activity, String userID, String product_sku, boolean hashSize) {
        returnProduct(activity, userID, product_sku, hashSize);
    }


    /**
     * @param activity
     * @param userID   - CURRENT LOGGED IN USER ID.
     * @param bundle   - ADD CUSTOM PARAMS WHAT EVER YOU WANT.
     */
    public static void addCustomEvent(Activity activity, String userID, Bundle bundle) {
        AppEventsLogger logger = AppEventsLogger.newLogger(activity);
        bundle.putString(Constants.user_id, userID);
        logger.logEvent(CUSTOM, bundle);
    }
}