# Size It

This library is the sizeit library for MIQYAS. It is used for fashion e-commerce to suggest sizes to users based on some questions. For more information about sizeit, please visit miqyas.net.

- Please contact us at to get your API key.

- The library will install a button that starts a popup for size reccomendation.

- You will get all the product sizes by calling "FindMySizeActivity.getAllSizes()" function.

- You will get perticular product size by calling "FindMySizeActivity.getSizeByAttribute('X')" function.
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
    startActivityForResult(intent, request_code);

X - You will need to add your current user id here.

Y - You will need to add your product miqyas_fit(product attribute) here.

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
//user_id = pass user id
//hassizes = true if sizes available else false
//data = other string data if needed
SizeitUtils.initUsers(this, "user_id", "hassizes");
SizeitUtils.initUsers(this, "user_id", "hassizes", "data");

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

```javascript
//Call this function when the user opens the product details screen.
SizeitUtils.visitProduct(this, "1234", "Skirts-S,M,L,XL,XXL", FindMySizeActivity.hasSizes());

//Call this function when the user adds the product to the cart.
SizeitUtils.addProductToCart(this, "1234", "Skirts-S,M,L,XL,XXL", FindMySizeActivity.hasSizes());

//Call this function when the user buys the product.
SizeitUtils.buyProduct(this, "1234", "Skirts-S,M,L,XL,XXL", FindMySizeActivity.hasSizes());

//Call this function when the user returns the product.
SizeitUtils.returnProduct(this, "1234", "Skirts-S,M,L,XL,XXL", FindMySizeActivity.hasSizes());

//Call this funtion when you need to add custom event when ever you want.
Bundle bundle = new Bundle();
bundle.putString("param1", "value1");
bundle.putInt("param2", 123);
bundle.putBoolean("param3", true);
SizeitUtils.addCustomEvent(this, "1234", bundle);
```




# Localization

You will set up your localization using below code:

```javascript
//If you want to set the English language then add the below the line on your class before call the FindMySizeActivity.
Preferences.getPreferences(this).putString(Constants.language, Constants.language_english);

//If you want to set the Arabic language then add the below the line on your class before call the FindMySizeActivity.
Preferences.getPreferences(this).putString(Constants.language, Constants.language_arabic);
```
