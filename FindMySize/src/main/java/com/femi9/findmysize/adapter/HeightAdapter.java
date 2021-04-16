package com.femi9.findmysize.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.femi9.findmysize.R;
import com.femi9.findmysize.databinding.RowHeightBinding;
import com.femi9.utils.Constants;

public class HeightAdapter extends RecyclerView.Adapter<HeightAdapter.ViewHolder> {

    public HeightAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowHeightBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_height, parent, false);
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
        return Constants.HEIGHT;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final RowHeightBinding binding;

        public ViewHolder(@NonNull RowHeightBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}