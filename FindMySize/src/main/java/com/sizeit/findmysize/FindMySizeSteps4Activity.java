package com.sizeit.findmysize;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.sizeit.findmysize.databinding.ActivityFindMySizeStep4Binding;
import com.sizeit.utils.Constants;
import com.sizeit.utils.Preferences;


public class FindMySizeSteps4Activity extends BaseActivity {

    private ActivityFindMySizeStep4Binding binding;
    private int selectionPosition = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_my_size_step_4);
        binding.setActivity(this);
        setUpInitialValue();
        setUpHeaderView();
    }

    private void setUpInitialValue() {
        binding.header.ivBack.setOnClickListener(v -> onBackPressed());
        binding.header.ivClose.setOnClickListener(v -> finishWithResultAndAnimation(null));

        selectionPosition = Preferences.getPreferences(this).getInt(Constants.belly, 2);
    }

    private void setUpHeaderView() {
        binding.tvFlatter.setTextColor(ContextCompat.getColor(this,
                selectionPosition == 1 ? R.color.black : R.color.colorGrayDark));
        binding.tvFlatter.setPaintFlags(selectionPosition == 1 ?
                binding.tvFlatter.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG : 0);

        binding.tvAverage.setTextColor(ContextCompat.getColor(this,
                selectionPosition == 2 ? R.color.black : R.color.colorGrayDark));
        binding.tvAverage.setPaintFlags(selectionPosition == 2 ?
                binding.tvAverage.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG : 0);

        binding.tvRounder.setTextColor(ContextCompat.getColor(this,
                selectionPosition == 3 ? R.color.black : R.color.colorGrayDark));
        binding.tvRounder.setPaintFlags(selectionPosition == 3 ?
                binding.tvRounder.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG : 0);

        binding.iv.setImageResource(selectionPosition == 1 ?
                R.drawable.stomach_flatter : selectionPosition == 2 ?
                R.drawable.stomach_average : R.drawable.stomach_rounder);
    }

    public void onViewClicked(View view) {
        if (view == binding.tvFlatter) {
            selectionPosition = 1;
            setUpHeaderView();
        } else if (view == binding.tvAverage) {
            selectionPosition = 2;
            setUpHeaderView();
        } else if (view == binding.tvRounder) {
            selectionPosition = 3;
            setUpHeaderView();
        } else if (view == binding.btnContinue) {
            Preferences.getPreferences(this).putInt(Constants.belly, selectionPosition);
            start(FindMySizeSteps5Activity.class);
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