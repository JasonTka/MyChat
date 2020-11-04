package com.jason.mychat.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.jason.framework.base.BaseUIActivity;
import com.jason.framework.entity.Constants;
import com.jason.framework.utils.SpUtils;
import com.jason.mychat.MainActivity;
import com.jason.mychat.R;

public class IndexActivity extends AppCompatActivity {

    private static final int SKIP_MAIN = 1000;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case SKIP_MAIN:
                    startMain();
            }
            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);


        mHandler.sendEmptyMessageDelayed(SKIP_MAIN, 2 * 1000);
    }

    private void startMain() {
        boolean isFirstApp = SpUtils.getInstance().getBoolean(Constants.SP_IS_FIRST_APP, true);
        Intent intent = new Intent();
        if (isFirstApp) {
            //跳转到引导页
            intent.setClass(this, GuideActivity.class);
            SpUtils.getInstance().putBoolean(Constants.SP_IS_FIRST_APP, false);
        } else {
            //非第一次启动，是否曾登录
            String token = SpUtils.getInstance().getString(Constants.SP_TOKEN, "");
            if (TextUtils.isEmpty(token)) {
                //没登录，跳转登录页
                intent.setClass(this, LoginActivity.class);
            } else {
                //跳转主页
                intent.setClass(this, MainActivity.class);
            }
        }
        startActivity(intent);
        finish();
    }
}