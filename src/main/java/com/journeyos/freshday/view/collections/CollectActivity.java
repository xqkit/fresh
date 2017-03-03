package com.journeyos.freshday.view.collections;

import android.app.Activity;
import android.os.Bundle;

import com.journeyos.freshday.adapter.HomeAdapter;
import com.journeyos.freshday.model.ItemBean;
import com.journeyos.freshday.view.SlideCutListView;
import com.journeyos.freshday.R;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends Activity {
    private SlideCutListView mSlideCutListView;
    private List<ItemBean> list = new ArrayList<>();
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
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
        list.add(new ItemBean("11:30", "电竞直播", "tab", 0));
        list.add(new ItemBean("11:30", "游戏直播", "tab", 1));
        list.add(new ItemBean("11:30", "礼包", "tab", 2));
        list.add(new ItemBean("11:30", "H5", "tab", 3));
    }

    private void initAdapter() {
        adapter = new HomeAdapter(list, this);
        mSlideCutListView.setAdapter(adapter);
    }

    private void initView() {
        mSlideCutListView = (SlideCutListView) findViewById(R.id.slideCutListView);
    }
//    private class MyAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return list.get(position);
//        }
//
//        @Override
//        public int getViewTypeCount() {
//            return 4;
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//            return list.get(position).getType();
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            int type = getItemViewType(position);
//
//            final ViewHolder holder;
//            if (convertView == null) {
//                convertView = LayoutInflater.from(CollectActivity.this).inflate(R.layout.item_main, parent, false);
//                holder = new ViewHolder(convertView);
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//            switch (type) {
//                case 0:
//                    holder.icon.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(CollectActivity.this,LiveMatchActivity.class));
//                        }
//                    });
//                    break;
//                case 1:
//                    holder.icon.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(CollectActivity.this,LiveGameActivity.class));
//                        }
//                    });
//                    break;
//                case 2:
//                    holder.icon.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(CollectActivity.this,GiftActivity.class));
//                        }
//                    });
//                    break;
//                case 3:
//                    holder.icon.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(CollectActivity.this,H5Activity.class));
//                        }
//                    });
//                    break;
//            }
//            holder.start.setVisibility(View.GONE);
//            holder.time.setText(list.get(position).getTime());
//            holder.name.setText(list.get(position).getName());
//            holder.itemTab.setText(list.get(position).getTab());
//            return convertView;
//        }
//
//
//        public class ViewHolder {
//            public View rootView;
//            public TextView time;
//            public TextView name;
//            public TextView itemTab;
//            public ImageView icon;
//            public Button start;
//
//            public ViewHolder(View rootView) {
//                this.rootView = rootView;
//                this.time = (TextView) rootView.findViewById(R.id.time);
//                this.name = (TextView) rootView.findViewById(R.id.name);
//                this.itemTab = (TextView) rootView.findViewById(R.id.item_tab);
//                this.icon = (ImageView) rootView.findViewById(R.id.icon);
//                this.start = (Button) rootView.findViewById(R.id.start);
//            }
//        }
//    }
}
