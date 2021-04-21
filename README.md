# Find My Size

This library is use to find what is my current size for particular products like, Skirt, T-shirts, Tops, and others.

- At the first time it will take some input from users like Height(FT, CM), Weight(LBS, KG), Age, Stomach, and Hips.

- Call one API to get all the product sizes that available on our database and stored them into a locale for future uses.

- You will get all the product sizes by calling "FindMySizeActivity.getAllSizes()" function.

- You will get perticular product size by calling "FindMySizeActivity.getSizeByAttribute('X')" function.
It will return X product size if it is available on locale storage otherwise it will return empty.
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
  implementation 'com.github.ViradiyaAmit:findmysize:tag'
}
```

##### Great!!
You have successfully implemented library on your project. So, You can access all the library things into your project.




## More information about this library:

##### 1. If you want to check the sizes are available on local storage or not then you get it through via the below function.

    /**
     * IF YOU WANT TO CHECK SIZES ARE AVAILABLE ON LOCALE OR NOT
     * THEN CALL THE "FindMySizeActivity.hasSizes()" FUNCTION.
     *
     * IT WILL RETURN TRUE IF IT IS AVAILABLE ELSE IT IS RETURN FALSE.
     */
    public void hashProductSizes() {
        boolean isAvailable = FindMySizeActivity.hasSizes();
    }
    
    
##### 2. If you want to get a particular product size by product name (miqyas_fit), then you will get it via the below functions.

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

Please configure the facebook in your application by using https://developers.facebook.com/docs/app-events/getting-started-app-events-android/#log-manually link.

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
