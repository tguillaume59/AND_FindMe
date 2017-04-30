package com.tguillaume.findme.modules.account;
/**
 * @Project : AND_FindMe
 *
 * FDMCreateAccountFragment.java
 *
 * Created by TARTARA Guillaume on 30/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tguillaume.findme.R;
import com.tguillaume.findme.common.mainClass.FDMMainFragment;
import com.tguillaume.findme.common.manager.key.FDMEnum;
import com.tguillaume.findme.common.manager.key.FDMSharedPrefKey;

public class FDMCreateAccountFragment extends FDMMainFragment implements View.OnClickListener{

    private static final String TAG = FDMCreateAccountFragment.class.getSimpleName();
    private EditText mPseudoEdittext;
    private LinearLayout mWomanLayout;
    private LinearLayout mManLayout;
    private TextView mWomanTextview;
    private TextView mManTextview;
    private Button mNextBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        super.onCreateView(inflater, container, savedInstanceState);
        View rView = inflater.inflate(R.layout.fragment_create_account,container, false);

        mPseudoEdittext = (EditText)rView.findViewById(R.id.fragment_create_account_pseudo_edittext);
        mManLayout = (LinearLayout) rView.findViewById(R.id.fragment_create_account_man_layout);
        mWomanLayout = (LinearLayout)rView.findViewById(R.id.fragment_create_account_woman_layout);
        mWomanTextview = (TextView)rView.findViewById(R.id.fragment_create_account_woman_textview);
        mManTextview = (TextView)rView.findViewById(R.id.fragment_create_account_man_textview);
        mNextBtn = (Button)rView.findViewById(R.id.fragment_create_account_create_account_btn);

        mManLayout.setOnClickListener(this);
        mWomanLayout.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        selectWoman();

        return rView;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.fragment_create_account_man_layout:
                selectMan();
                break;
            case R.id.fragment_create_account_woman_layout:
                selectWoman();
                break;
            case R.id.fragment_create_account_create_account_btn:
                String tPseudo = mPseudoEdittext.getText().toString();
                if(!tPseudo.equals("")){
                    mSharedPrefManager.putString(FDMSharedPrefKey.PSEUDO, tPseudo);
                    mFragmentListener.showFragment(FDMEnum.EnumNameFragment.CREATE_AVATAR);
                }else {
                    Toast.makeText(mContext, "Merci de saisir un pseudo valide", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private void selectWoman(){
        mManTextview.setTextColor(mContext.getResources().getColor(R.color.fdm_white));
        mWomanTextview.setTextColor(mContext.getResources().getColor(R.color.fdm_blue));
        mSharedPrefManager.putBoolean(FDMSharedPrefKey.IS_WOMAN, true);
    }

    private void selectMan(){
        mManTextview.setTextColor(mContext.getResources().getColor(R.color.fdm_blue));
        mWomanTextview.setTextColor(mContext.getResources().getColor(R.color.fdm_white));
        mSharedPrefManager.putBoolean(FDMSharedPrefKey.IS_WOMAN, false);
    }
}
