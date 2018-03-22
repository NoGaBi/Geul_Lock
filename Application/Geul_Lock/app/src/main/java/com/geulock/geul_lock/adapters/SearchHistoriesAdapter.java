package com.geulock.geul_lock.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geulock.geul_lock.Fonts;
import com.geulock.geul_lock.R;
import com.geulock.geul_lock.data.SearchHistory;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;


/**
 * Created by sihwan on 2017. 12. 13..
 */

public class SearchHistoriesAdapter extends RealmRecyclerViewAdapter<SearchHistory, SearchHistoriesAdapter.ItemVH> {

    public SearchHistoriesAdapter(@Nullable OrderedRealmCollection<SearchHistory> data) {
        super(data, true);

        // RecyclerView 의 성능을 올려주는 기능. 사용 제약 있음.
        // TODO: setHasStableIds 메소드의 역할을 정확히 알아보기.
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
        ItemVH object = new ItemVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_history, parent, false));
        // 메세지 폰트 설정
        object.tvMessage.setTypeface(Fonts.getSDMiSaeng(object.tvMessage.getContext()));
        return object;
    }

    @Override
    public void onBindViewHolder(ItemVH holder, int position) {
        final SearchHistory obj = getItem(position);
        holder.data = obj;
        holder.tvMessage.setText(obj.getMessage());
    }

    /*
     * 특정 position 의 id (Primary Key) 값을 얻어오는 메소드입니다.
     */
    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }
}
