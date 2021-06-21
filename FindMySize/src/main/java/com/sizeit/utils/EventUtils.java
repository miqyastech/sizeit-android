package com.sizeit.utils;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.sizeit.findmysize.FindMySizeActivity;
import com.sizeit.findmysize.model.event.DataEvent;
import com.sizeit.findmysize.model.event.DataProducts;
import com.sizeit.findmysize.model.event.ResponseEvent;
import com.sizeit.findmysize.network.APIClient;
import com.sizeit.findmysize.network.MainApi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventUtils {

    private static final String FIND_MY_SIZE = "getFitted";
    private static final String VISIT_HOME = "visitHome";
    private static final String VISIT_PRODUCT = "visitProduct";
    private static final String ADD_TO_CART = "addToCart";
    private static final String BUY_PRODUCT = "buy";
    private static final String RETURN_PRODUCT = "return";

    private static final String platformAndroid = "android";
    private static MainApi apiInterface;

    /**
     * CALL THIS FUNCTION WHEN USER CLICK ON "FIND MY SIZE" BUTTON.
     *
     * <p>
     * {
     * "eventType": "getFitted",
     * "projectName": "Test", - enter your project name here
     * "origin": "https://storename.com", - enter your origin/website url
     * "platform": "android",
     * "userId": "userId", - If user is login then pass userId other wise pass null or empty
     * "products": [
     * {
     * "sku": "sku_id_n1", - enter n1 product sku id
     * "productSkuAbTest": - if user size for this product exists: true, else: false
     * },
     * {
     * "sku": "sku_id_n2", - enter n2 product sku id
     * "productSkuAbTest": - if user size for this product exists: true, else: false
     * }
     * ],
     * "orderValue": "product_value", - enter value/price of this product
     * "abTest": "true/false", - if user has no size the pass = false, else: true
     * "region": "region name" - enter region of device
     * }
     */
    public static void findMySize(Activity activity, String projectName,
                                  String origin, String userId) {
        apiInterface = APIClient.getEventClient(activity).create(MainApi.class);
        DataEvent dataEvent = new DataEvent();
        dataEvent.setEventType(FIND_MY_SIZE);
        dataEvent.setProjectName(projectName);
        dataEvent.setOrigin(origin);
        dataEvent.setPlatform(platformAndroid);
        dataEvent.setUserId(userId == null || userId.trim().equals("") ? getDeviceID(activity) : userId);
        List<DataProducts> products = new ArrayList<>();
        dataEvent.setProducts(products);
        dataEvent.setOrderValue("");
        dataEvent.setAbTest(FindMySizeActivity.hasSizes(activity));
        dataEvent.setRegion(getUserCountry(activity));
        addEvent(dataEvent);
    }

    /**
     * CALL THIS FUNCTION WHEN USER OPEN THE APP.
     *
     * <p>
     * {
     * "eventType": "visitHome",
     * "projectName": "Test", - enter your project name here
     * "origin": "https://storename.com", - enter your origin/website url
     * "platform": "android",
     * "userId": "userId", - If user is login then pass userId other wise pass null or empty
     * "products": [
     * {
     * "sku": "sku_id_n1", - enter n1 product sku id
     * "productSkuAbTest": - if user size for this product exists: true, else: false
     * },
     * {
     * "sku": "sku_id_n2", - enter n2 product sku id
     * "productSkuAbTest": - if user size for this product exists: true, else: false
     * }
     * ],
     * "orderValue": "product_value", - enter value/price of this product
     * "abTest": "true/false", - if user has no size the pass = false, else: true
     * "region": "region name" - enter region of device
     * }
     */
    public static void visitHome(Activity activity, String projectName, String origin, String userId) {
        apiInterface = APIClient.getEventClient(activity).create(MainApi.class);
        DataEvent dataEvent = new DataEvent();
        dataEvent.setEventType(VISIT_HOME);
        dataEvent.setProjectName(projectName);
        dataEvent.setOrigin(origin);
        dataEvent.setPlatform(platformAndroid);
        dataEvent.setUserId(userId == null || userId.trim().equals("") ? getDeviceID(activity) : userId);
        List<DataProducts> products = new ArrayList<>();
        dataEvent.setProducts(products);
        dataEvent.setOrderValue("");
        dataEvent.setAbTest(FindMySizeActivity.hasSizes(activity));
        dataEvent.setRegion(getUserCountry(activity));
        addEvent(dataEvent);
    }

    /**
     * CALL THIS FUNCTION WHEN USER VISIT PRODUCT.
     *
     * <p>
     * {
     * "eventType": "visitProduct",
     * "projectName": "Test", - enter your project name here
     * "origin": "https://storename.com", - enter your origin/website url
     * "platform": "android",
     * "userId": "userId", - If user is login then pass userId other wise pass null or empty
     * "products": [
     * {
     * "sku": "sku_id_n1", - enter n1 product sku id
     * "productSkuAbTest": - if user size for this product n1 exists the set true else set else: false
     * },
     * {
     * "sku": "sku_id_n2", - enter n2 product sku id
     * "productSkuAbTest": if user size for this product n2 exists the set true else set else: false
     * }
     * ],
     * "orderValue": "product_value", - enter total value/price of those products
     * "abTest": "true/false", - if user has no size the pass = false, else: true
     * "region": "region name" - enter region of device
     * }
     */
    public static void visitProduct(Activity activity, String projectName,
                                    String origin, String userId,
                                    List<DataProducts> productsList, String orderValue) {
        apiInterface = APIClient.getEventClient(activity).create(MainApi.class);
        DataEvent dataEvent = new DataEvent();
        dataEvent.setEventType(VISIT_PRODUCT);
        dataEvent.setProjectName(projectName);
        dataEvent.setOrigin(origin);
        dataEvent.setPlatform(platformAndroid);
        dataEvent.setUserId(userId == null || userId.trim().equals("") ? getDeviceID(activity) : userId);
        dataEvent.setProducts(productsList);
        dataEvent.setOrderValue(orderValue);
        dataEvent.setAbTest(FindMySizeActivity.hasSizes(activity));
        dataEvent.setRegion(getUserCountry(activity));
        addEvent(dataEvent);
    }

    /**
     * CALL THIS FUNCTION WHEN USER ADD PRODUCT TO CART.
     *
     * <p>
     * {
     * "eventType": "addToCart",
     * "projectName": "Test", - enter your project name here
     * "origin": "https://storename.com", - enter your origin/website url
     * "platform": "android",
     * "userId": "userId", - If user is login then pass userId other wise pass null or empty
     * "products": [
     * {
     * "sku": "sku_id_n1", - enter n1 product sku id
     * "productSkuAbTest": - if user size for this product n1 exists the set true else set else: false
     * },
     * {
     * "sku": "sku_id_n2", - enter n2 product sku id
     * "productSkuAbTest": if user size for this product n2 exists the set true else set else: false
     * }
     * ],
     * "orderValue": "product_value", - enter total value/price of those products
     * "abTest": "true/false", - if user has no size the pass = false, else: true
     * "region": "region name" - enter region of device
     * }
     */
    public static void addToCart(Activity activity, String projectName,
                                 String origin, String userId,
                                 List<DataProducts> productsList, String orderValue) {
        apiInterface = APIClient.getEventClient(activity).create(MainApi.class);
        DataEvent dataEvent = new DataEvent();
        dataEvent.setEventType(ADD_TO_CART);
        dataEvent.setProjectName(projectName);
        dataEvent.setOrigin(origin);
        dataEvent.setPlatform(platformAndroid);
        dataEvent.setUserId(userId == null || userId.trim().equals("") ? getDeviceID(activity) : userId);
        dataEvent.setProducts(productsList);
        dataEvent.setOrderValue(orderValue);
        dataEvent.setAbTest(FindMySizeActivity.hasSizes(activity));
        dataEvent.setRegion(getUserCountry(activity));
        addEvent(dataEvent);
    }

    /**
     * CALL THIS FUNCTION WHEN USER BUY PRODUCT.
     *
     * <p>
     * {
     * "eventType": "buy",
     * "projectName": "Test", - enter your project name here
     * "origin": "https://storename.com", - enter your origin/website url
     * "platform": "android",
     * "userId": "userId", - If user is login then pass userId other wise pass null or empty
     * "products": [
     * {
     * "sku": "sku_id_n1", - enter n1 product sku id
     * "productSkuAbTest": - if user size for this product n1 exists the set true else set else: false
     * },
     * {
     * "sku": "sku_id_n2", - enter n2 product sku id
     * "productSkuAbTest": if user size for this product n2 exists the set true else set else: false
     * }
     * ],
     * "orderValue": "product_value", - enter total value/price of those products
     * "abTest": "true/false", - if user has no size the pass = false, else: true
     * "region": "region name" - enter region of device
     * }
     */
    public static void buyProduct(Activity activity, String projectName,
                                  String origin, String userId,
                                  List<DataProducts> productsList, String orderValue) {
        apiInterface = APIClient.getEventClient(activity).create(MainApi.class);
        DataEvent dataEvent = new DataEvent();
        dataEvent.setEventType(BUY_PRODUCT);
        dataEvent.setProjectName(projectName);
        dataEvent.setOrigin(origin);
        dataEvent.setPlatform(platformAndroid);
        dataEvent.setUserId(userId == null || userId.trim().equals("") ? getDeviceID(activity) : userId);
        dataEvent.setProducts(productsList);
        dataEvent.setOrderValue(orderValue);
        dataEvent.setAbTest(FindMySizeActivity.hasSizes(activity));
        dataEvent.setRegion(getUserCountry(activity));
        addEvent(dataEvent);
    }

    /**
     * CALL THIS FUNCTION WHEN USER RETURN PRODUCT.
     *
     * <p>
     * {
     * "eventType": "return",
     * "projectName": "Test", - enter your project name here
     * "origin": "https://storename.com", - enter your origin/website url
     * "platform": "android",
     * "userId": "userId", - If user is login then pass userId other wise pass null or empty
     * "products": [
     * {
     * "sku": "sku_id_n1", - enter n1 product sku id
     * "productSkuAbTest": - if user size for this product n1 exists the set true else set else: false
     * },
     * {
     * "sku": "sku_id_n2", - enter n2 product sku id
     * "productSkuAbTest": if user size for this product n2 exists the set true else set else: false
     * }
     * ],
     * "orderValue": "product_value", - enter total value/price of those products
     * "abTest": "true/false", - if user has no size the pass = false, else: true
     * "region": "region name" - enter region of device
     * }
     */
    public static void returnProduct(Activity activity, String projectName,
                                     String origin, String userId,
                                     List<DataProducts> productsList, String orderValue) {
        apiInterface = APIClient.getEventClient(activity).create(MainApi.class);
        DataEvent dataEvent = new DataEvent();
        dataEvent.setEventType(RETURN_PRODUCT);
        dataEvent.setProjectName(projectName);
        dataEvent.setOrigin(origin);
        dataEvent.setPlatform(platformAndroid);
        dataEvent.setUserId(userId == null || userId.trim().equals("") ? getDeviceID(activity) : userId);
        dataEvent.setProducts(productsList);
        dataEvent.setOrderValue(orderValue);
        dataEvent.setAbTest(FindMySizeActivity.hasSizes(activity));
        dataEvent.setRegion(getUserCountry(activity));
        addEvent(dataEvent);
    }

    private static void addEvent(DataEvent dataEvent) {
        Call<ResponseEvent> call3 = apiInterface.createEvent(dataEvent);
        call3.enqueue(new Callback<ResponseEvent>() {
            @Override
            public void onResponse(@NotNull Call<ResponseEvent> call, @NotNull Response<ResponseEvent> response) {
                ResponseEvent result = response.body();
                if (result != null && result.isSuccess()) {
                    // event successfully added.
                } else {
                    // fail to add event to db.
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseEvent> call, @NotNull Throwable t) {
                t.printStackTrace();
                call.cancel();
            }
        });
    }

    public static String getDeviceID(Activity activity) {
        return Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getUserCountry(Activity activity) {
        try {
            final TelephonyManager tm = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US);
            } else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}