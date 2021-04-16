package com.femi9.tracking.main.findmysize;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.femi9.tracking.R;
import com.femi9.tracking.databinding.ActivityFindMySizeStep3Binding;
import com.femi9.tracking.main.utils.Constants;

public class FindMySizeSteps3Activity extends BaseActivity {

    private ActivityFindMySizeStep3Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_my_size_step_3);
        binding.setActivity(this);
        setUpInitialValue();
    }

    private void setUpInitialValue() {
        int selPos = App.preferences.getInt(Constants.age, 0);
        binding.spAge.setSelection(selPos);
    }

    public void onViewClicked(View view) {
        if (view == binding.ivBack) {
            onBackPressed();
        } else if (view == binding.btnFindMyFit) {
            App.preferences.putInt(Constants.age, binding.spAge.getSelectedItemPosition());
            start(FindMySizeSteps4Activity.class);
        } else if (view == binding.ivClose) {
            finishWithResultAndAnimation(null);
        }
    }

    @Override
    public void onBackPressed() {
        finishWithAnimation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                finishWithResultAndAnimation(data);
            }
        }
    }
}