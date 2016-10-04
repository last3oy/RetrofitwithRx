package com.example.choco3.rxretrofitlab.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.choco3.rxretrofitlab.R;
import com.example.choco3.rxretrofitlab.databinding.ListitemFollowBinding;
import com.example.choco3.rxretrofitlab.model.GitHubFollowerUser;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Choco3 on 30/9/2559.
 */

public class FollowerUserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface itemListener {
        void onItemClick(String user);
    }

    private itemListener mListener;

    public void setListener(itemListener listener) {
        mListener = listener;
    }

    private List<GitHubFollowerUser> listData;

    public FollowerUserListAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        ListitemFollowBinding binding = ListitemFollowBinding.inflate(inflator, parent, false);

        return new FollowerUserListItemHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final FollowerUserListItemHolder item = (FollowerUserListItemHolder) holder;
        Picasso.with(item.getBinding().getRoot().getContext())
                .load(listData.get(position).getAvatarUrl())
                .resize(200, 200)
                .placeholder(R.mipmap.ic_launcher)
                .into(item.getBinding().ivUser);

        item.getBinding().tvUser.setText(listData.get(position).getLogin());

        item.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onItemClick(item.getBinding().tvUser.getText().toString());
            }
        });


    }

    public void setListData(List<GitHubFollowerUser> listData) {
        this.listData = listData;
    }

    @Override
    public int getItemCount() {
        if (listData == null)
            return 0;
        return listData.size();
    }


}
