package com.tguillaume.findme.modules.messages;
/**
 * @Project : AND_FindMe
 *
 * FDMListMessageFragment.java
 *
 * Created by TARTARA Guillaume on 01/05/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.tguillaume.findme.R;
import com.tguillaume.findme.common.mainClass.FDMMainFragment;
import com.tguillaume.findme.common.manager.key.FDMEnum;
import com.tguillaume.findme.common.manager.key.FDMSharedPrefKey;
import com.tguillaume.findme.modules.messages.adpater.FDMListMessageAdapter;
import com.tguillaume.findme.modules.messages.entity.FDMContactEntity;

import java.util.ArrayList;


public class FDMListMessageFragment extends FDMMainFragment implements View.OnClickListener, AdapterView.OnItemClickListener{

    private static final String TAG = FDMListMessageFragment.class.getSimpleName();
    private ImageButton mLogoutBtn;
    private ListView mListViewMessages;
    private ArrayList<FDMContactEntity> mContactList;
    private FDMListMessageAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        super.onCreateView(inflater, container, savedInstanceState);
        Log.i(TAG,"onCreateView");
        View rView = inflater.inflate(R.layout.fragment_list_messages,container,false);

        mListViewMessages = (ListView)rView.findViewById(R.id.fragmet_list_messages_listview);
        mLogoutBtn = (ImageButton)rView.findViewById(R.id.fragment_list_messages_logout_btn);

        mListViewMessages.setOnItemClickListener(this);
        mLogoutBtn.setOnClickListener(this);

        //contact
        mContactList = new ArrayList<>();
        mContactList.add(new FDMContactEntity("Dorian","02/05/2017",""));
        mContactList.add(new FDMContactEntity("Sebastien","29/05/2017","Je viens d'arriver à Bordeaux et toi ?"));
        mContactList.add(new FDMContactEntity("Laure","21/05/2017","Hey ! comment tu vas ? ;)"));
        mContactList.add(new FDMContactEntity("Mathilde","15/05/2017","Ca serait cool de se revoir un de ces quatre"));

        mAdapter = new FDMListMessageAdapter(mContext,R.layout.item_list_message,mContactList);
        mListViewMessages.setAdapter(mAdapter);

        return rView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_list_messages_logout_btn:
                mSharedPrefManager.putBoolean(FDMSharedPrefKey.PROFIL_IS_CREATE, false);
                mSharedPrefManager.putString(FDMSharedPrefKey.PSEUDO, "");
                mSharedPrefManager.putInt(FDMSharedPrefKey.AVATAR_INDEX_CONSTANTE, -1);
                mFragmentListener.showFragment(FDMEnum.EnumNameFragment.ACCUEIL_FRAGMENT);
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle tBundle = new Bundle();
        tBundle.putString(FDMSharedPrefKey.NAME_CLICK, mAdapter.getName(i));

        mFragmentListener.showFragment(FDMEnum.EnumNameFragment.MESSAGE, tBundle);

    }
}
