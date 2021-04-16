package com.femi9.tracking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.femi9.tracking.databinding.ActivityMainBinding;
import com.femi9.tracking.main.findmysize.BaseActivity;
import com.femi9.tracking.main.findmysize.FindMySizeActivity;
import com.femi9.tracking.main.utils.Constants;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private final int request_code = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
    }

    public void onViewClicked(View view) {
        if (view == binding.btnFindMyFit) {
            Intent intent = new Intent(this, FindMySizeActivity.class);
//            intent.putExtra(Constants.miqyas_fit, "Pants-27,28,29,30,31");
            startActivityForResult(intent, request_code);
        } else if (view == binding.btn4) {
            Intent intent = new Intent(this, FindMySizeActivity.class);
            intent.putExtra(Constants.miqyas_fit, "Outer Wear-FREE");
            startActivityForResult(intent, request_code);
        } else if (view == binding.btn3) {
            Intent intent = new Intent(this, FindMySizeActivity.class);
            intent.putExtra(Constants.miqyas_fit, "Pants-XS,S,M,L,XL");
            startActivityForResult(intent, request_code);
        } else if (view == binding.btn2) {
            Intent intent = new Intent(this, FindMySizeActivity.class);
            intent.putExtra(Constants.miqyas_fit, "Skirts-S,M,L,XL,XXL");
            startActivityForResult(intent, request_code);
        } else if (view == binding.btn1) {
            Intent intent = new Intent(this, FindMySizeActivity.class);
            intent.putExtra(Constants.miqyas_fit, "Pants-27,28,29,30,31");
            startActivityForResult(intent, request_code);
        }
    }

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
}