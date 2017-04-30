package com.tguillaume.findme.common.manager.implementation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.tguillaume.findme.R;
import com.tguillaume.findme.common.mainClass.FDMMainFragment;
import com.tguillaume.findme.common.manager.key.FDMSharedPrefKey;
import com.tguillaume.findme.modules.account.adapter.FDMAvatarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @Project : AND_FindMe
 *
 * FDMCreateAvatarListFragment.java
 *
 * Created by TARTARA Guillaume on 30/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */


public class FDMCreateAvatarListFragment {

    private static final String TAG = FDMCreateAvatarListFragment.class.getSimpleName();
    private static FDMCreateAvatarListFragment mInstance;
    private Context mContext;

    public static final int AVATAR_MAN_1 = 1001;
    public static final int AVATAR_MAN_2 = 1002;
    public static final int AVATAR_MAN_3 = 1003;
    public static final int AVATAR_MAN_4 = 1004;

    public static final int AVATAR_WOMAN_1 = 2001;
    public static final int AVATAR_WOMAN_2 = 2002;
    public static final int AVATAR_WOMAN_3 = 2003;
    public static final int AVATAR_WOMAN_4 = 2004;

    private FDMCreateAvatarListFragment(Context sContext){
        mContext = sContext;
    }

    public static FDMCreateAvatarListFragment getInstance(Context sContext){
        if(mInstance == null){
            mInstance = new  FDMCreateAvatarListFragment(sContext);
        }

        return mInstance;
    }

    public int getIndexDrawable(int sAvatar){
        Log.i(TAG, "getIndexDrawable : "+ sAvatar);
        int rValue = -1;
        switch (sAvatar){
            case AVATAR_MAN_1:
                rValue = R.drawable.ic_man1;
                break;
            case AVATAR_MAN_2:
                rValue = R.drawable.ic_man2;
                break;
            case AVATAR_MAN_3:
                rValue = R.drawable.ic_man3;
                break;
            case AVATAR_MAN_4:
                rValue = R.drawable.ic_man4;
                break;
            case AVATAR_WOMAN_1:
                rValue = R.drawable.ic_woman1;
                break;
            case AVATAR_WOMAN_2:
                rValue = R.drawable.ic_woman2;
                break;
            case AVATAR_WOMAN_3:
                rValue = R.drawable.ic_woman3;
                break;
            case AVATAR_WOMAN_4:
                rValue = R.drawable.ic_woman4;
                break;
        }
        return rValue;
    }

    public Bundle createBundle(int sAvatarIndex){
        Log.i(TAG, "createBundle : "+ sAvatarIndex);
        Bundle rBundle = new Bundle();
        rBundle.putInt(FDMSharedPrefKey.AVATAR_INDEX_CONSTANTE, sAvatarIndex);
        rBundle.putInt(FDMSharedPrefKey.AVATAR_ID, getIndexDrawable(sAvatarIndex));
        return rBundle;
    }

    public List getListManFragment(){
        Log.i(TAG, "getListManFragment");
        List rList = new Vector();
        rList.add(FDMAvatarView.instantiate(mContext,FDMAvatarView.class.getName(), createBundle(AVATAR_MAN_1)));
        rList.add(FDMAvatarView.instantiate(mContext,FDMAvatarView.class.getName(), createBundle(AVATAR_MAN_2)));
        rList.add(FDMAvatarView.instantiate(mContext,FDMAvatarView.class.getName(), createBundle(AVATAR_MAN_3)));
        rList.add(FDMAvatarView.instantiate(mContext,FDMAvatarView.class.getName(), createBundle(AVATAR_MAN_4)));

        return rList;
    }

    public List getListWomanFragment(){
        Log.i(TAG, "getListManFragment");
        List rList = new Vector();
        rList.add(FDMAvatarView.instantiate(mContext,FDMAvatarView.class.getName(), createBundle(AVATAR_WOMAN_1)));
        rList.add(FDMAvatarView.instantiate(mContext,FDMAvatarView.class.getName(), createBundle(AVATAR_WOMAN_2)));
        rList.add(FDMAvatarView.instantiate(mContext,FDMAvatarView.class.getName(), createBundle(AVATAR_WOMAN_3)));
        rList.add(FDMAvatarView.instantiate(mContext,FDMAvatarView.class.getName(), createBundle(AVATAR_WOMAN_4)));

        return rList;
    }



}
