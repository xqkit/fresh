package com.journeyos.freshday.base;

import android.view.View;

/**
 * Desc:    基类Holder
 * Date:    2017/3/2 10:43
 * Email:   frank.xiong@zeusis.com
 */

public abstract class BaseHolder<T> {

    protected View contentView;
    protected T data;

    protected BaseHolder() {
        contentView = initView();
        contentView.setTag(this);
    }

    /**
     * 设置数据
     *
     * @param data 传入要刷新的list
     */
    public void setData(T data) {
        this.data = data;
        refreshView(data);
    }

    /**
     * Holder中的view对象显示到界面上的时候调用
     *
     * @return 返回view
     */
    public View getContentView() {
        return contentView;
    }

    protected void refreshView(T data) {
    }

    protected abstract View initView();
}
