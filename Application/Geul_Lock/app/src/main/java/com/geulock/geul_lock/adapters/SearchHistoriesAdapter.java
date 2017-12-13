package com.geulock.geul_lock.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geulock.geul_lock.R;
import com.geulock.geul_lock.data.SearchHistories;

/**
 * Created by sihwan on 2017. 12. 13..
 */

public class SearchHistoriesAdapter extends RecyclerView.Adapter<SearchHistoriesAdapter.ItemVH> {

    public static class ItemVH extends RecyclerView.ViewHolder {
        TextView tvMessage;
        public ItemVH(View itemView) {
            super(itemView);
            tvMessage = (TextView)itemView.findViewById(R.id.tv_message);
        }
    }

    @Override
    public ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_history, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemVH holder, int position) {
        holder.tvMessage.setText(SearchHistories.getList().get(position));
    }

    @Override
    public int getItemCount() {
        return SearchHistories.getList().size();
    }

    public boolean update() {
        notifyDataSetChanged();
        return true;
    }
}
