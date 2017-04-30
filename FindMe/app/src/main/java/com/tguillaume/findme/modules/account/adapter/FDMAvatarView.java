package com.tguillaume.findme.modules.account.adapter;
/**
 * @Project : AND_FindMe
 *
 * FDMAvatarView.java
 *
 * Created by TARTARA Guillaume on 30/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tguillaume.findme.R;
import com.tguillaume.findme.common.mainClass.FDMMainFragment;
import com.tguillaume.findme.common.manager.key.FDMSharedPrefKey;


public class FDMAvatarView extends FDMMainFragment {

    private static final String TAG =  FDMAvatarView.class.getSimpleName();
    private ImageView mAvatarImageView ;
    private int mAvatarIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        super.onCreateView(inflater, container, savedInstanceState);
        View rView = inflater.inflate(R.layout.view_avatar_viewpager, container, false);
        mAvatarImageView = (ImageView)rView.findViewById(R.id.view_avatar_imageview);

        Bundle tBundle = this.getArguments();
        if(tBundle != null){
            int tIndexDrawable = tBundle.getInt(FDMSharedPrefKey.AVATAR_ID);
            mAvatarIndex = tBundle.getInt(FDMSharedPrefKey.AVATAR_INDEX_CONSTANTE);
            mAvatarImageView.setImageDrawable(mContext.getResources().getDrawable(tIndexDrawable));
        }

        return rView;
    }

    public int getAvatarIndex(){
        return mAvatarIndex;
    }
}
