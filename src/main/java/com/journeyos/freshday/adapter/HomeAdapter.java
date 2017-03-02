package com.journeyos.freshday.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.journeyos.freshday.base.BaseHolder;
import com.journeyos.freshday.base.MyBaseAdapter;
import com.journeyos.freshday.model.ItemBean;
import com.journeyos.freshday.view.activity.content.GiftActivity;
import com.journeyos.freshday.view.activity.content.H5Activity;
import com.journeyos.freshday.view.activity.content.LiveGameActivity;
import com.journeyos.freshday.view.activity.content.LiveMatchActivity;
import com.journeyos.freshday.R;

import java.util.List;

import com.journeyos.freshday.view.activity.content.GiftActivity;

/**
 * Desc:    首页适配器
 * Date:    2017/3/2 10:35
 * Email:   frank.xiong@coolpad.com
 */

public class HomeAdapter extends MyBaseAdapter<ItemBean> {

    private List<ItemBean> mDatas;

    public HomeAdapter(List<ItemBean> datas, Context context) {
        super(datas, context);
        mDatas = datas;
    }

    @Override
    protected BaseHolder<ItemBean> getHolder() {
        return new MainHolder();
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);

        final MainHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_main, null);
            holder = new MainHolder();
            convertView.setTag(holder);
        } else {
            holder = (MainHolder) convertView.getTag();
        }

        gotoOther(holder, type);

        holder.start.setVisibility(View.GONE);
        holder.time.setText(mDatas.get(position).getTime());
        holder.name.setText(mDatas.get(position).getName());
        holder.itemTab.setText(mDatas.get(position).getTab());
        return convertView;
    }


    class MainHolder extends BaseHolder<ItemBean> {

        public TextView time;
        public TextView name;
        public TextView itemTab;
        public ImageView icon;
        public Button start;

        @Override
        protected void refreshView(ItemBean data) {
        }

        @Override
        protected View initView() {
            View view = View.inflate(mContext, R.layout.item_main, null);
            time = (TextView) view.findViewById(R.id.time);
            name = (TextView) view.findViewById(R.id.name);
            itemTab = (TextView) view.findViewById(R.id.item_tab);
            icon = (ImageView) view.findViewById(R.id.icon);
            start = (Button) view.findViewById(R.id.start);
            return view;
        }
    }

    private void gotoOther(MainHolder holder, int type) {
        switch (type) {
            case 0:
                holder.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, LiveMatchActivity.class));
                    }
                });
                break;
            case 1:
                holder.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, LiveGameActivity.class));
                    }
                });
                break;
            case 2:
                holder.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, GiftActivity.class));
                    }
                });
                break;
            case 3:
                holder.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, H5Activity.class));
                    }
                });
                break;
        }
    }
}
