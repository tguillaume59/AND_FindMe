package com.tguillaume.findme.modules.messages.adpater;
/**
 * @Project : AND_FindMe
 * <p>
 * < file name >.java
 * <p>
 * Created by TARTARA Guillaume on 01/05/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tguillaume.findme.R;
import com.tguillaume.findme.common.manager.implementation.FDMSharedPrefManager;
import com.tguillaume.findme.common.manager.key.FDMSharedPrefKey;
import com.tguillaume.findme.modules.messages.entity.FDMMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillaume on 01/05/2017.
 */

public class FDMMessageAdapter extends ArrayAdapter<FDMMessage> {

    private Context mContext;
    private ArrayList<FDMMessage> mListMessages;

    private RelativeLayout mSenderLayout;
    private RelativeLayout mReceiverLayout;
    private TextView mSenderTextview;
    private TextView mReceiverTextview;

    public FDMMessageAdapter(Context context, int resource) {
        super(context, resource);
        mContext = context;
    }

    public FDMMessageAdapter(Context context, int resource, ArrayList<FDMMessage> objects) {
        super(context, resource, objects);
        mContext = context;
        mListMessages = objects;
    }

    public FDMMessageAdapter(Context context, int resource, FDMMessage[] objects) {
        super(context, resource, objects);
        mContext = context;
    }

    public FDMMessageAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        mContext = context;
    }

    public FDMMessageAdapter(Context context, int resource, int textViewResourceId, ArrayList<FDMMessage> objects) {
        super(context, resource, textViewResourceId, objects);
        mContext = context;
        mListMessages = objects;
    }

    public FDMMessageAdapter(Context context, int resource, int textViewResourceId, FDMMessage[] objects) {
        super(context, resource, textViewResourceId, objects);
        mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Chargement du layout de ligne
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_message, parent, false);

        mSenderLayout = (RelativeLayout)rowView.findViewById(R.id.item_message_sender_layout);
        mReceiverLayout = (RelativeLayout)rowView.findViewById(R.id.item_message_receiver_layout);
        mSenderTextview = (TextView)rowView.findViewById(R.id.item_message_sender_textview);
        mReceiverTextview = (TextView)rowView.findViewById(R.id.item_message_receiver_textview);

        FDMSharedPrefManager tSharedManager = new FDMSharedPrefManager(mContext);

        if(mListMessages != null){
            FDMMessage tMessage = mListMessages.get(position);
                if(tMessage.getSender().equals(tSharedManager.getString(FDMSharedPrefKey.PSEUDO))){
                    mReceiverLayout.setVisibility(View.GONE);
                    mSenderLayout.setVisibility(View.VISIBLE);
                    mSenderTextview.setText(tMessage.getMessage());
                }else {
                    mReceiverLayout.setVisibility(View.VISIBLE);
                    mSenderLayout.setVisibility(View.GONE);
                    mReceiverTextview.setText(tMessage.getMessage());
                }

        }

        return rowView;

    }
}
