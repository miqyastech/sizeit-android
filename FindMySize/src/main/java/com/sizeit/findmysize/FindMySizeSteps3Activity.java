package com.sizeit.findmysize;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.sizeit.findmysize.databinding.ActivityFindMySizeStep3Binding;
import com.sizeit.utils.Constants;
import com.sizeit.utils.Preferences;
import com.sizeit.utils.SizeitUtils;

public class FindMySizeSteps3Activity extends BaseActivity {

    private ActivityFindMySizeStep3Binding binding;
    private Button[] ageViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_my_size_step_3);
        binding.setActivity(this);
        setUpInitialValue();
    }

    private void setUpInitialValue() {
        binding.header.ivBack.setOnClickListener(v -> onBackPressed());
        binding.header.ivClose.setOnClickListener(v -> finishWithResultAndAnimation(null));

        ageViews = new Button[]{binding.btn1420, binding.btn2030, binding.btn3040, binding.btn4050, binding.btn5060, binding.btn60};
        int selPos = Preferences.getPreferences(this).getInt(Constants.age, 0);
//        binding.spAge.setSelection(selPos);
        if (selPos > 0 && ageViews.length >= selPos) {
            ageViews[selPos - 1].setBackground(ContextCompat.getDrawable(this, R.drawable.bg_btn_selected));
            ageViews[selPos - 1].setTextColor(ContextCompat.getColor(this, R.color.white));
        }
    }

    public void onViewClicked(View view) {
        if (view == binding.btnFindMyFit) {
            if (binding.spAge.getSelectedItemPosition() == 0) {
                SizeitUtils.makeToast(this, getResources().getString(R.string.age_invalid_err));
                return;
            }
            Preferences.getPreferences(this).putInt(Constants.age, binding.spAge.getSelectedItemPosition());
            start(FindMySizeSteps4Activity.class);
        } else if (view == binding.btn1420) {
            navigateToNextScreen(view, 1);
        } else if (view == binding.btn2030) {
            navigateToNextScreen(view, 2);
        } else if (view == binding.btn3040) {
            navigateToNextScreen(view, 3);
        } else if (view == binding.btn4050) {
            navigateToNextScreen(view, 4);
        } else if (view == binding.btn5060) {
            navigateToNextScreen(view, 5);
        } else if (view == binding.btn60) {
            navigateToNextScreen(view, 6);
        }
    }

    private void navigateToNextScreen(View btn, int from) {
        for (Button ageView : ageViews) {
            ageView.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_btn));
            ageView.setTextColor(ContextCompat.getColor(this, R.color.black));
        }

        ((Button) btn).setBackground(ContextCompat.getDrawable(this, R.drawable.bg_btn_selected));
        ((Button) btn).setTextColor(ContextCompat.getColor(this, R.color.white));

        Preferences.getPreferences(this).putInt(Constants.age, from);
        start(FindMySizeSteps4Activity.class);
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