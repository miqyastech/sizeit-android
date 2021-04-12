package com.femi9.tracking.main.findmysize;

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

import com.femi9.tracking.R;
import com.femi9.tracking.databinding.ActivityFindMySizeStep1Binding;
import com.femi9.tracking.main.findmysize.adapter.HeightAdapter;

public class FindMySizeSteps1Activity extends BaseActivity {

    private ActivityFindMySizeStep1Binding binding;
    private boolean isFitSelected;

    //    min fit = 5
//    max fit = 6 11
    private final int minFT = 5;
    private final int maxFT = 6;
    private int ft = 6;

    private final int minInch = 0;
    private final int maxInch = 11;
    private int inch = 0;
    //    min CM = 142
//    max CM = 198
    private final int minCM = 142;
    private final int maxCM = 198;
    private int cm = 160;

    private SnapHelper snapHelper;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_my_size_step_1);
        binding.setActivity(this);
        setUpHeaderView();
    }

    private void setUpHeaderView() {
        layoutManager = new LinearLayoutManager(this);
        binding.rv.setLayoutManager(layoutManager);
        binding.rv.setAdapter(new HeightAdapter());

        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.rv);

        binding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View view = snapHelper.findSnapView(layoutManager);
                int pos = binding.rv.getChildAdapterPosition(view);
                if (pos < 0) return;
                if (isFitSelected) {
                    double inchVal = 0.3937 * pos;
                    double feetVal = 0.0328 * pos;
                    ft = (int) feetVal;
                    inch = (int) inchVal;
                    Log.e("TAG", "position " + pos);
                    Log.e("TAG", "inch: " + inchVal);
                    Log.e("TAG", "feet: " + feetVal);
                } else {
                    cm = pos;
                }
                setSelectedVal();
            }
        });

        layoutManager.scrollToPosition(cm);
        setSelectedVal();
        setHeadingBG(1);
    }

    public void onViewClicked(View view) {
        if (view == binding.tvFTHeading) {
            setHeadingBG(0);
            View viewSelected = snapHelper.findSnapView(layoutManager);
            int pos = binding.rv.getChildAdapterPosition(viewSelected);
            if (pos >= 0) {
                layoutManager.scrollToPosition(pos - 1);
                double inchVal = 0.3937 * pos;
                double feetVal = 0.0328 * pos;
                ft = (int) feetVal;
                inch = (int) inchVal;
                setSelectedVal();
            }
        } else if (view == binding.tvCMHeading) {
            setHeadingBG(1);
            View viewSelected = snapHelper.findSnapView(layoutManager);
            int pos = binding.rv.getChildAdapterPosition(viewSelected);
            cm = pos;
            setSelectedVal();
        } else if (view == binding.ivArrowDownHeight) {
            View viewSelected = snapHelper.findSnapView(layoutManager);
            int pos = binding.rv.getChildAdapterPosition(viewSelected);
            if (pos >= 0) {
                layoutManager.scrollToPosition(pos - 1);
                if (isFitSelected) {
                    double inchVal = 0.3937 * pos;
                    double feetVal = 0.0328 * pos;
                    ft = (int) feetVal;
                    inch = (int) inchVal;
                } else {
                    cm = pos;
                }
                setSelectedVal();
            }
        } else if (view == binding.ivArrowUpHeight) {
            View viewSelected = snapHelper.findSnapView(layoutManager);
            int pos = binding.rv.getChildAdapterPosition(viewSelected);
            layoutManager.scrollToPosition(pos + 1);
            if (isFitSelected) {
                double inchVal = 0.3937 * pos;
                double feetVal = 0.0328 * pos;
                ft = (int) feetVal;
                inch = (int) inchVal;
            } else {
                cm = pos;
            }
            setSelectedVal();
        } else if (view == binding.ivBack) {
            binding.layoutPrivacyPolicy.clPPMain.setVisibility(View.GONE);
            binding.ivBack.setVisibility(View.GONE);
        } else if (view == binding.tvPrivacyPolicy) {
            binding.layoutPrivacyPolicy.clPPMain.setVisibility(View.VISIBLE);
            binding.ivBack.setVisibility(View.VISIBLE);
        } else if (view == binding.btnFindMyFit) {
            start(FindMySizeSteps2Activity.class);
        }
    }

    private void setHeadingBG(int from) {
        isFitSelected = (from == 0);
        binding.tvFTHeading.setBackground(from == 0 ? ContextCompat.getDrawable(this,
                R.drawable.bg_round_left_fill) : null);
        binding.tvFTHeading.setTextColor(ContextCompat.getColor(this,
                from == 0 ? R.color.white : R.color.colorGrayDark));

        binding.tvCMHeading.setBackground(from == 1 ? ContextCompat.getDrawable(this,
                R.drawable.bg_round_right_fill) : null);
        binding.tvCMHeading.setTextColor(ContextCompat.getColor(this,
                from == 1 ? R.color.white : R.color.colorGrayDark));
    }

    private void setSelectedVal() {
        if (isFitSelected) {
            binding.tvHeightVal.setText(ft + getResources().getString(R.string.ft) + " " +
                    inch + getResources().getString(R.string.in));
        } else {
            binding.tvHeightVal.setText(cm + getResources().getString(R.string.cm));
        }
    }
}