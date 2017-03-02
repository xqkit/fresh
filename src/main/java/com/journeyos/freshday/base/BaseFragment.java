package com.journeyos.freshday.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Desc:    基础fragment父类
 * Date:    2017/3/2 10:22
 * Email:   frank.xiong@coolpad.com
 */

public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;

    public abstract void initData();

    protected abstract int getLayoutId();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
