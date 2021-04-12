package com.femi9.tracking.main.findmysize;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.femi9.tracking.R;
import com.femi9.tracking.databinding.ActivityFindMySizeStep2Binding;
import com.femi9.tracking.main.findmysize.adapter.WeightAdapter;

public class FindMySizeSteps2Activity extends AppCompatActivity {

    private ActivityFindMySizeStep2Binding binding;
    private boolean isLBSSelected;

    //    min fit = 5
//    max fit = 6 11
    private final int minLBS = 36;
    private final int maxLBS = 329;
    private int lbs = 111;

    private final int minKG = 36;
    private final int maxKG = 150;
    private int kg = 50;

    private SnapHelper snapHelper;
    private LinearLayoutManager layoutManager;

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
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View view = snapHelper.findSnapView(layoutManager);
                int pos = binding.rv.getChildAdapterPosition(view);
                if (pos < 0) return;
                if (isLBSSelected) {
                    lbs = pos;
                } else {
                    kg = pos;
                }
                setSelectedVal();
            }
        });

        layoutManager.scrollToPosition(kg);
        setSelectedVal();
        setHeadingBG(1);
    }

    public void onViewClicked(View view) {
        if (view == binding.tvLBSHeading) {
            setHeadingBG(0);
            View viewSelected = snapHelper.findSnapView(layoutManager);
            int pos = binding.rv.getChildAdapterPosition(viewSelected);
            if (pos >= 0) {
                layoutManager.scrollToPosition(pos - 1);
                lbs = pos;
                setSelectedVal();
            }
        } else if (view == binding.tvKGHeading) {
            setHeadingBG(1);
            View viewSelected = snapHelper.findSnapView(layoutManager);
            kg = binding.rv.getChildAdapterPosition(viewSelected);
            setSelectedVal();
        } else if (view == binding.ivArrowDownHeight) {
            View viewSelected = snapHelper.findSnapView(layoutManager);
            int pos = binding.rv.getChildAdapterPosition(viewSelected);
            if (pos >= 0) {
                layoutManager.scrollToPosition(pos - 1);
                if (isLBSSelected) {
                    lbs = pos;
                } else {
                    kg = pos;
                }
                setSelectedVal();
            }
        } else if (view == binding.ivArrowUpHeight) {
            View viewSelected = snapHelper.findSnapView(layoutManager);
            int pos = binding.rv.getChildAdapterPosition(viewSelected);
            layoutManager.scrollToPosition(pos + 1);
            if (isLBSSelected) {
                lbs = pos;
            } else {
                kg = pos;
            }
            setSelectedVal();
        } else if (view == binding.ivBack) {
            onBackPressed();
        } else if (view == binding.tvPrivacyPolicy) {
            binding.layoutPrivacyPolicy.clPPMain.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        if (binding.layoutPrivacyPolicy.clPPMain.getVisibility() == View.VISIBLE)
            binding.layoutPrivacyPolicy.clPPMain.setVisibility(View.GONE);
        else
            finish();
    }

    private void setHeadingBG(int from) {
        isLBSSelected = (from == 0);
        binding.tvLBSHeading.setBackground(from == 0 ? ContextCompat.getDrawable(this,
                R.drawable.bg_round_left_fill) : null);
        binding.tvLBSHeading.setTextColor(ContextCompat.getColor(this,
                from == 0 ? R.color.white : R.color.colorGrayDark));

        binding.tvKGHeading.setBackground(from == 1 ? ContextCompat.getDrawable(this,
                R.drawable.bg_round_right_fill) : null);
        binding.tvKGHeading.setTextColor(ContextCompat.getColor(this,
                from == 1 ? R.color.white : R.color.colorGrayDark));
    }

    private void setSelectedVal() {
        if (isLBSSelected) {
            binding.tvWeightVal.setText(lbs + getResources().getString(R.string.lbs));
        } else {
            binding.tvWeightVal.setText(kg + getResources().getString(R.string.kg));
        }
    }
}