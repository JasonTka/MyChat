package com.jason.mychat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.jason.framework.base.BaseUIActivity;
import com.jason.mychat.fragment.ChatFragment;
import com.jason.mychat.fragment.MeFragment;
import com.jason.mychat.fragment.SquareFragment;
import com.jason.mychat.fragment.StarFragment;

import java.util.List;

public class MainActivity extends BaseUIActivity implements View.OnClickListener {

    private static final int PERMISSION_REQUEST_CODE = 1000;

    private FrameLayout mMMainLayout;

    private ImageView mIvStar;
    private TextView mTvStar;
    private LinearLayout mLlStar;
    private StarFragment mStarFragment = null;
    private FragmentTransaction mStarTransaction = null;

    private ImageView mIvSquare;
    private TextView mTvSquare;
    private LinearLayout mLlSquare;
    private SquareFragment mSquareFragment = null;
    private FragmentTransaction mSquareTransaction = null;

    private ImageView mIvChat;
    private TextView mTvChat;
    private LinearLayout mLlChat;
    private ChatFragment mChatFragment = null;
    private FragmentTransaction mChatTransaction = null;

    private ImageView mIvMe;
    private TextView mTvMe;
    private LinearLayout mLlMe;
    private MeFragment mMeFragment = null;
    private FragmentTransaction mMeTransaction = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }

    private void initView() {
        requestPermiss();

        mMMainLayout = findViewById(R.id.mMainLayout);

        mIvStar = findViewById(R.id.iv_star);
        mTvStar = findViewById(R.id.tv_star);
        mLlStar = findViewById(R.id.ll_star);

        mIvSquare = findViewById(R.id.iv_square);
        mTvSquare = findViewById(R.id.tv_square);
        mLlSquare = findViewById(R.id.ll_square);

        mIvChat = findViewById(R.id.iv_chat);
        mTvChat = findViewById(R.id.tv_chat);
        mLlChat = findViewById(R.id.ll_chat);

        mIvMe = findViewById(R.id.iv_me);
        mTvMe = findViewById(R.id.tv_me);
        mLlMe = findViewById(R.id.ll_me);

        mLlStar.setOnClickListener(this);
        mLlSquare.setOnClickListener(this);
        mLlChat.setOnClickListener(this);
        mLlMe.setOnClickListener(this);

        initFragment();

        checkMainTab(0);
    }

    private void initFragment() {
        //星球
        if (mStarFragment == null) {
            mStarFragment = new StarFragment();
            mStarTransaction = getSupportFragmentManager().beginTransaction();
            mStarTransaction.add(R.id.mMainLayout, mStarFragment);
            mStarTransaction.commit();
        }

        //广场
        if (mSquareFragment == null) {
            mSquareFragment = new SquareFragment();
            mSquareTransaction = getSupportFragmentManager().beginTransaction();
            mSquareTransaction.add(R.id.mMainLayout, mSquareFragment);
            mSquareTransaction.commit();
        }

        //聊天
        if (mChatFragment == null) {
            mChatFragment = new ChatFragment();
            mChatTransaction = getSupportFragmentManager().beginTransaction();
            mChatTransaction.add(R.id.mMainLayout, mChatFragment);
            mChatTransaction.commit();
        }

        //我
        if (mMeFragment == null) {
            mMeFragment = new MeFragment();
            mMeTransaction = getSupportFragmentManager().beginTransaction();
            mMeTransaction.add(R.id.mMainLayout, mMeFragment);
            mMeTransaction.commit();
        }

    }

    private void showFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            hideAllFragment(transaction);
            transaction.show(fragment);
            transaction.commitAllowingStateLoss();
        }
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (mStarFragment != null) {
            transaction.hide(mStarFragment);
        }

        if (mSquareFragment != null) {
            transaction.hide(mSquareFragment);
        }

        if (mChatFragment != null) {
            transaction.hide(mChatFragment);
        }

        if (mMeFragment != null) {
            transaction.hide(mMeFragment);
        }
    }

    private void checkMainTab(int index) {
        switch (index) {
            case 0:
                showFragment(mStarFragment);

                mIvStar.setImageResource(R.drawable.img_star_p);
                mIvSquare.setImageResource(R.drawable.img_square);
                mIvChat.setImageResource(R.drawable.img_chat);
                mIvMe.setImageResource(R.drawable.img_me);

                mTvStar.setTextColor(getResources().getColor(R.color.colorAccent));
                mTvSquare.setTextColor(Color.BLACK);
                mTvChat.setTextColor(Color.BLACK);
                mTvMe.setTextColor(Color.BLACK);

                break;
            case 1:
                showFragment(mSquareFragment);

                mIvStar.setImageResource(R.drawable.img_star);
                mIvSquare.setImageResource(R.drawable.img_square_p);
                mIvChat.setImageResource(R.drawable.img_chat);
                mIvMe.setImageResource(R.drawable.img_me);

                mTvStar.setTextColor(Color.BLACK);
                mTvSquare.setTextColor(getResources().getColor(R.color.colorAccent));
                mTvChat.setTextColor(Color.BLACK);
                mTvMe.setTextColor(Color.BLACK);

                break;
            case 2:
                showFragment(mChatFragment);

                mIvStar.setImageResource(R.drawable.img_star);
                mIvSquare.setImageResource(R.drawable.img_square);
                mIvChat.setImageResource(R.drawable.img_chat_p);
                mIvMe.setImageResource(R.drawable.img_me);

                mTvStar.setTextColor(Color.BLACK);
                mTvSquare.setTextColor(Color.BLACK);
                mTvChat.setTextColor(getResources().getColor(R.color.colorAccent));
                mTvMe.setTextColor(Color.BLACK);
                break;
            case 3:
                showFragment(mMeFragment);

                mIvStar.setImageResource(R.drawable.img_star);
                mIvSquare.setImageResource(R.drawable.img_square);
                mIvChat.setImageResource(R.drawable.img_chat);
                mIvMe.setImageResource(R.drawable.img_me_p);

                mTvStar.setTextColor(Color.BLACK);
                mTvSquare.setTextColor(Color.BLACK);
                mTvChat.setTextColor(Color.BLACK);
                mTvMe.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
        }
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

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (mStarFragment != null && fragment instanceof StarFragment) {
            mStarFragment = (StarFragment) fragment;
        }

        if (mSquareFragment != null && fragment instanceof SquareFragment) {
            mSquareFragment = (SquareFragment) fragment;
        }

        if (mChatFragment != null && fragment instanceof ChatFragment) {
            mChatFragment = (ChatFragment) fragment;
        }

        if (mMeFragment != null && fragment instanceof MeFragment) {
            mMeFragment = (MeFragment) fragment;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_star:
                checkMainTab(0);
                break;
            case R.id.ll_square:
                checkMainTab(1);
                break;
            case R.id.ll_chat:
                checkMainTab(2);
                break;
            case R.id.ll_me:
                checkMainTab(3);
                break;
        }
    }
}