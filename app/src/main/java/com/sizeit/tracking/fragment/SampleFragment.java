package com.sizeit.tracking.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sizeit.findmysize.FindMySizeActivity;
import com.sizeit.findmysize.model.DataSizes;
import com.sizeit.tracking.R;
import com.sizeit.tracking.databinding.MainFragmentBinding;
import com.sizeit.utils.Constants;
import com.sizeit.utils.Preferences;
import com.sizeit.utils.SizeitUtils;

import java.util.List;

public class SampleFragment extends Fragment {

    private final int request_code = 100;

    private MainFragmentBinding binding;

    public static SampleFragment newInstance() {
        return new SampleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        binding.setFragment(this);
        return binding.getRoot();
    }

    public void onViewClicked(View view) {
        if (view == binding.btnFindMyFit) {
            //put language to set locale
            Preferences.getPreferences(getActivity())
                    .putString(Constants.language, Constants.language_arabic);

            Intent intent = new Intent(getActivity(), FindMySizeActivity.class);
            intent.putExtra(Constants.user_id, "1234");
            intent.putExtra(Constants.miqyas_fit, "Outer Wear-FREE");
            startActivityForResult(intent, request_code);
        } else if (view == binding.btn4) {
            SizeitUtils.makeToast(getActivity(), "Your current size is " + FindMySizeActivity.getSizeByAttribute(
                    getActivity(), "Outer Wear-FREE"));
        } else if (view == binding.btn3) {
            SizeitUtils.makeToast(getActivity(), "Your current size is " + FindMySizeActivity.getSizeByAttribute(
                    getActivity(), "Pants-XS,S,M,L,XL"));
        } else if (view == binding.btn2) {
            SizeitUtils.makeToast(getActivity(), "Your current size is " + FindMySizeActivity.getSizeByAttribute(
                    getActivity(), "Skirts-S,M,L,XL,XXL"));
        } else if (view == binding.btn1) {
            SizeitUtils.makeToast(getActivity(), "Your current size is " + FindMySizeActivity.getSizeByAttribute(
                    getActivity(), "Pants-27,28,29,30,31"));
        }
    }

    /**
     * IF YOU WANT TO GET ALL THE PRODUCT SIZE FROM LOCALE
     * THEN CALL THE "FindMySizeActivity.getAllSizes()" FUNCTION.
     * <p>
     * IT WILL RETURN ALL SIZE IN STORAGE IF AVAILABLE.
     */
    public void getAllSizesFromLocale() {
        List<DataSizes> dataSizes = FindMySizeActivity.getAllSizes(getActivity());
    }

    /**
     * IF YOU WANT TO GET PARTICULAR PRODUCT SIZE BY PRODUCT NAME
     * THEN CALL THE "FindMySizeActivity.getSizeByAttribute('X')" FUNCTION.
     * <p>
     * IT WILL RETURN PARTICULAR PRODUCT SIZE FROM LOCALE IF AVAILABLE.
     */
    private void getSizeByProductName() {
        FindMySizeActivity.getSizeByAttribute(getActivity(), "");
    }

    /**
     * IF YOU WANT TO CHECK SIZES ARE AVAILABLE ON LOCALE OR NOT
     * THEN CALL THE "FindMySizeActivity.hasSizes()" FUNCTION.
     * <p>
     * IT WILL RETURN TRUE IF IT IS AVAILABLE ELSE IT IS RETURN FALSE.
     */
    public void hashProductSizes() {
        boolean isAvailable = FindMySizeActivity.hasSizes(getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_code) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null && data.hasExtra(FindMySizeActivity.CURRENT_SIZE)) {
                    if (!data.getStringExtra(FindMySizeActivity.CURRENT_SIZE).equals("")) {
                        SizeitUtils.makeToast(getActivity(), "Your current size is " +
                                data.getStringExtra(FindMySizeActivity.CURRENT_SIZE));
                    }
                }
            }
        }
    }
}