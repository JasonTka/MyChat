package com.jason.framework.bmob;

import android.content.Context;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

public class BmobManager {

    private static final String BMOB_SDK_ID = "2e43af343202310a1429b8bd6c03e375";

    private static BmobManager mInstance = null;

    private BmobManager() {

    }

    public static BmobManager getInstance() {
        if (mInstance == null) {
            synchronized (BmobManager.class) {
                if (mInstance == null) {
                    mInstance = new BmobManager();
                }
            }
        }
        return mInstance;
    }

    public void initBmob(Context mContext) {
        Bmob.initialize(mContext, BMOB_SDK_ID);
    }

    public boolean isLogin(){
        return BmobUser.isLogin();
    }

    //获取本地对象
    public IMUser getUser() {
        return BmobUser.getCurrentUser(IMUser.class);
    }

    //发送短信验证
    public void requestSMS(String phone, QueryListener<Integer> listener) {
        BmobSMS.requestSMSCode(phone, "", listener);
    }

    //手机一键注册或登录
    public void signOrLoginByMobilePhone(String phone, String code, LogInListener<IMUser> listener) {
        BmobUser.signOrLoginByMobilePhone(phone, code, listener);
    }
}
