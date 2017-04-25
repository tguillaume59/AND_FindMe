package com.tguillaume.findme.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.tguillaume.findme.R;
import com.tguillaume.findme.views.adapter.FDMSpinerCluesAdapter;

import java.util.ArrayList;

/**
 * Created by Avast on 24/04/2017.
 */

public class FDMSearchingInfos extends RelativeLayout {

    private final static String TAG = FDMSearchingInfos.class.getSimpleName();
    private Context mContext;
    private TextView mSearchingTextview;
    private ImageView mAvatarImg;
    private Spinner mSpiner;
    private FDMSpinerCluesAdapter mAdapter;
    private ArrayList<String> mListClues;

    public FDMSearchingInfos(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public FDMSearchingInfos(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public FDMSearchingInfos(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init(){
        Log.i(TAG,"init");
        View tLayout = LayoutInflater.from(mContext).inflate(R.layout.searching_infos_view,null);
        tLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mSearchingTextview = (TextView) tLayout.findViewById(R.id.searching_infos_textview);
        mAvatarImg = (ImageView) tLayout.findViewById(R.id.searching_infos_imageview);
        mSpiner = (Spinner) tLayout.findViewById(R.id.searching_infos_spinner);

        createList();
        this.addView(tLayout);
    }

    public void createList(){
        if(mListClues != null){
            mListClues.clear();
            mListClues = null;
        }
        mListClues = new ArrayList<>();
    }

    public void setName(String name) {
        if(mSearchingTextview != null) {
            String tFind = mContext.getString(R.string.find_guy) + " " + name;
            mSearchingTextview.setText(tFind);
        }
    }

    public void setAvatarPicture(Drawable sAvatar) {
        if(mAvatarImg != null) {
            mAvatarImg.setImageDrawable(sAvatar);
        }
    }

    public void addClues(String sClue){
        if(mListClues != null) {
            mListClues.add(sClue);
            notifyDataChange();
        }
    }
    public void notifyDataChange() {
        mAdapter = null;
        mAdapter = new FDMSpinerCluesAdapter(mContext,R.id.item_spiner_clue_textview,mListClues);
        mSpiner.setAdapter(mAdapter);


    }
}
