package com.tguillaume.findme.modules.accueil;
/**
 * @Project : AND_FindMe
 *
 * FDMAccueilFragment.java
 *
 * Created by TARTARA Guillaume on 05/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.tguillaume.findme.R;
import com.tguillaume.findme.common.mainClass.FDMMainFragment;
import com.tguillaume.findme.common.manager.key.FDMEnum;
import com.tguillaume.findme.common.manager.key.FDMSharedPrefKey;


public class FDMAccueilFragment extends FDMMainFragment implements View.OnClickListener {

    private static final String TAG = FDMAccueilFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);
        init();
        View rView = inflater.inflate(R.layout.fragment_accueil, container,false);
        ImageButton tButtonPlay = (ImageButton) rView.findViewById(R.id.fragment_accueil_play_btn);
        tButtonPlay.setOnClickListener(this);

        return rView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_accueil_play_btn :
                if(!mSharedPrefManager.getBoolean(FDMSharedPrefKey.PROFIL_IS_CREATE)){
                    //todo afficher le fragment de creation du profil
                    mFragmentListener.showFragment(FDMEnum.EnumNameFragment.PLAY_VIEW);
                }else {
                    //profil créé , on affiche le fragment pour jouer
                    mFragmentListener.showFragment(FDMEnum.EnumNameFragment.PLAY_VIEW);
                }
                break;
        }
    }
}
