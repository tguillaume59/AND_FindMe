package com.tguillaume.findme.common.manager.implementation;

import com.tguillaume.findme.common.mainClass.FDMMainFragment;
import com.tguillaume.findme.common.manager.key.FDMEnum;
import com.tguillaume.findme.modules.account.FDMCreateAccountFragment;
import com.tguillaume.findme.modules.account.FDMCreateAvatarFragment;
import com.tguillaume.findme.modules.accueil.FDMAccueilFragment;
import com.tguillaume.findme.modules.home.fragment.FDMHomeFragment;
import com.tguillaume.findme.modules.messages.FDMListMessageFragment;
import com.tguillaume.findme.modules.messages.FDMMessageFragment;

/**
 * @Project : AND_FindMe
 *
 * FDMFragmentCreatorImpl.java
 *
 * Created by TARTARA Guillaume on 04/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */


public class FDMFragmentCreatorImpl {

    private static String TAG =  FDMFragmentCreatorImpl.class.getSimpleName();
    private static FDMFragmentCreatorImpl mInstance;

    private FDMFragmentCreatorImpl(){}

    public static FDMFragmentCreatorImpl getInstance(){
        if(mInstance == null){
            mInstance = new FDMFragmentCreatorImpl();
        }
        return mInstance;
    }


    public FDMMainFragment createFragmentWithEnum(FDMEnum.EnumNameFragment sName) {
        FDMMainFragment rFragment = null;
        switch (sName){
            case CREATE_PROFIL:
                rFragment = new FDMCreateAccountFragment();
                break;

            case CREATE_AVATAR:
                rFragment = new FDMCreateAvatarFragment();
                break;

            case ACCUEIL_FRAGMENT:
                rFragment = new FDMAccueilFragment();
                break;

            case PLAY_VIEW:
                rFragment = new FDMHomeFragment();
                break;

            case MESSAGE:
                rFragment = new FDMMessageFragment();
                break;

            case LIST_MESSAGE :
                rFragment = new FDMListMessageFragment();
                break;
        }

        return rFragment;
    }
}
