package com.femi9.findmysize;

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

import com.femi9.findmysize.adapter.HeightAdapter;
import com.femi9.findmysize.databinding.ActivityFindMySizeStep1Binding;
import com.femi9.findmysize.model.DataSizes;
import com.femi9.utils.Constants;

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
    private final List<String> listFitText = new ArrayList<>();

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

        int tmpInch = 0;
        int tmoFit = 0;
        listFitText.clear();
        for (int i = 0; i < Constants.HEIGHT; i++) {
            listFitText.add(tmoFit + "ft " + tmpInch + "in");
            tmpInch++;
            if (tmpInch == 12) {
                tmoFit++;
                tmpInch = 0;
            }
        }

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
        int pos = App.preferences.getInt(Constants.height, 65);
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
            View viewCurrentItem = snapHelper.findSnapView(layoutManager);
            int currentPosition = binding.rv.getChildAdapterPosition(viewCurrentItem);
            App.preferences.putInt(Constants.height, currentPosition);
            App.preferences.putInt(Constants.heightSel, isFitSelected ? 0 : 1);
            start(FindMySizeSteps2Activity.class);
        } else if (view == binding.ivClose) {
            onBackPressed();
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
            if (listFitText.size() > selectPosition)
                binding.tvHeightVal.setText(listFitText.get(selectPosition));
        } else {
            binding.tvHeightVal.setText(selectPosition + getResources().getString(R.string.cm));
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
                    return dataSizes.get(i).getName();
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