package com.journeyos.freshday.view.collections;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.journeyos.freshday.model.ItemBean;
import com.journeyos.freshday.view.custom.SlideCutListView;
import com.journeyos.freshday.R;

import java.util.ArrayList;
import java.util.List;

public class PlayedActivity extends Activity {

    private SlideCutListView mSlideCutListView;
    private List<ItemBean> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5_game);
        initView();
        initDate();
        initAdapter();
        mSlideCutListView.setRemoveListener(new SlideCutListView.RemoveListener() {
            @Override
            public void removeItem(SlideCutListView.RemoveDirection direction, int position) {
                list.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initDate() {
        list.add(new ItemBean("1", "2", "3", 4));
        list.add(new ItemBean("1", "2", "3", 4));
    }

    private void initAdapter() {
        adapter = new MyAdapter();
        mSlideCutListView.setAdapter(adapter);
    }

    private void initView() {
        mSlideCutListView = (SlideCutListView) findViewById(R.id.slideCutListView);
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(PlayedActivity.this).inflate(R.layout.item_h5game, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public ImageView ivH5;
            public TextView tvH5;
            public TextView tvTime;
            public Button btH5;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.ivH5 = (ImageView) rootView.findViewById(R.id.iv_h5);
                this.tvH5 = (TextView) rootView.findViewById(R.id.tv_h5);
                this.tvTime = (TextView) rootView.findViewById(R.id.tv_time);
                this.btH5 = (Button) rootView.findViewById(R.id.bt_h5);
            }

        }
    }
}
