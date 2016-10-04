package com.example.choco3.rxretrofitlab.recycler;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.choco3.rxretrofitlab.databinding.ListitemFollowBinding;

/**
 * Created by Choco3 on 30/9/2559.
 */

public class FollowerUserListItemHolder extends RecyclerView.ViewHolder {

    private ListitemFollowBinding mBinding;

    public FollowerUserListItemHolder(View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }

    public ListitemFollowBinding getBinding() {
        return mBinding;
    }

    public void setItemClickListener(View.OnClickListener listener) {
        mBinding.getRoot().setOnClickListener(listener);
    }
}
