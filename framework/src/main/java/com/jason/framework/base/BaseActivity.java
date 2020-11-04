package com.jason.framework.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    //声明所需权限
    private String[] mStrPermission = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //保存没同意的权限
    private List<String> mPerList = new ArrayList<>();
    //保存没有同意的失败权限
    private List<String> mPerNoList = new ArrayList<>();

    private OnPermissionResult permissionResult;

    private int requestCode;

    protected void request(int requestCode, OnPermissionResult permissionResult) {
        if (!checkPermissionsAll()) {
            requestPermissionAll(requestCode, permissionResult);
        }
    }

    //判断单个权限
    protected boolean checkPermissions(String permission) {
        int check = checkSelfPermission(permission);
        return check == PackageManager.PERMISSION_GRANTED;
    }

    //判断是否需要申请权限
    protected boolean checkPermissionsAll() {
        mPerList.clear();
        for (int i = 0; i < mStrPermission.length; i++) {
            boolean check = checkPermissions(mStrPermission[i]);
            if (!check) {
                mPerList.add(mStrPermission[i]);
            }
        }
        return mPerList.size() <= 0;
    }

    protected void requestPermission(String[] mPermission, int requestCode) {
        requestPermissions(mPermission, requestCode);
    }

    //申请所有权限
    protected void requestPermissionAll(int requestCode, OnPermissionResult permissionResult) {
        this.permissionResult = permissionResult;
        this.requestCode = requestCode;
        requestPermission((String[]) mPerList.toArray(new String[mPerList.size()]), requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPerNoList.clear();
        if (requestCode == this.requestCode) {
            if (grantResults.length > 0) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        mPerNoList.add(permissions[i]);
                    }
                }
                if (permissionResult != null) {
                    if (mPerNoList.size() == 0) {
                        permissionResult.OnSuccess();
                    } else {
                        permissionResult.OnFail(mPerNoList);
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected interface OnPermissionResult {
        void OnSuccess();

        void OnFail(List<String> noPermissions);
    }

    //窗口权限
    protected boolean checkWindowPermissions() {
        return Settings.canDrawOverlays(this);
    }

    //请求窗口权限
    protected void requestWindowPermissions(int requestCode) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package" + getPackageName()));
        startActivityForResult(intent, requestCode);
    }
}
