package com.jason.framework.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.framework.R;
import com.jason.framework.manager.DialogManager;
import com.jason.framework.utils.AnimUtils;

public class LoadingView {

    private DialogView mLoadingView;
    private ImageView mIvLoading;
    private TextView mTvLoadingText;

    private ObjectAnimator mAnim;

    public LoadingView(Context mContext) {
        mLoadingView = DialogManager.getInstance().initView(mContext, R.layout.dialog_loading);
        mIvLoading = mLoadingView.findViewById(R.id.iv_loading);
        mTvLoadingText = mLoadingView.findViewById(R.id.tv_loading_text);
        mAnim = AnimUtils.rotation(mIvLoading);
    }

    public void setLoadingText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mTvLoadingText.setText(text);
        }
    }

    public void show() {
        mAnim.start();
        DialogManager.getInstance().show(mLoadingView);
    }

    public void show(String text) {
        mAnim.start();
        setLoadingText(text);
        DialogManager.getInstance().show(mLoadingView);
    }

    public void hide() {
        mAnim.pause();
        DialogManager.getInstance().hide(mLoadingView);
    }
}
