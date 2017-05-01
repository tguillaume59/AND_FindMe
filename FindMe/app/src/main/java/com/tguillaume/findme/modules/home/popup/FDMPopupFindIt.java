package com.tguillaume.findme.modules.home.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tguillaume.findme.R;
import com.tguillaume.findme.common.manager.interfaces.FDMFragmentListener;
import com.tguillaume.findme.common.manager.key.FDMEnum;

/**
 * @Project : AND_FindMe
 *
 * FDMPopupFindIt.java
 *
 * Created by TARTARA Guillaume on 01/05/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */


public class FDMPopupFindIt extends DialogFragment implements View.OnClickListener{

    private static final String TAG = FDMPopupFindIt.class.getSimpleName();
    private Context mContext;
    private FDMFragmentListener mFragmentListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.i(TAG, "onCreateView");
        mContext = getContext();

        View rView = inflater.inflate(R.layout.popup_findit, container, false);
        this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button tChatBtn = (Button)rView.findViewById(R.id.popup_find_chat_btn);
        tChatBtn.setOnClickListener(this);
        return rView;
    }

    public void setFragmentListener(FDMFragmentListener sFragmentListener) {
        this.mFragmentListener = sFragmentListener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.popup_find_chat_btn:
                if(mFragmentListener != null){
                    mFragmentListener.showFragment(FDMEnum.EnumNameFragment.MESSAGE);
                }
                break;
        }
    }
}
