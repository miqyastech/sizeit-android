package com.femi9.tracking;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.femi9.tracking.databinding.ActivityMainBinding;
import com.femi9.tracking.main.findmysize.BaseActivity;
import com.femi9.tracking.main.findmysize.FindMySizeSteps1Activity;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
    }

    public void onViewClicked(View view) {
        if (view == binding.btnFindMyFit) {
            start(FindMySizeSteps1Activity.class);
        }
    }
}