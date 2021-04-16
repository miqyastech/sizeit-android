package com.femi9.findmysize.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.femi9.tracking.R;
import com.femi9.tracking.databinding.RowWeightBinding;
import com.femi9.tracking.main.utils.Constants;

public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.ViewHolder> {

    public WeightAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowWeightBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_weight, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position % 4 == 0) {
            holder.binding.viewBig.setVisibility(View.VISIBLE);
            holder.binding.viewSmall.setVisibility(View.GONE);
        } else {
            holder.binding.viewBig.setVisibility(View.GONE);
            holder.binding.viewSmall.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return Constants.WEIGHT;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final RowWeightBinding binding;

        public ViewHolder(@NonNull RowWeightBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}