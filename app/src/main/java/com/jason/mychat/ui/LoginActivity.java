package com.jason.mychat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.jason.framework.base.BaseUIActivity;
import com.jason.framework.bmob.BmobManager;
import com.jason.framework.bmob.IMUser;
import com.jason.framework.entity.Constants;
import com.jason.framework.manager.DialogManager;
import com.jason.framework.utils.SpUtils;
import com.jason.framework.view.DialogView;
import com.jason.framework.view.TouchPictureV;
import com.jason.mychat.MainActivity;
import com.jason.mychat.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

public class LoginActivity extends BaseUIActivity implements View.OnClickListener {

    private EditText mEtPhone;
    private EditText mEtCode;
    private Button mBtnSendCode;
    private Button mBtnLogin;

    private TouchPictureV mPictureV;

    private static final int H_TIME = 1001;

    private static int TIME = 60;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case H_TIME:
                    TIME--;
                    mBtnSendCode.setText(TIME + "s");
                    if (TIME > 0) {
                        mHandler.sendEmptyMessageDelayed(H_TIME, 1000);
                    } else {
                        mBtnSendCode.setEnabled(true);
                        mBtnSendCode.setText(getString(R.string.text_login_send));
                    }
                    break;
            }
            return false;
        }
    });

    DialogView mCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {

        initDialogView();

        mEtPhone = findViewById(R.id.et_phone);
        mEtCode = findViewById(R.id.et_code);
        mBtnSendCode = findViewById(R.id.btn_send_code);
        mBtnLogin = findViewById(R.id.btn_login);

        mBtnSendCode.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);

        String phone = SpUtils.getInstance().getString(Constants.SP_PHONE, "");
        if (!TextUtils.isEmpty(phone)) {
            mEtPhone.setText(phone);
        }
    }

    private void initDialogView() {
        mCodeView = DialogManager.getInstance().initView(this, R.layout.dialog_code_view);
        mPictureV = mCodeView.findViewById(R.id.mPictureV);
        mPictureV.setViewResultListener(() -> {
            DialogManager.getInstance().hide(mCodeView);
            sendSMS();
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_code:
                DialogManager.getInstance().show(mCodeView);
                break;
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void login() {
        final String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String code = mEtCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        BmobManager.getInstance().signOrLoginByMobilePhone(phone, code, new LogInListener<IMUser>() {
            @Override
            public void done(IMUser imUser, BmobException e) {
                if (e == null) {
                    //登录成功
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    //保存手机号
                    SpUtils.getInstance().putString(Constants.SP_PHONE, phone);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendSMS() {
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        BmobManager.getInstance().requestSMS(phone, new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    mBtnSendCode.setEnabled(false);
                    mHandler.sendEmptyMessage(H_TIME);
                    Toast.makeText(LoginActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}