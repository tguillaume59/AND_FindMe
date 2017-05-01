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
import android.widget.TextView;

import com.tguillaume.findme.R;
import com.tguillaume.findme.modules.messages.entity.FDMContactEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillaume on 01/05/2017.
 */

public class FDMListMessageAdapter extends ArrayAdapter<FDMContactEntity> {

    private final static String TAG = FDMListMessageAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<FDMContactEntity> mListContacts;


    public FDMListMessageAdapter(Context context, int resource) {
        super(context, resource);
        mContext = context;
    }

    public FDMListMessageAdapter(Context context, int resource, ArrayList<FDMContactEntity> objects) {
        super(context, resource, objects);
        mContext = context;
        mListContacts = objects;
    }

    public FDMListMessageAdapter(Context context, int resource, FDMContactEntity[] objects) {
        super(context, resource, objects);
        mContext = context;
    }

    public FDMListMessageAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        mContext = context;
    }

    public FDMListMessageAdapter(Context context, int resource, int textViewResourceId, ArrayList<FDMContactEntity> objects) {
        super(context, resource, textViewResourceId, objects);
        mContext = context;
        mListContacts = objects;
    }

    public FDMListMessageAdapter(Context context, int resource, int textViewResourceId, FDMContactEntity[] objects) {
        super(context, resource, textViewResourceId, objects);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Chargement du layout de ligne
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_list_message, parent, false);


        if(mListContacts != null){
            ((TextView)rowView.findViewById(R.id.item_list_message_contact_title)).setText(mListContacts.get(position).getName());
            ((TextView)rowView.findViewById(R.id.item_list_message_date_textview)).setText(mListContacts.get(position).getDate());
            ((TextView)rowView.findViewById(R.id.item_list_message_last_message_textview)).setText(mListContacts.get(position).getLastMessage());
        }

        return rowView;
    }
}
