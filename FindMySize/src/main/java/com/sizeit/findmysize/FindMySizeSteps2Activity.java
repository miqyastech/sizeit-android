package com.sizeit.findmysize;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.sizeit.findmysize.adapter.WeightAdapter;
import com.sizeit.findmysize.databinding.ActivityFindMySizeStep2Binding;
import com.sizeit.utils.Constants;
import com.sizeit.utils.Preferences;

public class FindMySizeSteps2Activity extends BaseActivity {

    private ActivityFindMySizeStep2Binding binding;
    private boolean isLBSSelected;

    private int selectPosition = 0;

    private SnapHelper snapHelper;
    private LinearLayoutManager layoutManager;
    private boolean isFromUpDownButton = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_my_size_step_2);
        binding.setActivity(this);
        setUpHeaderView();
    }

    private void setUpHeaderView() {
        layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        binding.rv.setLayoutManager(layoutManager);
        binding.rv.setAdapter(new WeightAdapter());

        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.rv);

        binding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isFromUpDownButton) {
                    isFromUpDownButton = false;
                    return;
                }
                View view = snapHelper.findSnapView(layoutManager);
                int pos = binding.rv.getChildAdapterPosition(view);
                if (pos < 0) return;
                Log.e("TAG", "onScrolled: " + pos);
                selectPosition = pos;
                setSelectedVal();
            }
        });
        setUpInitialValue();
    }

    private void setUpInitialValue() {
        int pos = Preferences.getPreferences(this).getInt(Constants.weight, 15);
        if (pos > 35) pos = pos - 35;
        int selPos = Preferences.getPreferences(this).getInt(Constants.weightSel, 1);
        layoutManager.scrollToPosition(pos);
        setHeadingBG(selPos);
        setSelectedVal();
    }

    public void onViewClicked(View view) {
        if (view == binding.tvLBSHeading) {
            setHeadingBG(0);
            setSelectedVal();
        } else if (view == binding.tvKGHeading) {
            setHeadingBG(1);
            setSelectedVal();
        } else if (view == binding.ivArrowDownHeight) {
            if (selectPosition > 0) {
                isFromUpDownButton = true;
                selectPosition--;
                Log.e("TAG", "onScrolled: " + selectPosition);
                layoutManager.scrollToPosition(selectPosition);
                setSelectedVal();
            }
        } else if (view == binding.ivArrowUpHeight) {
            if (selectPosition < Constants.WEIGHT) {
                isFromUpDownButton = true;
                selectPosition++;
                Log.e("TAG", "onScrolled: " + selectPosition);
                layoutManager.scrollToPosition(selectPosition);
                setSelectedVal();
            }
        } else if (view == binding.ivBack) {
            onBackPressed();
        } else if (view == binding.tvPrivacyPolicy) {
            binding.layoutPrivacyPolicy.clPPMain.setVisibility(View.VISIBLE);
        } else if (view == binding.btnContinue) {
//            if (selectPosition < 35 || selectPosition > 150) {
//                SizeitUtils.makeToast(this, getResources().getString(R.string.weight_invalid_err));
//                return;
//            }
            Preferences.getPreferences(this).putInt(Constants.weight, selectPosition + 35);
            Preferences.getPreferences(this).putInt(Constants.weightSel, isLBSSelected ? 0 : 1);
            start(FindMySizeSteps3Activity.class);
        } else if (view == binding.ivClose) {
            finishWithResultAndAnimation(null);
        }
    }

    @Override
    public void onBackPressed() {
        if (binding.layoutPrivacyPolicy.clPPMain.getVisibility() == View.VISIBLE) {
            binding.layoutPrivacyPolicy.clPPMain.setVisibility(View.GONE);
        } else {
            finishWithAnimation();
        }
    }

    private void setHeadingBG(int from) {
        isLBSSelected = (from == 0);
        binding.tvLBSHeading.setBackground(from == 0 ? ContextCompat.getDrawable(this,
                Preferences.getPreferences(this).isArabic() ? R.drawable.bg_round_left_fill_ar : R.drawable.bg_round_left_fill) : null);
        binding.tvLBSHeading.setTextColor(ContextCompat.getColor(this,
                from == 0 ? R.color.white : R.color.colorGrayDark));

        binding.tvKGHeading.setBackground(from == 1 ? ContextCompat.getDrawable(this,
                Preferences.getPreferences(this).isArabic() ? R.drawable.bg_round_right_fill_ar : R.drawable.bg_round_right_fill) : null);
        binding.tvKGHeading.setTextColor(ContextCompat.getColor(this,
                from == 1 ? R.color.white : R.color.colorGrayDark));
    }

    private void setSelectedVal() {
        if (isLBSSelected) {
            binding.tvWeightVal.setText((int) ((selectPosition + 35) * 2.20452) + getResources().getString(R.string.lbs));
        } else {
            binding.tvWeightVal.setText((selectPosition + 35) + getResources().getString(R.string.kg));
        }
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