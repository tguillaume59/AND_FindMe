package com.tguillaume.findme.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.tguillaume.findme.R;

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
        View tLayout = LayoutInflater.from(mContext).inflate(R.layout.searching_infos_view,null);
        tLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mSearchingTextview = (TextView) tLayout.findViewById(R.id.searching_infos_textview);
        mAvatarImg = (ImageView) tLayout.findViewById(R.id.searching_infos_imageview);
        mSpiner = (Spinner) tLayout.findViewById(R.id.searching_infos_spinner);

        this.addView(tLayout);
    }


    /*public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rView = inflater.inflate(R.layout.fragment_searching_infos, container, false);

        // avec quelles valeurs initialiser le spinner ?
        ArrayList<String> rDatasRoot = new ArrayList<String>();
        rDatasRoot.add("");

        Spinner rSpinner = (Spinner) rView.findViewById(R.id.searching_infos_spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this.getContext(),R.layout.support_simple_spinner_dropdown_item, rDatasRoot);
        rSpinner.setAdapter(spinnerArrayAdapter);

        return rView;
    }*/

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

    public void notifyDataChange(ArrayList<String> sList) {
        if(mSpiner != null){

        }
        /*ArrayAdapter<String> rSpinnerAdapter = (ArrayAdapter<String>) rSpinner.getAdapter();

        rSpinnerAdapter.clear();
        rSpinnerAdapter.addAll(sList);
        rSpinnerAdapter.notifyDataSetChanged();*/
    }
}
