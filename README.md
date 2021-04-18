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

	allprojects {
  		repositories {
    		jcenter()
   		 maven { url "https://jitpack.io" }
 		 }
	}

##### 2. Add below code to your app level build.gradle file.

	dependencies {
  	implementation 'com.github.ViradiyaAmit:findmysize:tag'
	}
