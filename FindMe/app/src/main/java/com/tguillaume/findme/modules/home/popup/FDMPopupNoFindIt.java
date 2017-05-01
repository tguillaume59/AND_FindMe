package com.tguillaume.findme.modules.home.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tguillaume.findme.R;

/**
 * @Project : AND_FindMe
 *
 * FDMPopupNoFindIt.java
 *
 * Created by TARTARA Guillaume on 01/05/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */


public class FDMPopupNoFindIt extends DialogFragment implements View.OnClickListener{

    private static final String TAG = FDMPopupNoFindIt.class.getSimpleName();
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.i(TAG, "onCreateView");
        mContext = getContext();

        View rView = inflater.inflate(R.layout.popup_not_findit, container, false);
        this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return rView;
    }

    @Override
    public void onClick(View view) {

    }
}
