package com.journeyos.freshday.base;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Desc:    基础父类
 * Date:    2017/3/2 10:21
 * Email:   frank.xiong@coolpad.com
 */

public abstract class BaseActivity extends Activity {

    public abstract void initData();
    public void initView(){}

    //布局文件ID
    protected abstract int getContentViewId();

    //布局中fragment的ID
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    //添加fragment
    protected void addFragment(com.journeyos.freshday.base.BaseFragment fragment) {
        if (fragment != null) {
            getFragmentManager().beginTransaction().replace(getFragmentContentId(), fragment
                    , fragment.getClass().getSimpleName()).addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    //移除fragment
    protected void removeFragment() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}
