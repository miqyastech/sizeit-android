package com.sizeit.findmysize;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.sizeit.findmysize.adapter.HeightAdapter;
import com.sizeit.findmysize.databinding.ActivityFindMySizeStep1Binding;
import com.sizeit.findmysize.model.DataSizes;
import com.sizeit.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class FindMySizeActivity extends BaseActivity {

    public static final String CURRENT_SIZE = "current_size";
    public static String miqyas_fit = "", user_id = "";

    private ActivityFindMySizeStep1Binding binding;
    private boolean isFitSelected;

    private int selectPosition = 0;

    private SnapHelper snapHelper;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_my_size_step_1);
        binding.setActivity(this);
        if (getIntent() != null) {
            if (getIntent().hasExtra(Constants.user_id)) {
                user_id = getIntent().getStringExtra(Constants.user_id);
            } else {
                user_id = "";
            }
            if (getIntent().hasExtra(Constants.miqyas_fit)) {
                miqyas_fit = getIntent().getStringExtra(Constants.miqyas_fit);
            } else {
                miqyas_fit = "";
            }
            isAttributeAvailableInList();
        } else {
            miqyas_fit = "";
            user_id = "";
        }
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
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = snapHelper.findSnapView(layoutManager);
                int pos = binding.rv.getChildAdapterPosition(view);
                if (pos < 0) return;
                selectPosition = pos;
                setSelectedVal();
            }
        });

        setUpInitialValue();
    }

    private void setUpInitialValue() {
        int pos = App.preferences.getInt(Constants.height, 20);
        if (pos > 140) pos = pos - 140;
        int selPos = App.preferences.getInt(Constants.heightSel, 1);
        layoutManager.scrollToPosition(pos);
        setHeadingBG(selPos);
        setSelectedVal();
    }

    public void onViewClicked(View view) {
        if (view == binding.tvFTHeading) {
            setHeadingBG(0);
            setSelectedVal();
//            View viewSelected = snapHelper.findSnapView(layoutManager);
//            selectPosition = binding.rv.getChildAdapterPosition(viewSelected);
//            setSelectedVal();
        } else if (view == binding.tvCMHeading) {
            setHeadingBG(1);
            setSelectedVal();
//            View viewSelected = snapHelper.findSnapView(layoutManager);
//            selectPosition = binding.rv.getChildAdapterPosition(viewSelected);
//            setSelectedVal();
        } else if (view == binding.ivArrowDownHeight) {
            if (selectPosition > 0) {
                selectPosition--;
                layoutManager.scrollToPosition(selectPosition);
                setSelectedVal();
            }
        } else if (view == binding.ivArrowUpHeight) {
            if (selectPosition < Constants.HEIGHT) {
                selectPosition++;
                layoutManager.scrollToPosition(selectPosition);
                setSelectedVal();
            }
        } else if (view == binding.ivBack) {
            binding.layoutPrivacyPolicy.clPPMain.setVisibility(View.GONE);
            binding.ivBack.setVisibility(View.GONE);
        } else if (view == binding.tvPrivacyPolicy) {
            binding.layoutPrivacyPolicy.clPPMain.setVisibility(View.VISIBLE);
            binding.ivBack.setVisibility(View.VISIBLE);
        } else if (view == binding.btnFindMyFit) {
//            if (!isFitSelected && (selectPosition < 140 || selectPosition > 200)) {
//                SizeitUtils.makeToast(this, getResources().getString(R.string.height_invalid_err));
//                return;
//            }
//            if (isFitSelected && (selectPosition < 53 || selectPosition > 78)) {
//                SizeitUtils.makeToast(this, getResources().getString(R.string.height_invalid_err));
//                return;
//            }
            App.preferences.putInt(Constants.height, (selectPosition + 140));
            App.preferences.putInt(Constants.heightSel, isFitSelected ? 0 : 1);
            start(FindMySizeSteps2Activity.class);
        } else if (view == binding.ivClose) {
            onBackPressed();
        }
    }

    private void setHeadingBG(int from) {
        isFitSelected = (from == 0);
        binding.tvFTHeading.setBackground(from == 0 ? ContextCompat.getDrawable(this,
                App.preferences.isArabic() ? R.drawable.bg_round_left_fill_ar : R.drawable.bg_round_left_fill) : null);
        binding.tvFTHeading.setTextColor(ContextCompat.getColor(this,
                from == 0 ? R.color.white : R.color.colorGrayDark));

        binding.tvCMHeading.setBackground(from == 1 ? ContextCompat.getDrawable(this,
                App.preferences.isArabic() ? R.drawable.bg_round_right_fill_ar : R.drawable.bg_round_right_fill) : null);
        binding.tvCMHeading.setTextColor(ContextCompat.getColor(this,
                from == 1 ? R.color.white : R.color.colorGrayDark));
    }

    private void setSelectedVal() {
        if (isFitSelected) {
//            if (listFitText.size() > selectPosition)
            double dCentimeter = (selectPosition + 140);
            int feetPart = (int) Math.floor((dCentimeter / 2.54) / 12);
            int inchesPart = (int) Math.floor((dCentimeter / 2.54) - (feetPart * 12));
            binding.tvHeightVal.setText(feetPart + "ft " + inchesPart + "in");
        } else {
            binding.tvHeightVal.setText((selectPosition + 140) + getResources().getString(R.string.cm));
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

    private void isAttributeAvailableInList() {
        String size = getSizeByAttribute(miqyas_fit);
        if (!size.equals("")) {
            Intent intent = new Intent();
            intent.putExtra(FindMySizeActivity.CURRENT_SIZE, size);
            finishWithResultAndAnimation(intent);
        }
    }

    /**
     * @return TRUE - IF SiZES AVAILABLE IN STORAGE
     * FALSE - IF SIZES NOT AVAILABLE IN STORAGE
     */
    public static boolean hasSizes() {
        List<DataSizes> dataSizes = App.preferences.getSizesList();
        return dataSizes.size() != 0;
    }

    /**
     * CHECK AND RETURN ATTRIBUTE SIZE IF AVAILABLE IN STORAGE
     *
     * @param attribute
     * @return
     */
    public static String getSizeByAttribute(String attribute) {
        if (attribute == null || attribute.trim().equals("")) return "";
        List<DataSizes> dataSizes = App.preferences.getSizesList();
        if (dataSizes.size() > 0) {
            for (int i = 0; i < dataSizes.size(); i++) {
                if (dataSizes.get(i).getName().equalsIgnoreCase(miqyas_fit)) {
                    return dataSizes.get(i).getSize();
                }
            }
        }
        return "";
    }

    /**
     * @return GET ALL PRODUCT SIZES WITH NAME IF AVAILABLE
     */
    public static List<DataSizes> getAllSizes() {
        if (hasSizes()) {
            return App.preferences.getSizesList();
        }
        return new ArrayList<>();
    }
}