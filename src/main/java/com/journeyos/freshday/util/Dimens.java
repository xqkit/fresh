package com.journeyos.freshday.util;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by mike.li on 2017/2/22.
 */

public class Dimens {
    public static int dp2px(int dp, DisplayMetrics metrics) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }
}
