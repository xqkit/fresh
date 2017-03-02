package com.journeyos.freshday.view.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.journeyos.freshday.view.ISettingView;
import com.journeyos.freshday.view.collections.AccountActivity;
import com.journeyos.freshday.view.collections.CollectActivity;
import com.journeyos.freshday.view.collections.OrderActivity;
import com.journeyos.freshday.view.collections.PlayedActivity;
import com.journeyos.freshday.presenter.ISettingPresenterIml;
import com.journeyos.freshday.R;


public class SettingActivity extends Activity implements ISettingView,View.OnClickListener {


    private ImageView mUserIc;
    private TextView mUserLogin;
    private RelativeLayout mUser;
    private TextView mMine;
    private Button mH5game;
    private Button mCollect;
    private Button mGameOrder;
    private Button mAccount;
    private RelativeLayout mActivitySetting;
    private ISettingPresenterIml presenter;
    private boolean loged;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initActionBar();
        initView();
        sp = getSharedPreferences("login", MODE_PRIVATE);
        if(sp.getBoolean("loginthree",false)){

        }
        presenter = new ISettingPresenterIml(this);
    }

    private void initActionBar() {
        ActionBar bar = getActionBar();
        if(bar!=null){
            bar.setTitle("FreshDay设置");
            bar.setHomeButtonEnabled(true);
            bar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initView() {

        mUserIc = (ImageView) findViewById(R.id.user_ic);
        mUserLogin = (TextView) findViewById(R.id.user_login);
        mUser = (RelativeLayout) findViewById(R.id.user);
        mUser.setOnClickListener(this);
        mMine = (TextView) findViewById(R.id.mine);
        mMine.setOnClickListener(this);
        mH5game = (Button) findViewById(R.id.h5game);
        mH5game.setOnClickListener(this);
        mCollect = (Button) findViewById(R.id.collect);
        mCollect.setOnClickListener(this);
        mGameOrder = (Button) findViewById(R.id.game_order);
        mGameOrder.setOnClickListener(this);
        mAccount = (Button) findViewById(R.id.account);
        mAccount.setOnClickListener(this);
        mActivitySetting = (RelativeLayout) findViewById(R.id.activity_setting);
        mActivitySetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user:
                if(loged){
                    presenter.login(loged);
                    loged = false;
                }else {
                    presenter.login(loged);
                    loged = true;
                }
                Toast.makeText(this, "登录酷派账号", Toast.LENGTH_SHORT).show();
                break;
            case R.id.h5game:
                startActivity(new Intent(this,PlayedActivity.class));
                break;
            case R.id.collect:
                startActivity(new Intent(this,CollectActivity.class));
                break;
            case R.id.game_order:
                startActivity(new Intent(this,OrderActivity.class));
                break;
            case R.id.account:
                startActivity(new Intent(this,AccountActivity.class));
                break;
        }
    }

    @Override
    public void onLogin() {
        //登录界面变化
        mUserLogin.setText("已登录");
    }

    @Override
    public void onLogoff() {
        mUserLogin.setText("登录/注册");
    }
}
