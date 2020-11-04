package com.jason.mychat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jason.framework.base.BaseUIActivity;
import com.jason.framework.utils.LogUtils;

import java.util.List;

public class MainActivity extends BaseUIActivity {

    private static final int PERMISSION_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermiss();
    }

    //请求权限
    private void requestPermiss() {
        request(PERMISSION_REQUEST_CODE, new OnPermissionResult() {
            @Override
            public void OnSuccess() {

            }

            @Override
            public void OnFail(List<String> noPermissions) {

            }
        });

    }


}