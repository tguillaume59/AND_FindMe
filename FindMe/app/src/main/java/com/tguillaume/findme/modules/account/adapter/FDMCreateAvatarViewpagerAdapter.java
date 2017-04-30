package com.tguillaume.findme.modules.account.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * @Project : AND_FindMe
 *
 * FDMCreateAvatarViewpagerAdapter.java
 *
 * Created by TARTARA Guillaume on 30/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */



public class FDMCreateAvatarViewpagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = FDMCreateAvatarViewpagerAdapter.class.getSimpleName();

    private final List<Fragment> mListFragments;

    //On fournit à l'adapter la liste des mListFragments à afficher
    public FDMCreateAvatarViewpagerAdapter(FragmentManager fm, List sListFragments) {
        super(fm);
        Log.i(TAG , "constructeur : FDMCreateAvatarViewpagerAdapter");
        this.mListFragments = sListFragments;
    }

    @Override
    public int getCount() {
        return this.mListFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragments.get(position);
    }

    public int getIndexAvatar(int sCurrentItem) {
        return ((FDMAvatarView)mListFragments.get(sCurrentItem)).getAvatarIndex();
    }
}
