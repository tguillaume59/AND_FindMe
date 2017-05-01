package com.tguillaume.findme.modules.messages;
/**
 * @Project : AND_FindMe
 * <p>
 * < file name >.java
 * <p>
 * Created by TARTARA Guillaume on 01/05/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tguillaume.findme.R;
import com.tguillaume.findme.common.mainClass.FDMMainFragment;
import com.tguillaume.findme.common.manager.key.FDMSharedPrefKey;
import com.tguillaume.findme.modules.messages.adpater.FDMMessageAdapter;
import com.tguillaume.findme.modules.messages.entity.FDMMessage;

import java.util.ArrayList;

/**
 * Created by guillaume on 01/05/2017.
 */

public class FDMMessageFragment extends FDMMainFragment implements View.OnClickListener{


    private TextView mNameTextview;
    private EditText mMessageEditText;
    private ListView mMessagesListview;
    private ArrayList<FDMMessage> mListMessages;
    private FDMMessageAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        super.onCreateView(inflater, container, savedInstanceState);
        View rView = inflater.inflate(R.layout.fragment_message,container,false);
        mNameTextview = (TextView)rView.findViewById(R.id.fragment_toolbar_title_textview);
        mMessagesListview = (ListView)rView.findViewById(R.id.fragment_messages_listview);
        mMessageEditText = (EditText)rView.findViewById(R.id.fragment_messages_message_edittext);

        Bundle tBundle = this.getArguments();
        if(tBundle != null){
            String tName = tBundle.getString(FDMSharedPrefKey.NAME_CLICK);
            mNameTextview.setText(tName);
            mListMessages = new ArrayList<>();

            if(tName.equals("Dorian")){

            }else if (tName.equals("Sebastien")){
                mListMessages.add(new FDMMessage("Sebastien","Re :)"));
                mListMessages.add(new FDMMessage(mSharedPrefManager.getString(FDMSharedPrefKey.PSEUDO),"Re, comment vas tu ?"));
                mListMessages.add(new FDMMessage("Sebastien","Toujours aussi bien et toi ?"));
                mListMessages.add(new FDMMessage(mSharedPrefManager.getString(FDMSharedPrefKey.PSEUDO),"Ca va bien"));
                mListMessages.add(new FDMMessage("Sebastien","Je viens d'arriver à Bordeaux et toi ?"));
            }else if(tName.equals("Laure")){
                mListMessages.add(new FDMMessage(mSharedPrefManager.getString(FDMSharedPrefKey.PSEUDO),"Salut, ca va ? "));
                mListMessages.add(new FDMMessage("Laure","Hey ! Super et toi quoi de beau ? Tu es arrivé chez toi ?"));
                mListMessages.add(new FDMMessage(mSharedPrefManager.getString(FDMSharedPrefKey.PSEUDO),"Oui ca va.\nNon pas encore ... encore des retard"));
                mListMessages.add(new FDMMessage("Laure","Ah oui je vois ... Toujours aussi efficace ... "));
                mListMessages.add(new FDMMessage(mSharedPrefManager.getString(FDMSharedPrefKey.PSEUDO),"Enfin on fait avec, allez bonne soirée "));
                mListMessages.add(new FDMMessage(mSharedPrefManager.getString(FDMSharedPrefKey.PSEUDO),"A la prochaine"));
                mListMessages.add(new FDMMessage("Laure","Merci, toi aussi bonne soirée"));
                mListMessages.add(new FDMMessage("Laure","Bisous :) "));
                mListMessages.add(new FDMMessage(mSharedPrefManager.getString(FDMSharedPrefKey.PSEUDO),"Bisous"));
                mListMessages.add(new FDMMessage("Laure",":)"));

            }else if(tName.equals("Mathilde")){
                mListMessages.add(new FDMMessage("Mathilde","Hey ! Tu connais un peu le RER ?"));
                mListMessages.add(new FDMMessage(mSharedPrefManager.getString(FDMSharedPrefKey.PSEUDO),"Salut, oui un peu Pourquoi ?"));
                mListMessages.add(new FDMMessage("Mathilde","Je suis Paris la semaine prochaine et je suis un peu perdue :/"));
                mListMessages.add(new FDMMessage(mSharedPrefManager.getString(FDMSharedPrefKey.PSEUDO),"telecharge l'appli RATP elle est complète ;)"));
                mListMessages.add(new FDMMessage("Mathilde","Cool merci du conseil"));
                mListMessages.add(new FDMMessage(mSharedPrefManager.getString(FDMSharedPrefKey.PSEUDO),"Ca serait sympa de se revoir un de ces quatres"));
            }


            mAdapter = new FDMMessageAdapter(mContext,R.layout.item_message, mListMessages);
            mMessagesListview.setAdapter(mAdapter);
        }
        return rView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_message_send_message_btn:

                break;
        }
    }
}
