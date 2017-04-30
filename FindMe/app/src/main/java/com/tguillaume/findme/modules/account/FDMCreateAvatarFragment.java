package com.tguillaume.findme.modules.account;
/**
 * @Project : AND_FindMe
 *
 * FDMCreateAvatarFragment.java
 *
 * Created by TARTARA Guillaume on 30/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.tguillaume.findme.R;
import com.tguillaume.findme.common.mainClass.FDMMainFragment;
import com.tguillaume.findme.common.manager.implementation.FDMCreateAvatarListFragment;
import com.tguillaume.findme.common.manager.key.FDMEnum;
import com.tguillaume.findme.common.manager.key.FDMSharedPrefKey;
import com.tguillaume.findme.modules.account.adapter.FDMCreateAvatarViewpagerAdapter;

import java.util.List;


public class FDMCreateAvatarFragment extends FDMMainFragment implements View.OnClickListener{

    private static final String TAG = FDMCreateAvatarFragment.class.getSimpleName();

    private ViewPager mViewPagger;

    private FDMCreateAvatarViewpagerAdapter mAdapter;
    private ImageButton mGoLeftBtn;
    private ImageButton mGoRightBtn;
    private Button mValidateBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        init();
        super.onCreateView(inflater, container, savedInstanceState);
        View rView = inflater.inflate(R.layout.fragment_create_avatar, container, false);
        mViewPagger = (ViewPager) rView.findViewById(R.id.fragment_create_avatar_viewpager);
        mGoLeftBtn = (ImageButton) rView.findViewById(R.id.fragment_create_avatar_go_left_btn);
        mGoRightBtn = (ImageButton)rView.findViewById(R.id.fragment_create_avatar_go_right_btn);
        mValidateBtn = (Button)rView.findViewById(R.id.fragment_create_avatar_create_account_btn);

        mGoLeftBtn.setColorFilter(mContext.getResources().getColor(R.color.fdm_white));
        mGoRightBtn.setColorFilter(mContext.getResources().getColor(R.color.fdm_white));

        mGoLeftBtn.setOnClickListener(this);
        mGoRightBtn.setOnClickListener(this);
        mValidateBtn.setOnClickListener(this);

        List tListFragment;
        if (mSharedPrefManager.getBoolean(FDMSharedPrefKey.IS_WOMAN)) {
            tListFragment = FDMCreateAvatarListFragment.getInstance(mContext).getListWomanFragment();
        } else{
            tListFragment = FDMCreateAvatarListFragment.getInstance(mContext).getListManFragment();
        }
        AppCompatActivity tActivity = (AppCompatActivity)mContext;
        mAdapter = new FDMCreateAvatarViewpagerAdapter(tActivity.getSupportFragmentManager(),tListFragment);

        mViewPagger.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {
                displayGoLeft(position);
                displayGoRight(position);
            }
        });

        displayGoLeft(0);
        displayGoRight(0);

        mViewPagger.setAdapter(mAdapter);
        return rView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_create_avatar_go_left_btn:
                int tCurrentItem = mViewPagger.getCurrentItem();
                tCurrentItem --;
                mViewPagger.setCurrentItem(tCurrentItem,true);
                break;
            case R.id.fragment_create_avatar_go_right_btn:
                tCurrentItem = mViewPagger.getCurrentItem();
                tCurrentItem ++;
                mViewPagger.setCurrentItem(tCurrentItem,true);
                break;

            case R.id.fragment_create_avatar_create_account_btn:
                tCurrentItem = mViewPagger.getCurrentItem();
                int tIndexAvatar = mAdapter.getIndexAvatar(tCurrentItem);
                mSharedPrefManager.putInt(FDMSharedPrefKey.AVATAR_INDEX_CONSTANTE,tIndexAvatar);
                mSharedPrefManager.putBoolean(FDMSharedPrefKey.PROFIL_IS_CREATE, true);
                mFragmentListener.showFragment(FDMEnum.EnumNameFragment.PLAY_VIEW);
                break;
        }
    }

    public void displayGoLeft(int sPositition){
        if(sPositition > 0 ){
            mGoLeftBtn.setVisibility(View.VISIBLE);
        }else {
            mGoLeftBtn.setVisibility(View.GONE);
        }
    }
    public void displayGoRight(int sPositition){
        if(sPositition < mAdapter.getCount() - 1){
            mGoRightBtn.setVisibility(View.VISIBLE);
        }else {
            mGoRightBtn.setVisibility(View.GONE);
        }
    }
}
