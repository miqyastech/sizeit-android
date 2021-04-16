package com.femi9.tracking.main.findmysize;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.femi9.tracking.R;
import com.femi9.tracking.databinding.ActivityFindMySizeStep4Binding;
import com.femi9.tracking.main.utils.Constants;

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
        selectionPosition = App.preferences.getInt(Constants.hip, 1);
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
        if (view == binding.ivBack) {
            onBackPressed();
        } else if (view == binding.tvFlatter) {
            selectionPosition = 1;
            setUpHeaderView();
        } else if (view == binding.tvAverage) {
            selectionPosition = 2;
            setUpHeaderView();
        } else if (view == binding.tvRounder) {
            selectionPosition = 3;
            setUpHeaderView();
        } else if (view == binding.btnContinue) {
            App.preferences.putInt(Constants.hip, selectionPosition);
            start(FindMySizeSteps5Activity.class);
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