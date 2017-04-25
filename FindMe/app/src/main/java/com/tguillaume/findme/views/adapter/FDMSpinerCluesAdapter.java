package com.tguillaume.findme.views.adapter;
/**
 * @Project : AND_FindMe
 * <p>
 * < file name >.java
 * <p>
 * Created by TARTARA Guillaume on 25/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tguillaume.findme.R;

import java.util.ArrayList;

/**
 * Created by guillaume on 25/04/2017.
 */

public class FDMSpinerCluesAdapter  extends ArrayAdapter<String> {

    private static final String TAG = FDMSpinerCluesAdapter.class.getSimpleName();
    private ArrayList<String> mListClues;
    private Context mContext;
    private TextView mClueTextView;

    public FDMSpinerCluesAdapter(Context context, int resource) {
        super(context, resource);
        mContext = context;

    }

    public FDMSpinerCluesAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        mContext = context;
        mListClues = objects;
    }

    public FDMSpinerCluesAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        mContext = context;
    }

    public FDMSpinerCluesAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        mContext = context;
    }

    public FDMSpinerCluesAdapter(Context context, int resource, int textViewResourceId, ArrayList<String> objects) {
        super(context, resource, textViewResourceId, objects);
        mContext = context;
        mListClues= objects;
    }

    public FDMSpinerCluesAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
        super(context, resource, textViewResourceId, objects);
        mContext = context;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rView = inflater.inflate(R.layout.item_spiner_clues, parent, false);
        mClueTextView = (TextView)rView.findViewById(R.id.item_spiner_clue_textview);

        if(mListClues !=null){
            if(mListClues.size() >= position){
                mClueTextView.setText(mListClues.get(position));
            }
        }


        return rView;
    }


    // It gets a View that displays in the drop down popup the data at the specified position
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // It gets a View that displays the data at the specified position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}
