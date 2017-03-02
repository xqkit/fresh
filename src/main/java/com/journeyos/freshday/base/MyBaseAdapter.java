package com.journeyos.freshday.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Desc:
 * Date:    2017/3/2 10:37
 * Email:   frank.xiong@zeusis.com
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private List<T> datas;
    protected Context mContext;

    public MyBaseAdapter(List<T> datas, Context context) {
        super();
        this.datas = datas;
        mContext = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder<T> holder;
        if (convertView == null) {
            holder = getHolder();
        } else {
            holder = (BaseHolder<T>) convertView.getTag();
        }

        T string = datas.get(position);
        holder.setData(string);

        return holder.getContentView();
    }

    protected abstract BaseHolder<T> getHolder();

}
