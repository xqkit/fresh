package com.journeyos.freshday;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by mike.li on 2017/2/22.
 */

public class APP extends Application {
    private static UMShareAPI umShareAPI;

    {PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
    PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");}

    @Override
    public void onCreate() {

        umShareAPI = UMShareAPI.get(this);
    }

    public static UMShareAPI getUmShareAPI() {
        return umShareAPI;
    }
}
