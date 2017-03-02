package com.journeyos.freshday.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.journeyos.freshday.R;

public class SplashActivity extends Activity implements View.OnClickListener {

    private SharedPreferences splash;
    private Button mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        splash = getSharedPreferences("splash", MODE_PRIVATE);
        if (splash.getBoolean("started", false)) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }

    private void initView() {
        mStart = (Button) findViewById(R.id.start);

        mStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                new AlertDialog.Builder(this)
                        .setMessage("是否允许？")
                        .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                splash.edit().putBoolean("started",true).apply();
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
        }
    }
}
