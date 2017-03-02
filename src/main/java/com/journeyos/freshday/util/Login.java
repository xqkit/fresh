package com.journeyos.freshday.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.journeyos.freshday.APP;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.journeyos.freshday.R;

import java.util.Map;

/**
 * Created by mike.li on 2017/2/22.
 */

public class Login {
    public interface Result{
        void getResult(Map<String, String> map);
    }
    public static void login(final Context context, View parent, final Result result) {
        final SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        View contentView = LayoutInflater.from(context).inflate(R.layout.pw_login, null);
        final PopupWindow popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        TextView qqlogin = (TextView) contentView.findViewById(R.id.qq_login);
        TextView weixinlogin = (TextView) contentView.findViewById(R.id.weixin_login);
        qqlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录
                UMShareAPI umShareAPI = APP.getUmShareAPI();
                umShareAPI.getPlatformInfo((Activity) context, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.i("ttt", "onStart: ");
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Log.i("ttt", "onComplete: " + map.toString());
                        sp.edit().putBoolean("login",true).apply();
                        sp.edit().putString("user",map.get("iconurl")).apply();
                        popupWindow.dismiss();
                        result.getResult(map);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Log.i("ttt", "onComplete: " + throwable.toString());
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                    }
                });
//                            holder.start.setText("已预约");
//                            startActivity(new Intent(context,LiveActivity.class));
            }
        });
        weixinlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录
                UMShareAPI umShareAPI = APP.getUmShareAPI();
                umShareAPI.getPlatformInfo((Activity) context, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.i("ttt", "onStart: ");

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Log.i("ttt", "onComplete: " + map.toString());
                        sp.edit().putBoolean("login",true).apply();
                        sp.edit().putString("user",map.toString()).apply();
                        popupWindow.dismiss();
                        result.getResult(map);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Log.i("ttt", "onComplete: " + throwable.toString());
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
            }
        });

//        popupWindow.setOutsideTouchable(false);
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }
}
