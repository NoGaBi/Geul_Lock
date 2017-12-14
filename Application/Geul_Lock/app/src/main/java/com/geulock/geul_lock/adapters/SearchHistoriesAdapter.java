package com.geulock.geul_lock.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geulock.geul_lock.R;
import com.geulock.geul_lock.data.SearchHistoriesPrev;
import com.geulock.geul_lock.data.SearchHistory;

import java.util.HashSet;
import java.util.Set;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;


/**
 * Created by sihwan on 2017. 12. 13..
 */

public class SearchHistoriesAdapter extends RealmRecyclerViewAdapter<SearchHistory, SearchHistoriesAdapter.ItemVH> {

    public SearchHistoriesAdapter(@Nullable OrderedRealmCollection<SearchHistory> data) {
        super(data, true);

        setHasStableIds(true);
    }

    public static class ItemVH extends RecyclerView.ViewHolder {
        TextView tvMessage;
        public SearchHistory data;
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
        final SearchHistory obj = getItem(position);
        holder.data = obj;
        holder.tvMessage.setText(obj.getMessage());
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }
}
