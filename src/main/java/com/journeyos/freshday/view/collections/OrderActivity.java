package com.journeyos.freshday.view.collections;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.journeyos.freshday.model.ItemBean;
import com.journeyos.freshday.util.Network;
import com.journeyos.freshday.R;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends Activity {

    private ListView mListView;
    private TextView mEmpty;
    private List<ItemBean> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        initData();
        initAdapter();
        initEmpty();
    }

    private void initEmpty() {
        if (Network.getNetWorkType(this) == Network.NetworkStateNo) {
            mEmpty.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        } else {
            mListView.setVisibility(View.VISIBLE);
        }
        if(list.size()==0){
            mEmpty.setText("无网络连接");
            mEmpty.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        }else {
            mListView.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        list.add(new ItemBean("1", "2", "3", 4));
        list.add(new ItemBean("1", "2", "3", 4));
    }

    private void initAdapter() {
        adapter = new MyAdapter();
        mListView.setAdapter(adapter);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
        mEmpty = (TextView) findViewById(R.id.empty);
        mEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initEmpty();
                adapter.notifyDataSetChanged();
            }
        });
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
                convertView = LayoutInflater.from(OrderActivity.this).inflate(R.layout.item_order, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public TextView time;
            public TextView title;
            public TextView team1;
            public TextView vs;
            public TextView team2;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.time = (TextView) rootView.findViewById(R.id.time);
                this.title = (TextView) rootView.findViewById(R.id.title);
                this.team1 = (TextView) rootView.findViewById(R.id.team1);
                this.vs = (TextView) rootView.findViewById(R.id.vs);
                this.team2 = (TextView) rootView.findViewById(R.id.team2);
            }

        }
    }
}
