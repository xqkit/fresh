package com.journeyos.freshday.view.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.BaseSwipListAdapter;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.journeyos.freshday.model.ItemBean;
import com.journeyos.freshday.util.Dimens;
import com.journeyos.freshday.view.IBaseView;
import com.journeyos.freshday.view.activity.SettingActivity;
import com.journeyos.freshday.view.activity.content.H5Activity;
import com.journeyos.freshday.view.activity.content.LiveMatchActivity;
import com.journeyos.freshday.presenter.IBasePresenterIml;
import com.journeyos.freshday.view.activity.content.GiftActivity;
import com.journeyos.freshday.view.activity.content.LiveGameActivity;
import com.journeyos.freshday.R;

import java.util.ArrayList;
import java.util.List;

import static com.journeyos.freshday.R.id.srfl;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements IBaseView, View.OnClickListener {
    public Context mContext;
    private List<ItemBean> list = new ArrayList<>();
    private MyAdapter mAdapter;
    private int lastVisiblePosition;
    public TextView mTime;
    public TextView mempty;
    public ImageView mWeatherImage;
    public TextView mWeather;

    public ImageView mSetting;
    public SwipeMenuListView mListView;
    public SwipeRefreshLayout mSrfl;
    public SharedPreferences sp;
    private SwipeMenuItem collectItem;
    private SwipeMenuItem item;
    private IBasePresenterIml presenter;
    private int num;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity();
        presenter = new IBasePresenterIml<ItemBean>(this);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sp = mContext.getSharedPreferences("login", Context.MODE_PRIVATE);
        initView(view);
        initData();
        initAdapter();
        if (sp.getBoolean("second", false)) {
            mempty.setText("正在摘取最新内容");
            sp.edit().putBoolean("second", true).apply();
        }
        if (list.size() == 0) {
            mempty.setVisibility(View.VISIBLE);
        }
        initListener();
    }

    private void initListener() {
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                collectItem = new SwipeMenuItem(mContext);
                // set item background
                collectItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                collectItem.setWidth(Dimens.dp2px(90, getResources().getDisplayMetrics()));
                // set item title
                collectItem.setTitle("收藏");
                // set item title fontsize
                collectItem.setTitleSize(18);
                // set item title font color
                collectItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(collectItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext);
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(Dimens.dp2px(90, getResources().getDisplayMetrics()));
                // set a icon
                deleteItem.setTitle("钉到桌面");
                // set item title fontsize
                deleteItem.setTitleSize(18);
                // set item title font color
                deleteItem.setTitleColor(Color.WHITE);
                //deleteItem.setIcon(R.mipmap.ic_launcher);
                // add to menu
                menu.addMenuItem(deleteItem);

            }

        };
        // set creator
        mListView.setMenuCreator(creator);
        // step 2. listener item click event
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        //收藏
                        menu.getMenuItems().get(index).setTitle("已收藏");
                        Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();

                        mAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        //钉到桌面
                        Toast.makeText(mContext, "钉到桌面", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        // set SwipeListener
        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
//                collectItem.setTitle("已收藏");
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE && lastVisiblePosition + 2 >= mAdapter.getCount()) {
                    presenter.loading(list);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastVisiblePosition = firstVisibleItem + visibleItemCount;
            }
        });
        mSrfl.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        mSrfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh(list);
            }
        });

    }

    private void initAdapter() {
        mAdapter = new MyAdapter();
        mListView.setSwipeDirection(-1);
        mListView.setAdapter(mAdapter);
    }

    private void initData() {

    }

    private void initView(View view) {
        this.mTime = (TextView) view.findViewById(R.id.time);
        this.mempty = (TextView) view.findViewById(R.id.empty);
        this.mWeatherImage = (ImageView) view.findViewById(R.id.weather_image);
        this.mWeather = (TextView) view.findViewById(R.id.weather);

        this.mSetting = (ImageView) view.findViewById(R.id.setting);
        mSetting.setOnClickListener(this);
        this.mListView = (SwipeMenuListView) view.findViewById(R.id.listView);
        this.mSrfl = (SwipeRefreshLayout) view.findViewById(srfl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
        }
    }

    @Override
    public void onRefresh() {
        mSrfl.setRefreshing(true);

        if (list.size() == 0) {
            num++;
            if (num >= 5) {
                mempty.setText("暂时没有内容，请稍后再试");
            } else {
                mempty.setText("暂时没有内容，试试下拉刷新");
            }
        } else {
            mempty.setVisibility(View.GONE);
        }
        mAdapter.notifyDataSetChanged();
        mSrfl.setRefreshing(false);
    }

    @Override
    public void onLoading() {
        //添加数据
        list.add(new ItemBean("11:30", "电竞直播", "tab", 0));
        list.add(new ItemBean("11:30", "游戏直播", "tab", 1));
        list.add(new ItemBean("11:30", "礼包", "tab", 2));
        list.add(new ItemBean("11:30", "H5", "tab", 3));
        Log.i("ttt", "onScrollStateChanged: " + lastVisiblePosition + mAdapter.getCount());
        mAdapter.notifyDataSetChanged();
    }

    private class MyAdapter extends BaseSwipListAdapter {
        @Override
        public boolean getSwipEnableByPosition(int position) {
            if (!(list.get(position).getType() == 3)) {
                return false;
            }
            return super.getSwipEnableByPosition(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getViewTypeCount() {
            return 4;
        }

        @Override
        public int getItemViewType(int position) {
            return list.get(position).getType();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            int type = getItemViewType(position);

            final ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            switch (type) {
                case 0:
                    holder.start.setText("预约提醒");
                    holder.start.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (holder.start.getText().equals("预约提醒")) {
                                if (sp.getBoolean("login", false)) {
                                    holder.start.setText("已预约");
                                    Toast.makeText(mContext, "预约成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(mContext, "登录酷派账号", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                holder.start.setText("预约提醒");
                            }
                        }
                    });
                    holder.icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(mContext, LiveMatchActivity.class));
                        }
                    });
                    break;
                case 1:
                    holder.start.setText("预约提醒");
                    holder.start.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (holder.start.getText().equals("预约提醒")) {
                                if (sp.getBoolean("login", false)) {
                                    holder.start.setText("已预约");
                                    Toast.makeText(mContext, "预约成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(mContext, "登录酷派账号", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                holder.start.setText("预约提醒");
                            }
                        }
                    });
                    holder.icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(mContext, LiveGameActivity.class));
                        }
                    });
                    break;
                case 2:
                    holder.start.setText("领取礼包");
                    holder.start.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(mContext, GiftActivity.class));
                        }
                    });
                    holder.icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(mContext, GiftActivity.class));
                        }
                    });
                    break;
                case 3:
                    holder.start.setText("开始游戏");
                    holder.start.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(mContext, H5Activity.class));
                        }
                    });
                    holder.icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(mContext, H5Activity.class));
                        }
                    });
                    break;
            }
            holder.time.setText(list.get(position).getTime());
            holder.name.setText(list.get(position).getName());
            holder.itemTab.setText(list.get(position).getTab());
            return convertView;
        }


        public class ViewHolder {
            public View rootView;
            public TextView time;
            public TextView name;
            public TextView itemTab;
            public ImageView icon;
            public Button start;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.time = (TextView) rootView.findViewById(R.id.time);
                this.name = (TextView) rootView.findViewById(R.id.name);
                this.itemTab = (TextView) rootView.findViewById(R.id.item_tab);
                this.icon = (ImageView) rootView.findViewById(R.id.icon);
                this.start = (Button) rootView.findViewById(R.id.start);
            }
        }
    }
}
