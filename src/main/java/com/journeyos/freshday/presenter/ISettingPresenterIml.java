package com.journeyos.freshday.presenter;

import com.journeyos.freshday.view.ISettingView;

/**
 * Created by mike.li on 2017/2/28.
 */

public class ISettingPresenterIml implements ISettingPresenter {
    private ISettingView isv;

    public ISettingPresenterIml(ISettingView isv) {
        this.isv = isv;
    }

    @Override
    public void login(boolean loged) {
        if(loged){

            isv.onLogoff();
        }else {
            isv.onLogin();
        }
    }
}
