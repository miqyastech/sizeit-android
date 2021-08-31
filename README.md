# Size It

[![](https://jitpack.io/v/miqyastech/sizeit-android-SDK.svg)](https://jitpack.io/#miqyastech/sizeit-android-SDK)

This library is the sizeit library for MIQYAS. It is used for fashion e-commerce to suggest sizes to users based on some questions. For more information about sizeit, please visit miqyas.net.

- Please contact us at to get your API key.

- The library will install a button that starts a popup for size recommendation.

- You will get all the product sizes by calling "FindMySizeActivity.getAllSizes()" function.

- You will get particular product size by calling "FindMySizeActivity.getSizeByAttribute('X')" function.
It will return X product size if it is available in locale storage (e.g., user) otherwise it will return empty.
X - product name(miqyas_fit).

- You will check the product sizes are available or not in the local storage by calling "FindMySizeActivity.hasSizes()" function.
It will return True if sizes available on storage otherwise it will return false.




## Steps to implement library

##### 1. Add below code to your project level build.gradle file.

```javascript
allprojects {
  repositories {
    jcenter()
    maven { url "https://jitpack.io" }
  }
}
```

##### 2. Add below code to your app level build.gradle file.

```javascript
dependencies {
  implementation 'com.github.miqyastech:sizeit-android:Tag'
}
```

##### Great!!
You have successfully implemented sizeit library on your project. Now you can access all the library functionalities.




## More information about this library:

##### 1. If you want to check if sizes are available in local storage use the function below:

    /**
     * IF YOU WANT TO CHECK SIZES ARE AVAILABLE ON LOCALE OR NOT
     * THEN CALL THE "FindMySizeActivity.hasSizes()" FUNCTION.
     *
     * IT WILL RETURN TRUE IF IT IS AVAILABLE ELSE IT IS RETURN FALSE.
     */
    public void hashProductSizes() {
        boolean isAvailable = FindMySizeActivity.hasSizes();
    }
    
    
##### 2. If you want to get a particular product size by product name (miqyas_fit attribute value), then you will get it via the function below:

    /**
     * IF YOU WANT TO GET PARTICULAR PRODUCT SIZE BY PRODUCT NAME
     * THEN CALL THE "FindMySizeActivity.getSizeByAttribute('X')" FUNCTION.
     *
     * IT WILL RETURN PARTICULAR PRODUCT SIZE FROM LOCALE IF AVAILABLE.
     */
    private void getSizeByProductName(){
        FindMySizeActivity.getSizeByAttribute("X");
    }
	

X - Add product miqyas_fit.

This function will return the product size of the user as per inputted information if available in local storage.


##### 3. If you want to open user inputs screens like height, weight, age, etc.. then you can do it via the below code.

	Intent intent = new Intent(this, FindMySizeActivity.class);
    intent.putExtra(Constants.user_id, "X");
    intent.putExtra(Constants.miqyas_fit, "Y");
    intent.putExtra(Constants.api_key, "api_key");
    startActivityForResult(intent, request_code);

X - You will need to add your current user id here.

Y - You will need to add your product miqyas_fit(product attribute) here.

api_key - You will need to add api_key to get all product information.

It will return the miqyas_fit(product attribute) size into onActivityResult function if it is available in local storage. 

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_code) {
            if (resultCode == RESULT_OK) {
                if (data != null && data.hasExtra(FindMySizeActivity.CURRENT_SIZE)) {
                    if (!data.getStringExtra(FindMySizeActivity.CURRENT_SIZE).equals("")) {
                        Toast.makeText(this, "Your current size is " + data.getStringExtra(FindMySizeActivity.CURRENT_SIZE), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }


# Localization

You will set up your localization using below code:

```javascript
//If you want to set the English language then add the below the line on your class before call the FindMySizeActivity.
Preferences.getPreferences(this).putString(Constants.language, Constants.language_english);

//If you want to set the Arabic language then add the below the line on your class before call the FindMySizeActivity.
Preferences.getPreferences(this).putString(Constants.language, Constants.language_arabic);
```


# Event Tracking

There are several types of events added in this library that described below.
1. 'getFitted' = Call this event when user click on "Find My Size" button.
2. 'visitHome' = Call this event every time when user open the application.
3. 'visitProduct' = Call this event when user visit/open product.
4. 'addToCart' = Call this event when user add product to the cart.
5. 'buy' = Call this event when user buy the product.
6. 'return' = Call this event when user return the product.

## Brief description about how to call event with required parameters.

##### Required parameters:

<table>
    <thead>
      <tr>
        <th>Parameters</th>
        <th>Value</th>
        <th>Description</th>
      </tr>
    </thead>
    <tbody>
        <tr>
            <td>eventType</td>
            <td>getFitted, visitHome, visitProduct,
            addToCart, buy, return</td>
            <td>Type of events </td>
        </tr>
        <tr>
            <td>projectName</td>
            <td>Project name i.e. abc</td>
            <td>Write name of your app</td>
        </tr>
        <tr>
            <td>origin</td>
            <td>Website URL i.e. https://www.website.com</td>
            <td>Write name of your Website name</td>
        </tr>
        <tr>
            <td>platform</td>
            <td>Platform name i.e. iOS</td>
            <td>Write platform name (i.e. web, android,
            iOS, etc...)</td>
        </tr>
        <tr>
            <td>userId</td>
            <td>User id i.e 100</td>
            <td>Enter userId if user is login otherwise pass empty or
            null library will pass deviceId if user id is not available</td>
        </tr>
        <tr>
            <td>products</td>
            <td>
                <code>"products": [
                        {
                          "product_id": "AS123",
                          "productSkuAbTest": true
                          "price": "100"
                          "currency": "SAR"
                        },
                        {
                          "product_id": "AS987",
                          "productSkuAbTest": false
                          "price": "160"
                          "currency": "USD"
                        }
                      ]
                </code>
            </td>
            <td>
                This is the products list when user visit, buy, return products then add it like this format.
                <br/>
                <br/>
                <b>product_id</b> - product unique id
                <br/>
                <b>productSkuAbTest</b> - if user size for this product n1 exists the set true else set else: false
                <br/>
                <b>price</b> - product price.
                <br/>
                <b>currency</b> - product currency type. i.e.
                <br/>
                <code>
                if product_has_size == true
                    return true
                else
                    return true
                </code>
            </td>
        </tr>
        <tr>
            <td>orderValue</td>
            <td>Order Value i.e 100</td>
            <td>Enter total value of the order.</td>
        </tr>
        <tr>
            <td>abTest</td>
            <td>hash size of not value i.e true, false</td>
            <td>If user tried to find his size from "find my size" button then
            pass true else false</td>
        </tr>
        <tr>
            <td>region</td>
            <td>Name of the region (US)</td>
            <td>Enter name of the region</td>
        </tr>
    </tbody>
</table>

##### How to call events from the application

##### 1. getFitted event - call this event when user click on find my size button.

```javascript
//project_name = enter your application name here i.e. SizeIt
//website_url = enter your website url here i.e. https://www.website.com
//user_id = enter your userId here is user is login in the app otherwise pass empty or null.
EventUtils.findMySize(context, "project_name", "website_url", "user_id");
```

##### 2. visitHome event - call this event when user visit home screen.

```javascript
EventUtils.visitHome(context, "project_name", "website_url", "user_id");
```

##### 3. visitProduct event - call this event when user visit product details screen.

```javascript
//products_list = Enter product list here that user visited, add to cart, buy or return
//This is the array format value like below example.

List<DataProducts> productsList = new ArrayList<>();
DataProducts products = new DataProducts();
products.setSku("FU21-0000012B");
products.setProductSkuAbTest(true);
productsList.add(products);
DataProducts products1 = new DataProducts();
products1.setSku("FU21-0000097A");
products1.setProductSkuAbTest(false);
productsList.add(products1);

EventUtils.visitProduct(context, "project_name", "website_url", "user_id", "products_list", "order_value");
```

##### 4. addToCart event - call this event when user add product to cart.

```javascript
EventUtils.addToCart(context, "project_name", "website_url", "user_id", "products_list", "order_value");
```

##### 5. buy event - call this event when user buy product.

```javascript
EventUtils.buyProduct(context, "project_name", "website_url", "user_id", "products_list", "order_value");
```

##### 6. return event - call this event when user return product.

```javascript
EventUtils.returnProduct(context, "project_name", "website_url", "user_id", "products_list", "order_value");
```

# Facebook Events

You can track your user and product events. There are several functions that use to track user events.

- How many users are visits the application (With size, Without size).
- Which user visits which product (With size, Without size).
- Home many users add the product to his cart (With size, Without size).
- How many users buy the product (With size, Without size).
- How many users return the product (With size, Without size).
- Custom events (You can add your custom event if you want).

## Steps to implement facebook events with this library. 

Please configure the facebook in your application by using https://developers.facebook.com/docs/app-events/getting-started-app-events-android link.

##### 1. Add below code to your project level build.gradle file.
```javascript
allprojects {
  repositories {
    jcenter()
    mavenCentral()
    maven { url "https://jitpack.io" }
  }
}
```

##### 2. Add below code to your app level build.gradle file.

```javascript
dependencies {
  implementation 'com.facebook.android:facebook-android-sdk:[5,6)'
}
```

##### 3. Add facebook id in string.xml file

```javascript
<string name="facebook_app_id">[APP_ID]</string>
<string name="fb_login_protocol_scheme">fb[APP_ID]</string>
```

##### 4. Add a meta-data element to the application element In the app/manifests/AndroidManifest.xml file.

```javascript
<application android:label="@string/app_name" ...>
    ...
    <meta-data android:name="com.facebook.sdk.ApplicationId" android:value="@string/facebook_app_id"/>
    ...
</application>
```

Now, You can add your events to Facebook by calling functions that are available in "SizeitUtils.class" file in the library section.

Example to add an event:

##### 1. Call this function when the user opens the app.
```javascript
//context = pass your context
//user_id = pass user id
//hassizes = true if sizes available else false
//data = other string data if needed
SizeitUtils.initUsers(context, "user_id", "hassizes");
SizeitUtils.initUsers(context, "user_id", "hassizes", "data");
example:
SizeitUtils.initUsers(this, "1", FindMySizeActivity.hasSizes(this));
SizeitUtils.initUsers(this, "1", FindMySizeActivity.hasSizes(this), "");

/**
* ADD USER INTO FB EVENT
*
* @param activity
* @param userID   - CURRENT LOGGED IN USER ID.
* @param hashSize - PASS TRUE IF USER HAS SIZE.
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
```

##### 2. Call this function when the user opens the product details screen.
```javascript
//context = pass your context
//user_id = pass user id
//miqyas_fit = pass miqyas_fit/product_id
//hassizes = true if sizes available of miqyas_fit else false 
//data = other string data if needed 
SizeitUtils.visitProduct(context, "user_id", "miqyas_fit", "hassizes");
SizeitUtils.visitProduct(context, "user_id", "miqyas_fit", "hassizes", "");
example:
SizeitUtils.visitProduct(this, "1", "Skirts-S,M,L,XL,XXL", FindMySizeActivity.isAttributeSizeAvailable(this, "Skirts-S,M,L,XL,XXL"));
SizeitUtils.visitProduct(this, "1", "Skirts-S,M,L,XL,XXL", FindMySizeActivity.isAttributeSizeAvailable(this, "Skirts-S,M,L,XL,XXL"), "");

/**
* ADD VISIT PRODUCT EVENT
*
* @param activity
* @param userID      - CURRENT LOGGED IN USER ID.
* @param product_sku - PRODUCT NAME OF PRODUCT ID
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
```

##### 3. Call this function when the user adds the product to the cart.
```javascript
//context = pass your context
//user_id = pass user id
//miqyas_fit = pass miqyas_fit/product_id
//hassizes = true if sizes available of miqyas_fit else false 
//data = other string data if needed 
SizeitUtils.addProductToCart(context, "user_id", "miqyas_fit", "hassizes");
SizeitUtils.addProductToCart(context, "user_id", "miqyas_fit", "hassizes", "");
example:
SizeitUtils.addProductToCart(this, "1", "Skirts-S,M,L,XL,XXL", FindMySizeActivity.isAttributeSizeAvailable(this, "Skirts-S,M,L,XL,XXL"));
SizeitUtils.addProductToCart(this, "1", "Skirts-S,M,L,XL,XXL", FindMySizeActivity.isAttributeSizeAvailable(this, "Skirts-S,M,L,XL,XXL"), "");


/**
* ADD PRODUCT TO CART EVENT
*
* @param activity
* @param userID      - CURRENT LOGGED IN USER ID.
* @param product_sku - PRODUCT NAME OF PRODUCT ID
* @param hashSize    - PASS TRUE IF USER HAS SIZE like, M, L, X, XL, XXL.
* @param data        - YOU CAN ADD OTHER STRING/JSON INFORMATION LIKE, SKU ID, RECEIPT NUMBER, ORDER DETAILS, ETC.. FOR FUTURE USE.
*/
public static void addProductToCart(Activity activity, String userID, String product_sku, boolean hashSize, String data) {
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
```

##### 4. Call this function when the user buys the product.
```javascript
//context = pass your context
//user_id = pass user id
//miqyas_fit = pass miqyas_fit/product_id
//hassizes = true if sizes available of miqyas_fit else false 
//data = other string data if needed 
SizeitUtils.buyProduct(context, "user_id", "miqyas_fit", "hassizes");
SizeitUtils.buyProduct(context, "user_id", "miqyas_fit", "hassizes", "");
example:
SizeitUtils.buyProduct(this, "1", "Skirts-S,M,L,XL,XXL", FindMySizeActivity.isAttributeSizeAvailable(this, "Skirts-S,M,L,XL,XXL"));
SizeitUtils.buyProduct(this, "1", "Skirts-S,M,L,XL,XXL", FindMySizeActivity.isAttributeSizeAvailable(this, "Skirts-S,M,L,XL,XXL"), "");

/**
* BUY PRODUCT EVENT
*
* @param activity
* @param userID      - CURRENT LOGGED IN USER ID.
* @param hashSize    - PASS TRUE IF USER HAS SIZE like, M, L, X, XL, XXL.
* @param product_sku - PRODUCT NAME OF PRODUCT ID
* @param data        - YOU CAN ADD OTHER STRING/JSON INFORMATION LIKE, SKU ID, RECEIPT NUMBER, ORDER DETAILS, ETC.. FOR FUTURE USE.
*/
public static void buyProduct(Activity activity, String userID, String product_sku, boolean hashSize, String data) {
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
```

##### 5. Call this function when the user returns the product.
```javascript
//context = pass your context
//user_id = pass user id
//miqyas_fit = pass miqyas_fit/product_id
//hassizes = true if sizes available of miqyas_fit else false 
//data = other string data if needed 
SizeitUtils.returnProduct(context, "user_id", "miqyas_fit", "hassizes");
SizeitUtils.returnProduct(context, "user_id", "miqyas_fit", "hassizes", "");
example:
SizeitUtils.returnProduct(this, "1", "Skirts-S,M,L,XL,XXL", FindMySizeActivity.isAttributeSizeAvailable(this, "Skirts-S,M,L,XL,XXL"));
SizeitUtils.returnProduct(this, "1", "Skirts-S,M,L,XL,XXL", FindMySizeActivity.isAttributeSizeAvailable(this, "Skirts-S,M,L,XL,XXL"), "");

/**
* RETURN PRODUCT EVENT
*
* @param activity
* @param userID      - CURRENT LOGGED IN USER ID.
* @param hashSize    - PASS TRUE IF USER HAS SIZE like, M, L, X, XL, XXL.
* @param product_sku - PRODUCT NAME OF PRODUCT ID
* @param data        - YOU CAN ADD OTHER STRING/JSON INFORMATION LIKE, SKU ID, RECEIPT NUMBER, ORDER DETAILS, ETC.. FOR FUTURE USE.
*/
public static void returnProduct(Activity activity, String userID, String product_sku, boolean hashSize, String data) {
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
```

##### 5. Call this function when you need to add custom event when ever you want.
```javascript
//context = pass your context
//user_id = pass user id
//bundle = pass bundle
SizeitUtils.addCustomEvent(this, "user_id", bundle);
example:
Bundle bundle = new Bundle();
bundle.putString("miqyas_fit", "Skirts-S,M,L,XL,XXL");
SizeitUtils.addCustomEvent(this, "1", bundle);
```
