package com.sizeit.tracking;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sizeit.findmysize.FindMySizeActivity;
import com.sizeit.findmysize.model.DataSizes;
import com.sizeit.findmysize.model.event.DataProducts;
import com.sizeit.tracking.databinding.ActivityMainBinding;
import com.sizeit.utils.Constants;
import com.sizeit.utils.EventUtils;
import com.sizeit.utils.Preferences;
import com.sizeit.utils.SizeitUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int request_code = 100;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
//        getHashKey();
//        putEvent();
    }

    private void putEvent() {
        SizeitUtils.initUsers(this, "1",
                FindMySizeActivity.hasSizes(this), "");

        SizeitUtils.visitProduct(this, "1", "Skirts-S,M,L,XL,XXL",
                FindMySizeActivity.isAttributeSizeAvailable(this, "Skirts-S,M,L,XL,XXL"));

        SizeitUtils.addProductToCart(this, "1", "Skirts-S,M,L,XL,XXL",
                FindMySizeActivity.hasSizes(this), "");

        SizeitUtils.buyProduct(this, "1", "Skirts-S,M,L,XL,XXL",
                FindMySizeActivity.hasSizes(this), "");

        SizeitUtils.returnProduct(this, "1", "Skirts-S,M,L,XL,XXL",
                FindMySizeActivity.hasSizes(this), "");

        Bundle bundle = new Bundle();
        bundle.putString("param1", "value1");
        bundle.putInt("param2", 123);
        bundle.putBoolean("param3", true);
        SizeitUtils.addCustomEvent(this, "1234", bundle);
    }

    private void getHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", "KeyHash:" + Base64.encodeToString(md.digest(),
                        Base64.DEFAULT));
                Toast.makeText(getApplicationContext(), Base64.encodeToString(md.digest(),
                        Base64.DEFAULT), Toast.LENGTH_LONG).show();
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void onViewClicked(View view) {
        if (view == binding.btnFindMyFit) {
            //put language to set locale
            Preferences.getPreferences(this).putString(Constants.language, Constants.language_arabic);
            Intent intent = new Intent(this, FindMySizeActivity.class);
            intent.putExtra(Constants.miqyas_fit, "Outer Wear-FREE");
            intent.putExtra(Constants.api_key, "8MYHPT4-6364FZJ-Q2SW96P-GEBF9QP");
            intent.putExtra(Constants.user_id, "1234");
            startActivityForResult(intent, request_code);
        } else if (view == binding.btn4) {
            SizeitUtils.makeToast(this, FindMySizeActivity.getSizeByAttribute(
                    this, "Outer Wear-FREE"));
        } else if (view == binding.btn3) {
            SizeitUtils.makeToast(this, FindMySizeActivity.getSizeByAttribute(
                    this, "Pants-XS,S,M,L,XL"));
        } else if (view == binding.btn2) {
            SizeitUtils.makeToast(this, FindMySizeActivity.getSizeByAttribute(
                    this, "Skirts-S,M,L,XL,XXL"));
        } else if (view == binding.btn1) {
            SizeitUtils.makeToast(this, FindMySizeActivity.getSizeByAttribute(
                    this, "Pants-27,28,29,30,31"));
        }
    }

    /**
     * IF YOU WANT TO GET ALL THE PRODUCT SIZE FROM LOCALE
     * THEN CALL THE "FindMySizeActivity.getAllSizes()" FUNCTION.
     * <p>
     * IT WILL RETURN ALL SIZE IN STORAGE IF AVAILABLE.
     */
    public void getAllSizesFromLocale() {
        List<DataSizes> dataSizes = FindMySizeActivity.getAllSizes(this);
    }

    /**
     * IF YOU WANT TO GET PARTICULAR PRODUCT SIZE BY PRODUCT NAME
     * THEN CALL THE "FindMySizeActivity.getSizeByAttribute('X')" FUNCTION.
     * <p>
     * IT WILL RETURN PARTICULAR PRODUCT SIZE FROM LOCALE IF AVAILABLE.
     */
    private void getSizeByProductName() {
        FindMySizeActivity.getSizeByAttribute(this, "");
    }

    /**
     * IF YOU WANT TO CHECK SIZES ARE AVAILABLE ON LOCALE OR NOT
     * THEN CALL THE "FindMySizeActivity.hasSizes()" FUNCTION.
     * <p>
     * IT WILL RETURN TRUE IF IT IS AVAILABLE ELSE IT IS RETURN FALSE.
     */
    public void hashProductSizes() {
        boolean isAvailable = FindMySizeActivity.hasSizes(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_code) {
            if (resultCode == RESULT_OK) {
                if (data != null && data.hasExtra(FindMySizeActivity.CURRENT_SIZE)) {
                    if (!data.getStringExtra(FindMySizeActivity.CURRENT_SIZE).equals("")) {
                        Toast.makeText(this, "Your current size is " +
                                data.getStringExtra(FindMySizeActivity.CURRENT_SIZE), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}