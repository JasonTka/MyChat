package com.jason.framework;

import android.content.Context;

import com.jason.framework.bmob.BmobManager;
import com.jason.framework.utils.SpUtils;

public class Framework {

    private volatile static Framework mFramework;

    private Framework() {

    }

    public static Framework getFramework() {
        if (mFramework == null) {
            synchronized (Framework.class) {
                if (mFramework == null) {
                    mFramework = new Framework();
                }
            }
        }
        return mFramework;
    }

    public void initFramework(Context mContext) {
        SpUtils.getInstance().initSp(mContext);
        BmobManager.getInstance().initBmob(mContext);
    }
}
