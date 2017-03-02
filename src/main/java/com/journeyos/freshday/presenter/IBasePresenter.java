package com.journeyos.freshday.presenter;

import java.util.List;



/**
 * Created by mike.li on 2017/2/28.
 */

public interface IBasePresenter<T> {

    //刷新
    void refresh(List<T> list);
    //加载
    void loading(List<T> list);
    //
}
