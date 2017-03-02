package com.journeyos.freshday.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by mike.li on 2017/2/28.
 */

public class Network {
    public static int NetworkStateWifi=0;
    public static int NetworkStateMOBILE=1;
    public static int NetworkStateNo=2;
    public static int getNetWorkType(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String type = networkInfo.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                Log.i("ttt", "getNetWorkType: wifi");
                return NetworkStateWifi;
            } else if (type.equalsIgnoreCase("MOBILE")) {
                Log.i("ttt", "getNetWorkType: MOBILE");
                return NetworkStateMOBILE;
            }
        }
        return NetworkStateNo;
    }
}
