# Find My Size

This library is helping me to find what is my current size for particular products like, Skirt, T-shirts, Tops, and others.

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


##### More information about this library:

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


##### 3. If you want to check the sizes are available in local storage then you can it via below functions.

    /**
     * IF YOU WANT TO CHECK SIZES ARE AVAILABLE ON LOCALE OR NOT
     * THEN CALL THE "FindMySizeActivity.hasSizes()" FUNCTION.
     *
     * IT WILL RETURN TRUE IF IT IS AVAILABLE ELSE IT IS RETURN FALSE.
     */
    public void hashProductSizes() {
        boolean isAvailable = FindMySizeActivity.hasSizes();
    }
