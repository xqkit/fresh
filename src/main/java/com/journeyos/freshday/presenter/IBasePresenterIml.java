package com.journeyos.freshday.presenter;


import com.journeyos.freshday.model.ItemBean;
import com.journeyos.freshday.view.IBaseView;

import java.util.List;

/**
 * Created by mike.li on 2017/2/28.
 */

public class IBasePresenterIml<T> implements IBasePresenter<T> {
    private IBaseView ibv;

    public IBasePresenterIml(IBaseView ibv) {
        this.ibv = ibv;
    }


    @Override
    public void refresh(List<T> list) {
        //增加数据
        list.add((T) new ItemBean("11:30", "电竞直播", "tab", 0));
        list.add((T) new ItemBean("11:30", "游戏直播", "tab", 1));
        list.add((T) new ItemBean("11:30", "礼包", "tab", 2));
        list.add((T) new ItemBean("11:30", "H5", "tab", 3));
        ibv.onRefresh();
    }

    @Override
    public void loading(List<T> list) {
        ibv.onLoading();
    }
}
